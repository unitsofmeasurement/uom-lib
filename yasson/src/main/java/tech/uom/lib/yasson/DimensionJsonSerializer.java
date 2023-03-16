/*
 * Units of Measurement Jakarta JSON-B Library
 * Copyright (c) 2005-2023, Werner Keil and others.
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
package tech.uom.lib.yasson;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
import javax.measure.Dimension;

/**
 * @author Werner Keil
 * @version 0.8
 */
public class DimensionJsonSerializer implements JsonbSerializer<Dimension> {

    /**
     * Serializes a dimension by serializing it's base dimension map.
     *
     * Based on my question and answer at
     * https://stackoverflow.com/questions/48509189/jsr-275-dimension-string-serialization-and-deserialization
     * which might contain better alternatives meanwhile.
     *
     * @param value     the dimension to serialize
     * @param generator the generator as provided by {@link JsonbSerializer}
     * @param ctx 		the SerializationContext as provided by {@link JsonbSerializer}
     */
	@Override
	public void serialize(Dimension value, JsonGenerator generator, SerializationContext ctx) {
       generator.writeStartObject();
       //ctx.serialize(value.getClass().getName(), value, generator);
       //ctx.serialize(value.getBaseDimensions(), generator);
       //generator.w
       for (Dimension baseDim : value.getBaseDimensions().keySet()) {
    	   generator.write(baseDim.toString(), value.getBaseDimensions().get(baseDim));
       }
       generator.writeEnd();		
	}

}
