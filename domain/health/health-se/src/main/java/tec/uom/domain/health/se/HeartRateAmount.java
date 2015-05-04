/**
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2015, Jean-Marie Dautelle, Werner Keil, V2COM.
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
 * 3. Neither the name of JSR-363, Unit-API nor the names of its contributors may be used to endorse or promote products
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
package tec.uom.domain.health.se;

import javax.measure.Unit;

import tec.uom.domain.health.HeartRate;
import tec.uom.se.quantity.NumberQuantity;
import tec.uom.se.quantity.Quantities;

/**
 * Represents the speed of heart beat. The standard unit for this quantity is
 * "bpm" (Beats per Minute).
 *
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.5, Date: 2015-05-04
 */
public final class HeartRateAmount extends NumberQuantity<HeartRate> implements
		HeartRate {

	protected HeartRateAmount(Number number, Unit<HeartRate> unit) {
		super(number, unit);
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -7105140153324121388L;
	
	// of() won't work here due to being defined in BaseQuantity ;-|
	public static HeartRateAmount of(Number number, Unit<HeartRate> unit) {
		return (HeartRateAmount) Quantities.getQuantity(number, unit);
	}
}
