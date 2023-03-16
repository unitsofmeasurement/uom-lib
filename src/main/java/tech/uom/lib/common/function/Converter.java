/*
 * Units of Measurement Libraries
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
package tech.uom.lib.common.function;

/**
 * Represents an operation to convert values.
 * <p>
 * This is a <a href= "http://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html#package.description" >functional interface</a>
 * whose functional method is {@link #convert(F)}.
 *
 * @param <T> the type to convert to
 * @param <F> the type to convert from
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 1.1, 2018-04-19
 * @see <a href="http://en.wikipedia.org/wiki/Data_conversion">Wikipedia: Data Conversion</a>
 * @since 2.0
 */
@FunctionalInterface
public interface Converter<F, T> {

    /**
     * Returns a F converted into a T.
     *
     * @param from the value to convert
     * @return the converted result.
     */
    T convert(F from);
}
