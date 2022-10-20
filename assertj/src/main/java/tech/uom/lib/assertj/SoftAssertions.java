/*
 * Units of Measurement AssertJ Library
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
package tech.uom.lib.assertj;

/**
 * Entry point for soft assertions of different data types.
 */
@jakarta.annotation.Generated(value = "assertj-assertions-generator")
public class SoftAssertions extends org.assertj.core.api.SoftAssertions {

    /**
     * Creates a new "soft" instance of <code>{@link tech.uom.lib.assertj.assertions.DimensionAssert}</code>.
     *
     * @param actual the actual value.
     * @return the created "soft" assertion object.
     */
    @org.assertj.core.util.CheckReturnValue
    public tech.uom.lib.assertj.assertions.DimensionAssert assertThat(javax.measure.Dimension actual) {
        return proxy(tech.uom.lib.assertj.assertions.DimensionAssert.class, javax.measure.Dimension.class, actual);
    }

    /**
     * Creates a new "soft" instance of <code>{@link tech.uom.lib.assertj.assertions.QuantityAssert}</code>.
     *
     * @param actual the actual value.
     * @return the created "soft" assertion object.
     */
    @org.assertj.core.util.CheckReturnValue
    public tech.uom.lib.assertj.assertions.QuantityAssert assertThat(@SuppressWarnings("rawtypes") javax.measure.Quantity actual) {
        return proxy(tech.uom.lib.assertj.assertions.QuantityAssert.class, javax.measure.Quantity.class, actual);
    }

    /**
     * Creates a new "soft" instance of <code>{@link tech.uom.lib.assertj.assertions.UnitAssert}</code>.
     *
     * @param actual the actual value.
     * @return the created "soft" assertion object.
     */
    @org.assertj.core.util.CheckReturnValue
    public tech.uom.lib.assertj.assertions.UnitAssert assertThat(@SuppressWarnings("rawtypes") javax.measure.Unit actual) {
        return proxy(tech.uom.lib.assertj.assertions.UnitAssert.class, javax.measure.Unit.class, actual);
    }

}
