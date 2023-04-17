/*
 * Units of Measurement Common Library for Java
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
package tech.uom.lib.common.util;

import javax.measure.format.UnitFormat;

/**
 * The Serialization-mode, either {@code SIMPLE}, {@code EBNF} or {@code UCUM}
 * @version 1.5
 * @since 2.2
 * @see UnitFormat
 */
public enum SerializationMode implements DescriptiveEnum<SerializationMode> {
    /**
     * Serialization-mode using {@code SimpleUnitFormat}. This is the <strong>default</strong> mode if none is explicitly selected.
     */
    SIMPLE("Simple formatting"),
    /**
     * Serialization-mode using an {@code EBNF} instance of {@link UnitFormat}.
     * @see <a href="https://en.wikipedia.org/wiki/Extended_Backus%E2%80%93Naur_form">Wikipedia: Extended Backus–Naur form</a> 
     */
    EBNF("EBNF style formatting"),
    /**
     * Serialization-mode using a {@code UCUM} instance of {@link UnitFormat}.
     */
    UCUM("UCUM based formatting");
	
	private final String description;
	
	private SerializationMode(final String desc) {
    	this.description = desc;
    }

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public DescriptiveEnum<SerializationMode>[] dValues() {
		return SerializationMode.values();
	}
}