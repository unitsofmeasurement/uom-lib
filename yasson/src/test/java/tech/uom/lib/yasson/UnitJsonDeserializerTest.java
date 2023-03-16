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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.measure.Unit;

import org.junit.jupiter.api.Test;

import systems.uom.ucum.UCUM;
import tech.units.indriya.unit.Units;

/**
 * @author keilw
 */
public class UnitJsonDeserializerTest {
	private static final String ERRMSG_PARSE_LEN = "The Unit<Length> in the JSON doesn't match";
	private static final String ERRMSG_PARSE_MASS = "The Unit<Mass> in the JSON doesn't match";

    /**
     * Test the deserialize method of class UnitJsonDeserializer.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testSimple() throws IOException {
    	// Create JSONB engine with pretty output and custom deserializer
    	JsonbConfig config = new JsonbConfig()
    	        .withFormatting(true)    	        
    	        .withDeserializers(new UnitJsonDeserializer());
    	Jsonb jsonb = JsonbBuilder.create(config);
    	
    	InputStream stream = new ByteArrayInputStream("{\"unit\":\"m\"}".getBytes(StandardCharsets.UTF_8));
     
    	Unit<?> result = jsonb.fromJson(stream, Unit.class);
		assertEquals(Units.METRE, result, ERRMSG_PARSE_LEN);
    }
    
    /**
     * Test the deserialize method of class UnitJsonDeserializer.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testEBNF() throws IOException {
    	// Create JSONB engine with pretty output and custom deserializer
    	JsonbConfig config = new JsonbConfig()
    	        .withFormatting(true)    	        
    	        .withDeserializers(UnitJsonDeserializer.ofMode(SerializationMode.EBNF));
    	Jsonb jsonb = JsonbBuilder.create(config);
    	
    	InputStream stream = new ByteArrayInputStream("{\"unit\":\"kg\"}".getBytes(StandardCharsets.UTF_8));
     
    	Unit<?> result = jsonb.fromJson(stream, Unit.class);
		assertEquals(Units.KILOGRAM, result, ERRMSG_PARSE_MASS);
    }
    
    /**
     * Test the deserialize method of class UnitJsonDeserializer.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testUCUM() throws IOException {
    	// Create JSONB engine with pretty output and custom deserializer
    	JsonbConfig config = new JsonbConfig()
    	        .withFormatting(true)    	        
    	        .withDeserializers(UnitJsonDeserializer.ofMode(SerializationMode.UCUM));
    	Jsonb jsonb = JsonbBuilder.create(config);
    	
    	InputStream stream = new ByteArrayInputStream("{\"unit\":\"[ly]\"}".getBytes(StandardCharsets.UTF_8));
     
    	Unit<?> result = jsonb.fromJson(stream, Unit.class);
		assertEquals(UCUM.LIGHT_YEAR, result, ERRMSG_PARSE_LEN);
    }
}
