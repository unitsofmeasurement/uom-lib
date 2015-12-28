/**
 *  Unit-API - Units of Measurement API for Java
 *  Copyright 2013-2015, Jean-Marie Dautelle, Werner Keil, V2COM and individual
 *  contributors by the @author tag.
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
package tec.uom.domain.health.types;

import static tec.uom.domain.health.types.BMIRange.Category.*;
import static tec.uom.se.AbstractQuantity.NONE;

import javax.measure.Quantity;

import tec.uom.lib.common.function.Nameable;
import tec.uom.se.spi.Range;

/**
 * @author Werner Keil
 * @version 0.4
 * @see <a href="http://en.wikipedia.org/wiki/Body_Mass_index"> Wikipedia:
 *      BMI</a>
 */
public class BMIRange extends Range<Quantity<?>> implements Nameable {

	/**
	 * The category
	 */
	public static enum Category {
		/* ?, lt15, 15-16, 16-18.5, 18.5-25, 25-30, 30-35, 35-40, over 40 */
		UNKNOWN, VERY_SEVERELY_UNDERWEIGHT, SEVERELY_UNDERWEIGHT, UNDERWEIGHT, NORMAL, OVERWEIGHT, OBESE_CLASS_I, OBESE_CLASS_II, OBESE_CLASS_III
	}

	private final Category category;

	public Category getCategory() {
		return category;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Range
	 */
	protected BMIRange(Quantity<?> min, Quantity<?> max, Category level) {
		super(min, max);
		this.category = level;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Range
	 */
	protected BMIRange(Quantity<?> min, Quantity<?> max) {
		this(min, max, UNKNOWN);
	}

	/**
	 * Returns an {@code BMIRange} with the specified values.
	 *
	 * @param min
	 *            The minimum value for the BMI.
	 * @param max
	 *            The maximum value for the BMI.
	 * @return an {@code BMIRange} with the values present
	 */
	public static final BMIRange of(Quantity<?> min, Quantity<?> max) {
		if (min.getValue().doubleValue() <= 15) {
			return new BMIRange(min, max, Category.VERY_SEVERELY_UNDERWEIGHT);
		} else if (min.getValue().doubleValue() == 15
				&& max.getValue().doubleValue() == 16) {
			return new BMIRange(min, max, Category.SEVERELY_UNDERWEIGHT);
		} else if (min.getValue().doubleValue() == 16
				&& max.getValue().doubleValue() == 18.5) {
			return new BMIRange(min, max, Category.UNDERWEIGHT);
		} else if (min.getValue().doubleValue() == 18.5
				&& max.getValue().doubleValue() == 25) {
			return new BMIRange(min, max, Category.NORMAL);
		} else if (min.getValue().doubleValue() == 25
				&& max.getValue().doubleValue() == 30) {
			return new BMIRange(min, max, Category.OVERWEIGHT);
		} else if (min.getValue().doubleValue() == 30
				&& max.getValue().doubleValue() == 35) {
			return new BMIRange(min, max, Category.OBESE_CLASS_I);
		} else if (min.getValue().doubleValue() == 35
				&& max.getValue().doubleValue() == 40) {
			return new BMIRange(min, max, Category.OBESE_CLASS_II);
		} else if (min.getValue().doubleValue() >= 40) {
			return new BMIRange(min, max, Category.OBESE_CLASS_III);
		}
		return new BMIRange(min, max);
	}

	@Override
	public boolean hasMinimum() {
		return getMinimum() != null
				&& !NONE.equals(getMinimum())
				&& !(getMinimum().getUnit() == null || getMinimum().getValue() == null);
	}

	@Override
	public boolean hasMaximum() {
		return getMaximum() != null
				&& !NONE.equals(getMaximum())
				&& !(getMaximum().getUnit() == null || getMaximum().getValue() == null);
	}

	@Override
	public String toString() {
		return getName() + " [category=" + category + ", minimum="
				+ getMinimum() + ", maximum=" + getMaximum() + "]";
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

	@Override
	public boolean contains(Quantity<?> q) {
		if (q.getUnit().equals(getMinimum().getUnit())) {
			// TODO better null checks
			if (q.getValue().doubleValue() >= getMinimum().getValue().doubleValue() &&
					q.getValue().doubleValue() <= getMaximum().getValue().doubleValue()) {
				return true;
			}
		}
		return false;
	}
}
