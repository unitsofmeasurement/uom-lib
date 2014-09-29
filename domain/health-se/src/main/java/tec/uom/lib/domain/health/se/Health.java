/**
 *  Unit-API - Units of Measurement API for Java
 *  Copyright 2013-2014, Jean-Marie Dautelle, Werner Keil, V2COM and individual
 *  contributors by the @author tag.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package tec.uom.lib.domain.health.se;

import javax.measure.SystemOfUnits;
import javax.measure.Unit;

import tec.uom.lib.domain.health.Floor;
import tec.uom.lib.domain.health.HeartRate;
import tec.uom.lib.domain.health.Heartbeat;
import tec.uom.lib.domain.health.Step;
import tec.uom.se.unit.ProductUnit;
import tec.units.ri.AbstractSystemOfUnits;
import tec.units.ri.AbstractUnit;
import tec.units.ri.unit.BaseUnit;
import tec.units.ri.util.SI;

/**
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.5.1
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
			BEAT.divide(SI.SECOND.multiply(60))));

	@Override
	public String getName() {
		return Health.class.getSimpleName();
	}

	/** Step */
	@SuppressWarnings("unused")
	private static final Unit<Step> STEP = addUnit(new BaseUnit<Step>("st"));

	/** Floor */
	@SuppressWarnings("unused")
	private static final Unit<Floor> FLOOR = addUnit(new BaseUnit<Floor>("flr"));

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
	@SuppressWarnings({ "unchecked", "unused" })
	private static <U extends Unit<?>> U addUnit(U unit, String name) {
		if (name != null && unit instanceof AbstractUnit) {
			AbstractUnit<?> aUnit = (AbstractUnit<?>) unit;
			// aUnit.setName(name);
			INSTANCE.units.add(aUnit);
			return (U) aUnit;
		}
		INSTANCE.units.add(unit);
		return unit;
	}
}
