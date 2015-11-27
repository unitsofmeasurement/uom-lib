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
package tec.uom.domain.health.unit;

import javax.measure.Unit;
import javax.measure.spi.SystemOfUnits;

import tec.uom.domain.health.Floor;
import tec.uom.domain.health.HeartRate;
import tec.uom.domain.health.Heartbeat;
import tec.uom.domain.health.Step;
import tec.uom.se.unit.ProductUnit;
import tec.uom.se.AbstractSystemOfUnits;
import tec.uom.se.unit.BaseUnit;
import tec.uom.se.unit.Units;

/**
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.7.2
 */
public class Health extends AbstractSystemOfUnits {

	/**
	 * The singleton instance of {@code Health}.
	 */
	private static final Health INSTANCE = new Health();

	/**
	 * Default constructor (prevents this class from being instantiated).
	 */
	private Health() {
	}

	private static final Unit<Heartbeat> BEAT = addUnit(new BaseUnit<Heartbeat>(
			"b"));

	/** BPM */
	public static final Unit<HeartRate> BPM = addUnit(new ProductUnit<HeartRate>(
			BEAT.divide(Units.SECOND.multiply(60))));

	@Override
	public String getName() {
		return Health.class.getSimpleName();
	}

	/** Step */
	public static final Unit<Step> STEP = addUnit(new BaseUnit<Step>("st"));

	/** Floor */
	public static final Unit<Floor> FLOOR = addUnit(new BaseUnit<Floor>("flr"));

	/**
	 * Returns the singleton instance of this class.
	 *
	 * @return the Seismic system instance.
	 */
	public static final SystemOfUnits getInstance() {
		return INSTANCE;
	}

	/**
	 * Adds a new unit not mapped to any specified quantity type.
	 *
	 * @param unit
	 *            the unit being added.
	 * @return <code>unit</code>.
	 */
	private static <U extends Unit<?>> U addUnit(U unit) {
		INSTANCE.units.add(unit);
		return unit;
	}

	/**
	 * Adds a new named unit to the collection.
	 *
	 * @param unit
	 *            the unit being added.
	 * @param name
	 *            the name of the unit.
	 * @return <code>unit</code>.
	 */
//	@SuppressWarnings({ "unchecked", "unused" })
//	private static <U extends Unit<?>> U addUnit(U unit, String name) {
//		if (name != null && unit instanceof AbstractUnit) {
//			AbstractUnit<?> aUnit = (AbstractUnit<?>) unit;
//			// aUnit.setName(name);
//			INSTANCE.units.add(aUnit);
//			return (U) aUnit;
//		}
//		INSTANCE.units.add(unit);
//		return unit;
//	}
}
