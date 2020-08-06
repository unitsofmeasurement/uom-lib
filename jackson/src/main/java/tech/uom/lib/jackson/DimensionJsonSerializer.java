/*
 *  Units of Measurement Jackson Library for JSON support
 *  Copyright (c) 2012-2020, Werner Keil and others
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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import java.io.IOException;
import javax.measure.Dimension;

/**
 *
 * @author richter
 * @author keilw
 */
class DimensionJsonSerializer extends StdScalarSerializer<Dimension> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DimensionJsonSerializer() {
        super(Dimension.class);
    }

    /**
     * Serializes a dimension by serializing it's base dimension map.
     *
     * Based on my question and answer at
     * https://stackoverflow.com/questions/48509189/jsr-275-dimension-string-serialization-and-deserialization
     * which might contain better alternatives meanwhile.
     *
     * @param value       the dimension to serialize
     * @param gen         the generator as provided by {@link JsonSerializer}
     * @param serializers the serializers as provided by {@link JsonSerializer}
     * @throws IOException if an I/O exception occurs
     */
    @Override
    public void serialize(Dimension value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeObject(value.getBaseDimensions());
    }
}
