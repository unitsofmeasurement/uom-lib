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

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.measure.Quantity;
import javax.measure.Unit;
import tech.uom.lib.common.util.NaturalQuantityComparator;

/**
 * Utility methods for operating on {@link Unit units} and {@link Quantity quantities} with Java
 * {@linkplain FunctionalInterface functional interfaces} like {@linkplain Function}.
 *
 * @author Otavio
 * @author Werner
 * @version 1.0.3
 * @since 2.0
 */
@SuppressWarnings("rawtypes")
public final class QuantityFunctions {

    private QuantityFunctions() {
        throw new Error("no instances");
    }

    /**
     * Creates a comparator to sort by number, ignoring the unit.
     *
     * @param <Q> the type of quantity
     * @return <p>
     * <b>Given:</b>
     * <p>
     * <code>
     * Quantity&lt;Time&gt; day = timeFactory.create(1, Units.DAY);<br>
     * Quantity&lt;Time&gt; hours = timeFactory.create(18, Units.HOUR);<br>
     * Quantity&lt;Time&gt; minutes = timeFactory.create(15, Units.HOUR);<br>
     * Quantity&lt;Time&gt; seconds = timeFactory.create(100, Units.HOUR);<br>
     * </code>
     * <p>
     * will return: <code>day, hours, minutes, seconds</code>
     * </p>
     * @throws NullPointerException if any of the values to compare is {@code null}
     */
    public static <Q extends Quantity<Q>> Comparator<Quantity<Q>> sortNumber() {
        return (q1, q2) -> Double.compare(q1.getValue().doubleValue(), q2.getValue().doubleValue());
    }

    /**
     * Creates a comparator to sort by number descending, ignoring the unit.
     *
     * @param <Q> the type of quantity
     * @return <p>
     * <b>Given:</b>
     * <p>
     * <code>
     * Quantity&lt;Time&gt; day = timeFactory.create(1, Units.DAY);<br>
     * Quantity&lt;Time&gt; hours = timeFactory.create(18, Units.HOUR);<br>
     * Quantity&lt;Time&gt; minutes = timeFactory.create(15, Units.HOUR);<br>
     * Quantity&lt;Time&gt; seconds = timeFactory.create(100, Units.HOUR);<br>
     * </code>
     * <p>
     * will return: <code>seconds, hours, minutes, day</code>
     * </p>
     * @throws NullPointerException if any of the values to compare is {@code null}
     */
    public static <Q extends Quantity<Q>> Comparator<Quantity<Q>> sortNumberDesc() {
        Comparator<Quantity<Q>> sortNumber = sortNumber();
        return sortNumber.reversed();
    }

    /**
     * Creates a comparator to sort by name, ignoring the value.
     *
     * @param <Q> the type of quantity
     * @return <p>
     * <b>Given:</b>
     * <p>
     * <code>
     * Quantity&lt;Time&gt; day = timeFactory.create(1, Units.DAY);<br>
     * Quantity&lt;Time&gt; hours = timeFactory.create(18, Units.HOUR);<br>
     * Quantity&lt;Time&gt; minutes = timeFactory.create(15, Units.HOUR);<br>
     * Quantity&lt;Time&gt; seconds = timeFactory.create(100, Units.HOUR);<br>
     * </code>
     * <p>
     * will return: <code>day, hours, minutes, seconds</code>
     * </p>
     * @throws NullPointerException if any of the values to compare is {@code null}
     */
    public static <Q extends Quantity<Q>> Comparator<Quantity<Q>> sortSymbol() {
        return (q1, q2) -> q1.getUnit().getSymbol().compareTo(q2.getUnit().getSymbol());
    }

    /**
     * Creates a comparator to sort by name descending, ignoring the value.
     *
     * @param <Q> the type of quantity
     * @return <p>
     * <b>Given:</b>
     * </p>
     * <code>
     * Quantity&lt;Time&gt; day = timeFactory.create(1, Units.DAY);<br>
     * Quantity&lt;Time&gt; hours = timeFactory.create(18, Units.HOUR);<br>
     * Quantity&lt;Time&gt; minutes = timeFactory.create(15, Units.HOUR);<br>
     * Quantity&lt;Time&gt; seconds = timeFactory.create(100, Units.HOUR);<br>
     * </code>
     * <p>
     * will return: <code>seconds, minutes, hour,  day</code>
     * </p>
     * @throws NullPointerException if any of the values to compare is {@code null}
     */
    public static <Q extends Quantity<Q>> Comparator<Quantity<Q>> sortSymbolDesc() {
        Comparator<Quantity<Q>> sortSymbol = sortSymbol();
        return sortSymbol.reversed();
    }

