/*
 * Units of Measurement Jakarta JSON-B Library
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
package tech.uom.lib.yasson;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.measure.Dimension;
import org.junit.jupiter.api.Test;
import tech.units.indriya.unit.UnitDimension;

/**
 * @author keilw
 */
public class DimensionJsonDeserializerTest {

    /**
     * Test of deserialize method, of class DimensionJsonDeserializer.Inspired by
     * https://stackoverflow.com/questions/21787128/how-to-unit-test-jackson-jsonserializer-and-jsondeserializer
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testDeserialize() throws IOException {
    	// Create JSONB engine with pretty output and custom serializer/deserializer
    	JsonbConfig config = new JsonbConfig()
    	        .withFormatting(true)    	        
    	        .withDeserializers(new DimensionJsonDeserializer());
    	Jsonb jsonb = JsonbBuilder.create(config);
    	
    	InputStream stream = new ByteArrayInputStream("{\"[L]\":2,\"[T]\":1}".getBytes(StandardCharsets.UTF_8));
        //JsonParser parser = objectMapper.getFactory().createParser(stream);        
        //DeserializationContext ctxt = objectMapper.getDeserializationContext();
        //DimensionJsonDeserializer instance = new DimensionJsonDeserializer();
        Dimension expResult = UnitDimension.LENGTH.pow(2).multiply(UnitDimension.TIME);
        Dimension result = jsonb.fromJson(stream, Dimension.class);
        assertEquals(expResult, result);
    }
}
