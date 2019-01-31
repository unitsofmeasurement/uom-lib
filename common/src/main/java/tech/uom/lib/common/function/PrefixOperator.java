/*
 *  Units of Measurement Common Library for Java
 *  Copyright (c) 2005-2019, Jean-Marie Dautelle, Werner Keil and others.
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
 * 3. Neither the name of JSR-385, Units of Measurement nor the names of their contributors may be used to endorse or promote products
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
package tech.uom.lib.common.function;

import javax.measure.Prefix;
import javax.measure.Quantity;
import javax.measure.Unit;

/**
 * Represents a function that parses an input value and produces an output.
 * 
 * <p>
 * This is a <a href=
 * "https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/FunctionalInterface.html"
 * >functional interface</a> whose functional method is {@link #prefix()}.
 * 
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.9, 2019-01-31
 * @param <Q>
 *            the quantity type
 * @see Prefix 
 * @see Unit 
 * @since 2.0
 */
@FunctionalInterface
public interface PrefixOperator<Q extends Quantity<Q>> {
    /**
     * Returns a new unit equal to this unit prefixed by the specified {@code prefix}.
     *
     * @param prefix
     *          the prefix to apply on this unit.
     * @return the unit with the given prefix applied.
     */
    Unit<Q> prefix(Prefix prefix);
}