    /**
     * Creates a comparator to sort by natural order, looking to both the unit and the value.
     *
     * @param <Q> the type of quantity
     * @return <p>
     * <b>Given:</b>
     * </p>
     * <code>
     * Quantity&lt;Time&gt; day = timeFactory.create(1, Units.DAY);<br>
     * Quantity&lt;Time&gt; hours = timeFactory.create(18, Units.HOUR);<br>
     * Quantity&lt;Time&gt; minutes = timeFactory.create(15, Units.HOUR);<br>
     * Quantity&lt;Time&gt; seconds = timeFactory.create(100, Units.HOUR);<br>
     * </code>
     * <p>
     * will return: <code>seconds, minutes, hours, day</code>
     * </p>
     * @throws NullPointerException if any of the values to compare is {@code null}
     */
    @SuppressWarnings("unchecked")
    public static <Q extends Quantity<Q>> Comparator<Quantity<Q>> sortNatural() {
        return new NaturalQuantityComparator();
    }

    /**
     * Creates a comparator to sort by natural order descending, looking to both the unit and the value.
     *
     * @param <Q> the type of quantity
     * @return <p>
     * <b>Given:</b>
     * </p>
     * <code>
     * Quantity&lt;Time&gt; day = timeFactory.create(1, Units.DAY);<br>
     * Quantity&lt;Time&gt; hours = timeFactory.create(18, Units.HOUR);<br>
     * Quantity&lt;Time&gt; minutes = timeFactory.create(15, Units.HOUR);<br>
     * Quantity&lt;Time&gt; seconds = timeFactory.create(100, Units.HOUR);<br>
     * </code>
     * <p>
     * will return: <code>day, hour, minute, second</code>
     * </p>
     * @throws NullPointerException if any of the values to compare is {@code null}
     */
    public static <Q extends Quantity<Q>> Comparator<Quantity<Q>> sortNaturalDesc() {
        Comparator<Quantity<Q>> sortNatural = sortNatural();
        return sortNatural.reversed();
    }

    /**
     * Creates a BinaryOperator to calculate the minimum Quantity
     *
     * @param <Q> the type of quantity
     * @return the min BinaryOperator, not null.
     */
    public static <Q extends Quantity<Q>> BinaryOperator<Quantity<Q>> min() {

        return (q1, q2) -> {
            double d1 = q1.getValue().doubleValue();
            double d2 = q2.to(q1.getUnit()).getValue().doubleValue();
            double min = Double.min(d1, d2);
            if (min == d1) {
                return q1;
            }
            return q2;
        };
    }

    /**
     * Creates a BinaryOperator to calculate the maximum Quantity
     *
     * @param <Q> the type of quantity
     * @return the max BinaryOperator, not null.
     */
    public static <Q extends Quantity<Q>> BinaryOperator<Quantity<Q>> max() {

        return (q1, q2) -> {
            double d1 = q1.getValue().doubleValue();
            double d2 = q2.to(q1.getUnit()).getValue().doubleValue();
            double min = Double.max(d1, d2);
            if (min == d1) {
                return q1;
            }
            return q2;
        };
    }

    /**
     * Creates a BinaryOperator to sum.
     *
     * @param <Q> the type of quantity
     * @return the sum BinaryOperator
     */
    public static <Q extends Quantity<Q>> BinaryOperator<Quantity<Q>> sum() {
        return Quantity::add;
    }

    /**
     * Creates a BinaryOperator to sum converting to unit
     *
     * @param unit unit to be converting
     * @param <Q> the type of quantity
     * @return the sum BinaryOperator converting to unit
     */
    public static <Q extends Quantity<Q>> BinaryOperator<Quantity<Q>> sum(Unit<Q> unit) {
        return (q1, q2) -> q1.to(unit).add(q2.to(unit));
    }

    /**
     * Predicate to filter to one or more units
     *
     * @param units - units to be filtered (optional)
     * @param <Q> the type of quantity
     * @return A predicate to filter one or more units
     */
    @SafeVarargs
    public static <Q extends Quantity<Q>> Predicate<Quantity<Q>> fiterByUnit(Unit<Q>... units) {

        if (Objects.isNull(units) || units.length == 0) {
            return q -> true;
        }
        Predicate<Quantity<Q>> predicate = null;
        for (Unit<Q> u : units) {
            if (Objects.isNull(predicate)) {
                predicate = q -> q.getUnit().equals(u);
            } else {
                predicate = predicate.or(q -> q.getUnit().equals(u));
            }
        }
        return predicate;
    }

    /**
     * Predicate to filter excluding these units
     *
     * @param units - units to be filtered (optional)
     * @param <Q> the type of quantity
     * @return A predicate to filter to not be these units
     */
    @SafeVarargs
    public static <Q extends Quantity<Q>> Predicate<Quantity<Q>> fiterByExcludingUnit(Unit<Q>... units) {
        if (Objects.isNull(units) || units.length == 0) {
            return q -> true;
        }
        return fiterByUnit(units).negate();
    }

