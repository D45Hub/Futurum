package com.futurumgame.base.additionalDatatypes;

import java.io.Serializable;
import java.util.function.Predicate;

import com.futurumgame.base.enums.Notation;

public final class Units extends Number implements Comparable<Units>, Serializable {

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
        if (evaluateToNaNIfTrue(u-> {return this.isNaN() || u.isNaN();}, units)) {
            return;
        }
        if (isNegInfinity() && !units.isPosInfinity() || !isPosInfinity() && units.isNegInfinity()) {
            value = NegativeInfinity.value;
            scale = NegativeInfinity.scale;
            return;
        }
        if (isInfinity() && units.isInfinity()) {
            value = Zero.value;
            scale = Zero.scale;
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

        normalize();
    }

    public void multiply(Units units) {
        normalize();
    }

    public void divide(Units units) {
        normalize();
    }

    public void pow(double exp) {
        normalize();
    }

    public void nRoot(double exp) {
        normalize();
    }

    public void log10() {
        log(10);
    }

    public void log(double base) {
        normalize();
    }

    public boolean isInfinity() {
        return Double.isInfinite(scale);
    }

    public boolean isPosInfinity() {
        return isInfinity() && scale > 0;
    }

    public boolean isNegInfinity() {
        return isInfinity() && scale < 0;
    }

    public boolean isNaN() {
        return Double.isNaN(scale);
    }


    public boolean isZero() {
        return value == Zero.value && scale == Zero.scale;
    }

    public Units copy() {
        return new Units(this);
    }

    @Override
    public int compareTo(Units o) {
        throw new RuntimeException();
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
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

    private boolean evaluateToNegInfIfTrue(Units units, Predicate<Units> predicate){
        if(predicate.test(units)){
            value = NegativeInfinity.value;
            scale = NegativeInfinity.scale;
            return true;
        }
        return false;
    }

    private boolean evaluateToPosInfIfTrue(Units units, Predicate<Units> predicate){
        if(predicate.test(units)){
            value = PositiveInfinity.value;
            scale = PositiveInfinity.scale;
            return true;
        }
        return false;
    }

    private void normalize() {

    }
}
