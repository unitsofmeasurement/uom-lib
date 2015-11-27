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
/*
 *  Unit-API - Units of Measurement API for Java
 *  Copyright 2013-2015, Jean-Marie Dautelle, Werner Keil, V2COM and individual
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
import javax.measure.spi.SystemOfUnits;

import tec.uom.domain.health.Floor;
import tec.uom.domain.health.HeartRate;
import tec.uom.domain.health.Heartbeat;
import tec.uom.domain.health.Step;
import tec.units.ri.AbstractSystemOfUnits;
import tec.units.ri.unit.Units;
import tec.units.ri.unit.BaseUnit;

/**
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.5.4
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
    public static final Unit<HeartRate> BPM = addUnit(BEAT.divide(Units.SECOND.multiply(60)).asType(HeartRate.class));

    @Override
    public String getName() {
            return Health.class.getSimpleName();
    }

    /** Step */
    private static final Unit<Step> STEP = addUnit(new BaseUnit<Step>(
            "st"));

    /** Floor */
    private static final Unit<Floor> FLOOR = addUnit(new BaseUnit<Floor>(
            "flr"));

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
     * @param  unit the unit being added.
     * @return <code>unit</code>.
     */
    private static <U extends Unit<?>>  U addUnit(U unit) {
        INSTANCE.units.add(unit);
        return unit;
    }
}
