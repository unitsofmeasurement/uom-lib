package tech.uom.lib.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.measure.Dimension;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tech.units.indriya.quantity.QuantityDimension;

/**
 *
 * @author richter
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
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream stream = new ByteArrayInputStream("{\"[L]\":2,\"[T]\":1}".getBytes(StandardCharsets.UTF_8));
        JsonParser parser = objectMapper.getFactory().createParser(stream);
        DeserializationContext ctxt = objectMapper.getDeserializationContext();
        DimensionJsonDeserializer instance = new DimensionJsonDeserializer();
        Dimension expResult = QuantityDimension.LENGTH.pow(2).multiply(QuantityDimension.TIME);
        Dimension result = instance.deserialize(parser, ctxt);
        assertEquals(expResult, result);
    }
}
