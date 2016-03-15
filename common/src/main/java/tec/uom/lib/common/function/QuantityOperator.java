
/*
 * Units of Measurement Reference Implementation
 * Copyright (c) 2005-2015, Jean-Marie Dautelle, Werner Keil, V2COM.
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
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or promote products
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
package tec.uom.lib.common.function;

import javax.measure.Quantity;

/**
 * Represents an operation on a single {@link Quantity} that produces a
 * result of type {@link Quantity}.
 * <p>
 * Examples might be an operator that rounds the amount to the nearest 1000, or
 * one that performs other quantity operations.
 * <p>
 * 
 * <pre>
 * // Example
 * result = thisOperator.apply(quantity);
 * </pre>
 *  
 * <h4>Implementation specification</h4>
 * The implementation must take the input object and apply it. The
 * implementation defines the logic of the operator and is responsible for
 * documenting that logic. It may use any method on {@code Quantity} to
 * determine the result.
 * <p>
 * The input object must not be altered. Instead, an altered copy of the
 * original must be returned. This provides equivalent, safe behavior for
 * immutable and mutable quantities.
 * <p>
 * This method may be called from multiple threads in parallel. It must be
 * thread-safe when invoked.
 * 
 * <p>
 * This interface is modeled after {@code java.util.function.UnaryOperator} in Java SE 8, 
 * but intended to be Java ME compatible as well.
 * 
 * @author Werner Keil
 * 
 * @version 0.1
 */
public interface QuantityOperator {

    /**
     * Applies the operator on the given quantity.
     * @param quantity the quantity to be operated on.
     * @return the applied quantity.
     */
    Quantity apply(Quantity quantity);
}