    /**
     * creates a Filter to greater than number, ignoring units
     *
     * @param value - the value to be used in Predicate
     * @param <Q> the type of quantity
     * @return the Predicate greater than this number, ignoring units
     */
    public static <Q extends Quantity<Q>> Predicate<Quantity<Q>> isGreaterThan(Number value) {
        return q -> q.getValue().doubleValue() > value.doubleValue();
    }

    /**
     * creates a filter to greater than the quantity measure
     *
     * @param quantity - the measure to be used in filter
     * @param <Q> the type of quantity
     * @return the Predicate greater than this measure
     */
    public static <Q extends Quantity<Q>> Predicate<Quantity<Q>> isGreaterThan(Quantity<Q> quantity) {
        return q -> q.to(quantity.getUnit()).getValue().doubleValue() > quantity.getValue().doubleValue();
    }

    /**
     * creates a Filter to greater or equals than number, ignoring units
     *
     * @param value - the value to be used in Predicate
     * @param <Q> the type of quantity
     * @return the Predicate greater or equals than this number, ignoring units
     */
    public static <Q extends Quantity<Q>> Predicate<Quantity<Q>> isGreaterThanOrEqualTo(Number value) {
        return q -> q.getValue().doubleValue() >= value.doubleValue();
    }

    /**
     * creates a filter to greater or equals than the quantity measure
     *
     * @param quantity - the measure to be used in filter
     * @param <Q> the type of quantity
     * @return the Predicate greater or equals than this measure
     */
    public static <Q extends Quantity<Q>> Predicate<Quantity<Q>> isGreaterThanOrEqualTo(Quantity<Q> quantity) {
        return q -> q.to(quantity.getUnit()).getValue().doubleValue() >= quantity.getValue().doubleValue();
    }

    /**
     * creates a Filter to lesser than number, ignoring units
     *
     * @param value - the value to be used in Predicate
     * @param <Q> the type of quantity
     * @return the Predicate greater than this number, ignoring units
     */
    public static <Q extends Quantity<Q>> Predicate<Quantity<Q>> isLessThan(Number value) {
        return q -> q.getValue().doubleValue() < value.doubleValue();
    }

    /**
     * creates a filter to lesser than the quantity measure
     *
     * @param quantity - the measure to be used in filter
     * @param <Q> the type of quantity
     * @return the Predicate lesser than this measure
     */
    public static <Q extends Quantity<Q>> Predicate<Quantity<Q>> isLessThan(Quantity<Q> quantity) {
        return q -> q.to(quantity.getUnit()).getValue().doubleValue() < quantity.getValue().doubleValue();
    }

    /**
     * creates a Filter to lesser or equals than number, ignoring units
     *
     * @param value - the value to be used in Predicate
     * @param <Q> the type of quantity
     * @return the Predicate lesser or equals than this number, ignoring units
     */
    public static <Q extends Quantity<Q>> Predicate<Quantity<Q>> isLessThanOrEqualTo(Number value) {
        return q -> q.getValue().doubleValue() <= value.doubleValue();
    }

    /**
     * creates a filter to lesser or equals than the quantity measure
     *
     * @param quantity - the measure to be used in filter
     * @param <Q> the type of quantity
     * @return the Predicate lesser or equals than this measure
     */
    public static <Q extends Quantity<Q>> Predicate<Quantity<Q>> isLessThanOrEqualTo(Quantity<Q> quantity) {
        return q -> q.to(quantity.getUnit()).getValue().doubleValue() <= quantity.getValue().doubleValue();
    }

    /**
     * creates a Filter to between, lesser or equals and greater or equals, than number, ignoring units
     *
     * @param min - the min value to be used in Predicate
     * @param max - the max value to be used in Predicate
     * @param <Q> the type of quantity
     * @return the Predicate lesser or equals than this number, ignoring units
     */
    public static <Q extends Quantity<Q>> Predicate<Quantity<Q>> isBetween(Number min, Number max) {
        Predicate<Quantity<Q>> minFilter = isGreaterThanOrEqualTo(min);
        Predicate<Quantity<Q>> maxFilter = isLessThanOrEqualTo(max);
        return minFilter.and(maxFilter);
    }

    /**
     * creates a filter to between, lesser or equals and greater or equals, than the quantity measure
     *
     * @param min - the min value to be used in Predicate
     * @param max - the max value to be used in Predicate
     * @param <Q> the type of quantity
     * @return the Predicate lesser or equals than this measure
     */
    public static <Q extends Quantity<Q>> Predicate<Quantity<Q>> isBetween(Quantity<Q> min, Quantity<Q> max) {
        return isGreaterThanOrEqualTo(min).and(isLessThanOrEqualTo(max));
    }

    /**
     * creates a function to group quantities by their {@link Unit unit}.
     *
     * @param <Q> the type of quantity
     * @return the Function of {@link Quantity} grouped by {@link Unit}
     */
    public static <Q extends Quantity<Q>> Function<Quantity<Q>, Unit<Q>> groupByUnit() {
        return Quantity::getUnit;
    }
}
