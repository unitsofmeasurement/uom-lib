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

import static javax.measure.MetricPrefix.KILO;
import static javax.measure.MetricPrefix.MILLI;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import org.junit.jupiter.api.Test;

import systems.uom.ucum.UCUM;
import tech.units.indriya.unit.Units;

/**
 * @author keilw
 */
public class UnitJsonSerializerTest {
	private static final String ERRORMSG_LEN = "Expected JSON with a representation of the length unit";
	private static final String ERRORMSG_AREA = "Expected JSON with a representation of the area unit";
	private static final String ERRORMSG_TEMP = "Expected JSON with a representation of the temperature unit";	

	@Test
	public void testSerializeSpeedSimple() throws Exception {
		assertEquals("\"km/h\"", serialize(Units.KILOMETRE_PER_HOUR),
				"Expected JSON with a representation of the speed unit");
	}
	
	@Test
	public void testSerializeAreaSimple() throws Exception {
		assertEquals("\"m²\"", serialize(Units.SQUARE_METRE, SerializationMode.SIMPLE), ERRORMSG_AREA);
	}
	
	@Test
	public void testSerializeSpeedEBNF() throws Exception {
		assertEquals("\"km/h\"", serialize(Units.KILOMETRE_PER_HOUR, SerializationMode.EBNF),
				"Expected JSON with a representation of the speed unit");
	}
	
	@Test
	public void testSerializeAreaEBNF() throws Exception {
		assertEquals("\"m²\"", serialize(Units.SQUARE_METRE, SerializationMode.EBNF), ERRORMSG_AREA);
	}
	
	@Test
	public void testSerializeAreaUCUM() throws Exception {
		assertEquals("\"m2\"", serialize(Units.SQUARE_METRE, SerializationMode.UCUM), ERRORMSG_AREA);
	}

	@Test
	public void testSerializeTemperatureUCUM() throws Exception {
		assertEquals("\"[degF]\"", serialize(UCUM.FAHRENHEIT, SerializationMode.UCUM), ERRORMSG_TEMP);
		assertEquals("\"Cel\"", serialize(Units.CELSIUS, SerializationMode.UCUM), ERRORMSG_TEMP);
	}

	@Test
	public void testSerializeLengthUCUM() throws Exception {
		assertEquals("\"[mi_i]\"", serialize(UCUM.MILE_INTERNATIONAL, SerializationMode.UCUM), ERRORMSG_LEN);
		assertEquals("\"m\"", serialize(Units.METRE, SerializationMode.UCUM), ERRORMSG_LEN);
		assertEquals("\"km\"", serialize(KILO(UCUM.METER), SerializationMode.UCUM), ERRORMSG_LEN);
		assertEquals("\"mm\"", serialize(MILLI(UCUM.METER), SerializationMode.UCUM), ERRORMSG_LEN);
	}

	@Test
	public void testSerializeSpeedUCUM() throws Exception {
		assertEquals("\"[kph]\"", serialize(Units.KILOMETRE_PER_HOUR, SerializationMode.UCUM),
				"Expected JSON with a representation of the speed unit");
	}    
    		
	private String serialize(Object objectToSerialize, SerializationMode mode) throws IOException {		
		// Create JSONB engine with pretty output and custom serializer/deserializer
    	JsonbConfig config = new JsonbConfig()
    	        //.withFormatting(true)    	        
    	        .withSerializers(UnitJsonSerializer.ofMode(mode));
    	Jsonb jsonb = JsonbBuilder.create(config);
    	
		final Writer writer = new StringWriter();
		jsonb.toJson(objectToSerialize, writer);
		return writer.toString();
	}
	
	private String serialize(Object object) throws IOException {
		return serialize(object, SerializationMode.SIMPLE);
	}
}
