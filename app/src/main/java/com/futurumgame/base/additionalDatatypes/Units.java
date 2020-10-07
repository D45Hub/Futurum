package com.futurumgame.base.additionalDatatypes;

import java.util.Objects;
import java.util.function.Predicate;

import com.futurumgame.base.enums.Notation;

public final class Units extends Number implements Comparable<Units> {
    private static final long serialVersionUID = -7295543336467767600L;

    public static final Units PositiveInfinity = new Units(0, Double.POSITIVE_INFINITY);
    public static final Units NegativeInfinity = new Units(0, Double.NEGATIVE_INFINITY);
    public static final Units NaN = new Units(0, Double.NaN);
    public static final Units Zero = new Units(0, 0);

    private static final long LongMaxPrecision = 10000000000L;
    private static final double DoubleMaxPrecision = 10;

    private double value;
    private double scale;

    public Units(long value, double scale) {
        this.value = value;
        this.scale = scale;
        normalize();
    }

    private Units(Units toCopy) {
        this.value = toCopy.value;
        this.scale = toCopy.scale;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public void add(Units units) {
        if (evaluateToNaNIfTrue(units, u -> isNaN() || u.isNaN())) {
            return;
        }
        if (evaluateToNegInfIfTrue(units, u -> isNegInfinity() && !u.isPosInfinity() || !isPosInfinity() && u.isNegInfinity())) {
            return;
        }
        if (evaluateToPosInfIfTrue(units, u -> isPosInfinity() && !u.isNegInfinity() || !isNegInfinity() && u.isPosInfinity())) {
            return;
        }
        if (evaluateToZeroIfTrue(units, u -> isInfinity() && u.isInfinity())) {
            return;
        }
        double scaleDiff = Math.abs(scale - units.scale);
        if (scale > units.scale) {
            value += units.value / (10 * scaleDiff);
        } else if (scale < units.scale) {
            value = units.value + value / (10 * scaleDiff);
            scale = units.scale;
        } else {
            value += units.value;
        }
        normalize();
    }

    public void subtract(Units units) {
        if (evaluateToNaNIfTrue(units, u -> isNaN() || u.isNaN())) {
            return;
        }
        if (evaluateToNegInfIfTrue(units, u -> isNegInfinity() && !u.isNegInfinity() || !isPosInfinity() && u.isPosInfinity())) {
            return;
        }
        if (evaluateToPosInfIfTrue(units, u -> isPosInfinity() && !u.isPosInfinity() || !isNegInfinity() && u.isNegInfinity())) {
            return;
        }
        if (evaluateToZeroIfTrue(units, u -> isInfinity() && u.isInfinity())) {
            return;
        }
        double scaleDiff = Math.abs(scale - units.scale);
        if (scale > units.scale) {
            value -= units.value / (10 * scaleDiff);
        } else if (scale < units.scale) {
            value = -units.value + value / (10 * scaleDiff);
            scale = units.scale;
        } else {
            value -= units.value;
        }
        normalize();
    }

    public void multiply(Units units) {
        if (evaluateToNaNIfTrue(units, u -> isNaN() || u.isNaN())) {
            return;
        }
        if (evaluateToZeroIfTrue(units, u -> isZero() || u.isZero())) {
            return;
        }
        if (evaluateToNegInfIfTrue(units, u -> isNegInfinity() && u.isBiggerThan(Zero) || isPosInfinity() && u.isSmallerThan(Zero)
                || isBiggerThan(Zero) && u.isNegInfinity() || isSmallerThan(Zero) && u.isPosInfinity())) {
            return;
        }
        if (evaluateToPosInfIfTrue(units, u -> isInfinity() || u.isInfinity())) {
            return;
        }
        value *= units.value;
        scale += units.scale;
        normalize();
    }

    public void divide(Units units) {
        if (evaluateToNaNIfTrue(units, u -> isNaN() || u.isNaN() || u.isZero())) {
            return;
        }
        if (evaluateToZeroIfTrue(units, u -> isZero() || u.isInfinity())) {
            return;
        }
        value /= units.value;
        scale -= units.scale;
        normalize();
    }

    public void pow(double exp) {
        value = Math.pow(value, exp);
        scale *= exp;
        normalize();
    }

    public void nRoot(double root) {
        if(Double.isInfinite(root)){
            value = 1;
            scale = 0;
        }
        value = Math.pow(value, 1 / root);
        scale /= root;
        normalize();
    }

    public void log10() {
        value = Math.log10(value) * scale;
        scale = 0;
    }

    public void log(double base) {
        value = (Math.log(value) / Math.log(base)) * (scale / Math.log10(base));
        scale = 0;
        normalize();
    }

    public boolean isBiggerThan(Units other) {
        if (scale > other.scale) {
            return true;
        }
        if (scale < other.scale) {
            return false;
        }
        return value > other.value;
    }

    public boolean isSmallerThan(Units other) {
        if (scale < other.scale) {
            return true;
        }
        if (scale > other.scale) {
            return false;
        }
        return value < other.value;
    }

    public boolean isInfinity() {
        return Double.isInfinite(scale) || Double.isInfinite(value);
    }

    public boolean isPosInfinity() {
        return isInfinity() && value > 0;
    }

    public boolean isNegInfinity() {
        return isInfinity() && value < 0;
    }

    public boolean isNaN() {
        return Double.isNaN(scale) || Double.isNaN(value);
    }

    public boolean isZero() {
        return Double.compare(value, Zero.value) == 0;
    }

    public Units copy() {
        return new Units(this);
    }

    @Override
    public int compareTo(Units o) {
        if (isSmallerThan(o)) {
            return -1;
        }
        if (isBiggerThan(o)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int intValue() {
        return (int) doubleValue();
    }

    @Override
    public long longValue() {
        return (long) doubleValue();
    }

    @Override
    public float floatValue() {
        return (float) doubleValue();
    }

    @Override
    public double doubleValue() {
        return Math.pow(value, scale);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Units units = (Units) o;
        return Double.compare(units.value, value) == 0 &&
                Double.compare(units.scale, scale) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, scale);
    }

    @Override
    public String toString() {
        return toString(Notation.Scientific);
    }

    public String toString(Notation notation) {
        return notation.applyNotationFormat(this);
    }

    private boolean evaluateToNaNIfTrue(Units units, Predicate<Units> predicate) {
        if (predicate.test(units)) {
            value = NaN.value;
            scale = NaN.scale;
            return true;
        }
        return false;
    }

    private boolean evaluateToNegInfIfTrue(Units units, Predicate<Units> predicate) {
        if (predicate.test(units)) {
            value = NegativeInfinity.value;
            scale = NegativeInfinity.scale;
            return true;
        }
        return false;
    }

    private boolean evaluateToPosInfIfTrue(Units units, Predicate<Units> predicate) {
        if (predicate.test(units)) {
            value = PositiveInfinity.value;
            scale = PositiveInfinity.scale;
            return true;
        }
        return false;
    }

    private boolean evaluateToZeroIfTrue(Units units, Predicate<Units> predicate) {
        if (predicate.test(units)) {
            value = Zero.value;
            scale = Zero.scale;
            return true;
        }
        return false;
    }

    private void normalize() {
        if (evaluateToNaNIfTrue(null, u -> isNaN())) {
            return;
        }
        if (evaluateToPosInfIfTrue(null, u -> isPosInfinity())) {
            return;
        }
        if (evaluateToNegInfIfTrue(null, u -> isNegInfinity())) {
            return;
        }
        if (evaluateToZeroIfTrue(null, u -> isZero())) {
            return;
        }
        int scaleDiff = (int) Math.log10(value);
        if (scaleDiff > 0) {
            value /= scaleDiff;
        } else if (scaleDiff < 0) {
            value *= -scaleDiff;//Faster than Math.abs(scaleDiff)
        }
        scale += scaleDiff;
    }
}
