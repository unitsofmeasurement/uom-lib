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

import static tech.uom.lib.yasson.Mode.UCUM;

import java.lang.reflect.Type;
import java.text.ParsePosition;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.measure.Unit;

import systems.uom.ucum.format.UCUMFormat;
import systems.uom.ucum.format.UCUMFormat.Variant;
import tech.units.indriya.AbstractUnit;

/**
 * @author Werner Keil
 * @version 0.2
 */
public class UnitJsonDeserializer implements JsonbDeserializer<Unit> {

    /**
     * @since 2.0.2
     */
    private final Mode mode;
    
    private UnitJsonDeserializer(Mode mode) {
    	this.mode = mode;
    }
    
    public UnitJsonDeserializer() {
    	this(UCUM);
    }
	
    /**
     * Deserializes a unit.
     *
     * @param parser    	the JSON parser
     * @param ctx 			the DeserializationContext as provided by {@link JsonbDeserializer}
     * @param runtimeType 	the type of the returned object
     */
	@Override
	public Unit deserialize(JsonParser parser, DeserializationContext ctx, Type runtimeType) {			   
        Unit retValue = AbstractUnit.ONE;
        
		//JsonArray array = parser.getArray(); //.getArrayStream().collect(Collectors.toMap(p -> p.getId(), p -> p));
		while (parser.hasNext()) { 		
		
			Event evt = parser.next();
			switch (evt) {
			case VALUE_STRING:
				String str = parser.getString();
				
				switch(mode) {
            	// currently we use only UCUM	
            	default:
            		retValue = UCUMFormat.getInstance(Variant.CASE_SENSITIVE).parse(str, new ParsePosition(0));
            		break;
            	}
				 
				break;
			default:
				break;
			}
		
		}

        return retValue;
	}
}
