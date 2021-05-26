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

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.json.JsonObject;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import javax.measure.Unit;

import tech.units.indriya.AbstractUnit;

/**
 * @author Werner Keil
 * @version 0.1
 */
public class UnitJsonDeserializer implements JsonbDeserializer<Unit> {

    /**
     * Deserializes a unit by decomposing it's base dimension map.
     *
     * @param parser    	the JSON parser
     * @param ctx 			the DeserializationContext as provided by {@link JsonbDeserializer}
     * @param runtimeType 	the type of the returned object
     */
	@Override
	public Unit deserialize(JsonParser parser, DeserializationContext ctx, Type runtimeType) {
		//JsonArray array = parser.getArray(); //.getArrayStream().collect(Collectors.toMap(p -> p.getId(), p -> p));
		JsonObject obj = parser.getObject();
		Set<String> keys = obj.keySet();		
		//Map<String, Integer> baseDimensionsStrings = parser.readValueAs(Map.class);
		Map<String, Integer> baseDimensionsStrings = new HashMap<>();
		
		for (String key : keys) {
			baseDimensionsStrings.put(key, obj.getInt(key));
		}
		
        Unit retValue = AbstractUnit.ONE;
        return retValue;
	}
}
