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

import static tech.uom.lib.yasson.SerializationMode.SIMPLE;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
import javax.measure.Unit;
import javax.measure.format.UnitFormat;

import systems.uom.ucum.format.UCUMFormat;
import systems.uom.ucum.format.UCUMFormat.Variant;
import tech.units.indriya.format.EBNFUnitFormat;
import tech.units.indriya.format.SimpleUnitFormat;

/**
 * @author Werner Keil
 * @version 0.5
 */
public class UnitJsonSerializer implements JsonbSerializer<Unit> {
	
    /**
     * @since 2.0.2
     */
    private final SerializationMode mode;
    
    private UnitJsonSerializer(SerializationMode mode) {
    	this.mode = mode;
    }
    
    public UnitJsonSerializer() {
    	this(SIMPLE);
    }    
    
    /**
     * Returns {@code UnitJsonSerializer} using the given {@code SerializationMode}.
     *
     * @param mode the {@code SerializationMode} to use
     * @return a {@code UnitJsonSerializer} using the specified serialization-mode
     */
    public static UnitJsonSerializer ofMode(SerializationMode mode) {
    	return new UnitJsonSerializer(mode);
    }
    
    /**
     * Serializes a unit.
     *
     *
     * @param value     the unit to serialize
     * @param generator the generator as provided by {@link JsonbSerializer}
     * @param ctx 		the SerializationContext as provided by {@link JsonbSerializer}
     */
	@Override
	public void serialize(Unit value, JsonGenerator generator, SerializationContext ctx) {
	   final UnitFormat format = getFormat(mode); // TODO could we cache this on an instance level
	   
	   if (value == null) {
		   generator.writeNull();
	   } else {
		   String formattedUnit = format.format(value);
		   generator.write(formattedUnit);	   
       }
	}
	
	private static final UnitFormat getFormat(final SerializationMode mode) { 
		switch(mode) {	
		case UCUM:
			return UCUMFormat.getInstance(Variant.CASE_SENSITIVE);
		case EBNF:
			return EBNFUnitFormat.getInstance();
		default:
			return SimpleUnitFormat.getInstance();
		}
	}
}
