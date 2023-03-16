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
package tech.uom.lib.common.util;

import javax.measure.Unit;
import javax.measure.spi.SystemOfUnits;

public class SystemOfUnitsReporter {
    final SystemOfUnits sou;

    private SystemOfUnitsReporter(SystemOfUnits unitSystem) {
        this.sou = unitSystem;
    }

    public static SystemOfUnitsReporter of(SystemOfUnits unitSystem) {
        return new SystemOfUnitsReporter(unitSystem);
    }

    public void report(boolean showIndex) {
        printSoU(sou, showIndex);
    }

    public void report() {
        report(false);
    }

    private static void printSoU(final SystemOfUnits sou, final boolean showIndex) {
        int index = 0;
        System.out.println("Reporting " + sou.getName());
        for (Unit<?> u : sou.getUnits()) {
            index++;
            if (showIndex) {
                System.out.println(index + "; " + u.getName() + "; " + u.getSymbol() + "; " + u);
            } else {
                System.out.println(u.getName() + "; " + u.getSymbol() + "; " + u);
            }
        }
    }
}
