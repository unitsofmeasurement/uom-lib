/*
 *  Units of Measurement Jackson Library for JSON support
 *  Copyright (c) 2012-2019, Werner Keil and others
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 *    and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-385, Units of Measurement nor the names of their contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package tech.uom.lib.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.measure.Dimension;
import tech.units.indriya.unit.UnitDimension;

/**
 * @author richter
 * @author keilw
 */
class DimensionJsonDeserializer extends StdScalarDeserializer<Dimension> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DimensionJsonDeserializer() {
        super(Dimension.class);
    }

    @Override
    public Dimension deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        @SuppressWarnings("unchecked")
		Map<String, Integer> baseDimensionsStrings = p.readValueAs(Map.class);
        Map<Dimension, Integer> baseDimensions = new HashMap<>(baseDimensionsStrings.entrySet().stream()
                .collect(Collectors.toMap(entry -> parseBaseDimension(entry.getKey()), entry -> entry.getValue())));
        Dimension retValue = UnitDimension.NONE;
        for (Dimension baseDimension : baseDimensions.keySet()) {
            int exp = baseDimensions.get(baseDimension);
            retValue = retValue.multiply(baseDimension.pow(exp));
        }
        return retValue;
    }

    private static Dimension parseBaseDimension(String symbol) {
        switch (symbol) {
            case "[N]":
                return UnitDimension.AMOUNT_OF_SUBSTANCE;
            case "[I]":
                return UnitDimension.ELECTRIC_CURRENT;
            case "[L]":
                return UnitDimension.LENGTH;
            case "[J]":
                return UnitDimension.LUMINOUS_INTENSITY;
            case "[M]":
                return UnitDimension.MASS;
            case "[\u0398]":
                return UnitDimension.TEMPERATURE;
            case "[T]":
                return UnitDimension.TIME;
            default:
                throw new IllegalArgumentException(String.format(
                        "dimension " + "symbol '%s' not supported, maybe dimensionless or " + "wrong universe?", symbol));
        }
    }
}
