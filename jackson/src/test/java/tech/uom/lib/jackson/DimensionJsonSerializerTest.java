package tech.uom.lib.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.io.StringWriter;
import javax.measure.Dimension;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tech.units.indriya.quantity.QuantityDimension;

/**
 *
 * @author richter
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
        Dimension value = QuantityDimension.LENGTH.multiply(QuantityDimension.TIME.pow(2));
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
