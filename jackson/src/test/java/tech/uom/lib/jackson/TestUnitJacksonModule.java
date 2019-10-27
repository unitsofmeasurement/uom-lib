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

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import systems.uom.ucum.UCUM;
import tech.units.indriya.unit.Units;

import javax.measure.Unit;

import static tech.units.indriya.AbstractUnit.ONE;
import static javax.measure.MetricPrefix.KILO;
import static javax.measure.MetricPrefix.MEGA;
import static javax.measure.MetricPrefix.MILLI;
import static org.junit.Assert.*;

/**
 * Unit tests for UnitJacksonModule
 */
public class TestUnitJacksonModule {
    // can't directly unit test the Jackson Module classes; need to go through
    // JsonFactory
    private JsonFactory jsonFactory;

    @Before
    public void setUp() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new UnitJacksonModule());
        this.jsonFactory = new JsonFactory(mapper);
    }

    @Test
    public void testSerializeArea() throws Exception {
        assertEquals("Expected JSON with a UCUM representation of the area unit", "\"m2\"",
                serialize(Units.SQUARE_METRE));
    }

    @Test
    public void testSerializeTemperature() throws Exception {
        assertEquals("Expected JSON with a UCUM representation of the temperature unit", "\"[degF]\"",
                serialize(UCUM.FAHRENHEIT));
        assertEquals("Expected JSON with a UCUM representation of the temperature unit", "\"Cel\"",
                serialize(Units.CELSIUS));
    }

    @Test
    public void testSerializeLength() throws Exception {
        assertEquals("Expected JSON with a UCUM representation of the length unit", "\"[mi_i]\"",
                serialize(UCUM.MILE_INTERNATIONAL));
        assertEquals("Expected JSON with a UCUM representation of the length unit", "\"m\"", serialize(Units.METRE));
        assertEquals("Expected JSON with a UCUM representation of the length unit", "\"km\"",
                serialize(KILO(UCUM.METER)));
        assertEquals("Expected JSON with a UCUM representation of the length unit", "\"mm\"",
                serialize(MILLI(UCUM.METER)));
    }

    @Test
    public void testSerializeSpeed() throws Exception {
        assertEquals("Expected JSON with a UCUM representation of the speed unit", "\"[kph]\"",
                serialize(Units.KILOMETRE_PER_HOUR));
    }

    @Test
    public void testParseArea() throws Exception {
        final Unit<?> parsedUnit = parse("\"[sft_i]\"", Unit.class);
        assertEquals("The Unit<Area> in the parsed JSON doesn't match", UCUM.SQUARE_FOOT_INTERNATIONAL, parsedUnit);
    }

    @Test
    public void testParseTemperature() throws Exception {
        final Unit<?> parsedUnit = parse("\"Cel\"", Unit.class);
        assertEquals("The Unit<Temperature> in the parsed JSON doesn't match", Units.CELSIUS, parsedUnit);
    }

    @Test
    public void testParseTemperatureInverse() throws Exception {
        final Unit<?> parsedUnit = parse("\"1/K\"", Unit.class);
        assertEquals("The Unit<Temperature> in the parsed JSON doesn't match", ONE.divide(Units.KELVIN), parsedUnit);
    }

    @Test
    public void testParseLength() throws Exception {
        Unit<?> parsedUnit = parse("\"m\"", Unit.class);
        assertEquals("The Unit<Length> in the parsed JSON doesn't match", Units.METRE, parsedUnit);
    }

    @Test(expected = JsonParseException.class)
    public void testParseWithUnrecognizedField() throws Exception {
        parse("foobar", Unit.class);
    }

    @Test
    public void testParseLengthKm() throws Exception {
        final Unit<?> parsedUnit = parse("\"km\"", Unit.class);
        assertEquals("The Unit<Length> in the parsed JSON doesn't match", KILO(Units.METRE), parsedUnit);
    }

    @Test
    public void testRoundTripSerialization() throws Exception {
        String serialized = serialize(MEGA(UCUM.METER));
        Unit<?> parsedUnit = parse(serialized, Unit.class);
        assertEquals("The Unit<Length> in the parsed JSON doesn't match", MEGA(UCUM.METER), parsedUnit);
    }

    protected String serialize(Object objectToSerialize) throws IOException {
        final Writer writer = new StringWriter();
        final JsonGenerator generator = this.jsonFactory.createJsonGenerator(writer);

        generator.writeObject(objectToSerialize);
        generator.close();
        return writer.toString();
    }

    protected <T> T parse(String json, Class<T> aClass) throws IOException {
        final JsonParser parser = this.jsonFactory.createJsonParser(json);
        T object = parser.readValueAs(aClass);

        parser.close();
        return object;
    }
}
