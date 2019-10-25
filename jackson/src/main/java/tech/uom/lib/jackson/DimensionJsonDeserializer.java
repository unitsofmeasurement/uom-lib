package tech.uom.lib.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.measure.Dimension;
import tech.units.indriya.quantity.QuantityDimension;

/**
 *
 * @author richter
 */
public class DimensionJsonDeserializer extends StdScalarDeserializer<Dimension> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DimensionJsonDeserializer() {
        super(Dimension.class);
    }

    @Override
    public Dimension deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        Map<String, Integer> baseDimensionsStrings = p.readValueAs(Map.class);
        Map<Dimension, Integer> baseDimensions = new HashMap<>(baseDimensionsStrings.entrySet().stream()
                .collect(Collectors.toMap(entry -> parseBaseDimension(entry.getKey()), entry -> entry.getValue())));
        Dimension retValue = QuantityDimension.NONE;
        for (Dimension baseDimension : baseDimensions.keySet()) {
            int exp = baseDimensions.get(baseDimension);
            retValue = retValue.multiply(baseDimension.pow(exp));
        }
        return retValue;
    }

    private static Dimension parseBaseDimension(String symbol) {
        switch (symbol) {
            case "[N]":
                return QuantityDimension.AMOUNT_OF_SUBSTANCE;
            case "[I]":
                return QuantityDimension.ELECTRIC_CURRENT;
            case "[L]":
                return QuantityDimension.LENGTH;
            case "[J]":
                return QuantityDimension.LUMINOUS_INTENSITY;
            case "[M]":
                return QuantityDimension.MASS;
            case "[\u0398]":
                return QuantityDimension.TEMPERATURE;
            case "[T]":
                return QuantityDimension.TIME;
            default:
                throw new IllegalArgumentException(String.format(
                        "dimension " + "symbol '%s' not supported, maybe dimensionless or " + "wrong universe?", symbol));
        }
    }
}
