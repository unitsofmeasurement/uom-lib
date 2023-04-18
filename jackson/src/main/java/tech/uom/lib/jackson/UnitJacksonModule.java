/*
 * Units of Measurement Jackson Library
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
package tech.uom.lib.jackson;

import static tech.uom.lib.jackson.SerializationMode.UCUM;

import java.io.IOException;
import java.text.ParsePosition;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import javax.measure.Dimension;
import javax.measure.Quantity;
import javax.measure.Unit;
import tech.units.indriya.format.EBNFUnitFormat;
import tech.units.indriya.format.SimpleUnitFormat;
import systems.uom.ucum.format.UCUMFormat;
import systems.uom.ucum.format.UCUMFormat.Variant;

/**
 * Configures Jackson to (de)serialize JSR 385 Unit objects using their UCUM representation, since the actual objects don't
 * translate well into JSON.
 * @version 2.1
 */
public class UnitJacksonModule extends SimpleModule {
    /**
     *
     */
    private static final long serialVersionUID = 7601584599518016604L;
       
    /**
     * @since 2.0.2
     */
    final SerializationMode mode;
    
    /**
     * 
     * @param mode the serialization-mode
     * @since 2.2 
     */
    public UnitJacksonModule(final SerializationMode mode) {
        super("UnitJsonSerializationModule", new Version(2, 1, 0, null, 
                UnitJacksonModule.class.getPackage().getName(), "uom-lib-jackson"));
        this.mode = mode;
        addSerializer(Unit.class, new UnitJsonSerializer());
        addSerializer(Dimension.class, new DimensionJsonSerializer());
        addDeserializer(Unit.class, new UnitJsonDeserializer());
        addDeserializer(Dimension.class, new DimensionJsonDeserializer());
        addDeserializer(Quantity.class, new QuantityJsonDeserializer());
    }
    
    public UnitJacksonModule() {
    	this(UCUM);
    }
    
    /**
     * Returns {@code UnitJsonSerializer} using the given {@code SerializationMode}.
     *
     * @param mode the {@code SerializationMode} to use
     * @return a {@code UnitJsonSerializer} using the specified serialization-mode
     * @since 2.2
     */
    public static UnitJacksonModule ofMode(SerializationMode mode) {
    	return new UnitJacksonModule(mode);
    }

    @SuppressWarnings("rawtypes")
    private class UnitJsonSerializer extends StdScalarSerializer<Unit> {
        /**
         *
         */
        private static final long serialVersionUID = 2500234678114311932L;

        protected UnitJsonSerializer() {
            super(Unit.class);
        }

        @Override
        public void serialize(Unit unit, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            if (unit == null) {
                jgen.writeNull();
            }
            else {
            	switch (mode) {            	
	            	// currently we use only UCUM
	            	default:
	            	// Format the unit using the UCUM representation.
	                // The string produced for a given unit is always the same; it is not affected by the locale.
	                // It can be used as a canonical string representation for exchanging units.
	                String ucumFormattedUnit = UCUMFormat.getInstance(Variant.CASE_SENSITIVE).format(unit, new StringBuilder()).toString();                
	                jgen.writeString(ucumFormattedUnit);
	                break;
            	}
            }
        }
    }

    @SuppressWarnings("rawtypes")
    private class UnitJsonDeserializer extends StdScalarDeserializer<Unit> {
        /**
         *
         */
        private static final long serialVersionUID = -6327531740958676293L;

        protected UnitJsonDeserializer() {
            super(Unit.class);
        }

        @Override
        public Unit deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            
            if (currentToken == JsonToken.VALUE_STRING) {
            	switch(mode) {
            	// currently we use only UCUM	
            	default:
            		return UCUMFormat.getInstance(Variant.CASE_SENSITIVE).parse(jsonParser.getText(), new ParsePosition(0));
            	}
            }
            throw deserializationContext.wrongTokenException(jsonParser, String.class,
                    JsonToken.VALUE_STRING,
                    "Expected unit value in String format");
        }
    }
}
