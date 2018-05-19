package com.opower.unitsofmeasure;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import tec.uom.se.unit.Units;
import tech.uom.lib.jackson.UnitJacksonModule;
import systems.uom.ucum.UCUM;

import javax.measure.Unit;

import static tec.uom.se.AbstractUnit.ONE;
import static tec.uom.se.unit.MetricPrefix.KILO;
import static tec.uom.se.unit.MetricPrefix.MILLI;
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
	Unit<?> parsedUnit = parse("\"[sft_i]\"", Unit.class);

	assertEquals("The Unit<Area> in the parsed JSON doesn't match", UCUM.SQUARE_FOOT_INTERNATIONAL, parsedUnit);
    }

    @Test
    public void testParseTemperature() throws Exception {
	Unit<?> parsedUnit = parse("\"Cel\"", Unit.class);
	assertEquals("The Unit<Temperature> in the parsed JSON doesn't match", Units.CELSIUS, parsedUnit);
    }
    
    @Test
    public void testParseTemperatureInverse() throws Exception {
	Unit<?> parsedUnit = parse("\"1/K\"", Unit.class);
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
    @Ignore("solve km formatting") // TODO solve km parsing
    public void testParseLengthKm() throws Exception {
	Unit<?> parsedUnit = parse("\"km\"", Unit.class);
	assertEquals("The Unit<Length> in the parsed JSON doesn't match", KILO(Units.METRE), parsedUnit);
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
