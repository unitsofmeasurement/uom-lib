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
 */
public class DimensionJsonSerializer extends StdScalarSerializer<Dimension> {

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
