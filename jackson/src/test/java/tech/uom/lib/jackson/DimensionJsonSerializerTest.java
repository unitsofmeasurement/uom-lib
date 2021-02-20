/*
 * Units of Measurement Jackson Library
 * Copyright (c) 2005-2021, Werner Keil and others.
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
 * 3. Neither the name of JSR-385, Indriya nor the names of their contributors may be used to endorse or promote products
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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import javax.measure.Dimension;
import org.junit.jupiter.api.Test;
import tech.units.indriya.unit.UnitDimension;

/**
 * @author richter
 * @author keilw
 */
public class DimensionJsonSerializerTest {

    /**
     * Test of serialize method, of class DimensionJsonSerializer.Inspired by
     * https://stackoverflow.com/questions/21787128/how-to-unit-test-jackson-jsonserializer-and-jsondeserializer
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testSerialize() throws IOException {
        Dimension value = UnitDimension.LENGTH.multiply(UnitDimension.TIME.pow(2));
        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        try (JsonGenerator gen = objectMapper.getFactory().createGenerator(writer)) {
            SerializerProvider serializers = null;
            // unused
            DimensionJsonSerializer instance = new DimensionJsonSerializer();
            instance.serialize(value, gen, serializers);
        }
        String expResult = "{\"[L]\":1,\"[T]\":2}";
        // escaping is extranous here, but JsonGenertor references which work
        // as needed are hard to obtain
        String result = writer.toString();
        assertEquals(expResult, result);
    }
}
