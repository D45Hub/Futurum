package com.futurumgame.base.additionalDatatypes;

import com.futurumgame.base.enums.Notation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;
import java.util.function.Predicate;

public final class Units extends Number implements Comparable<Units> {
    private static final long serialVersionUID = -7295543336467767600L;

    private static final double DoubleNaN = Double.NaN;
    private static final double DoublePosInfinity = Double.POSITIVE_INFINITY;
    private static final double DoubleNegInfinity = Double.NEGATIVE_INFINITY;
    private static final double DoubleZero = 0.0;
    private static final double DoubleOne = 1.0;
    private static final double DoubleNegOne = -1.0;

    private static final Units InternalPositiveInfinity = new Units(DoublePosInfinity, DoublePosInfinity, false);
    private static final Units InternalNegativeInfinity = new Units(DoubleNegInfinity, DoublePosInfinity, false);
    private static final Units InternalNaN = new Units(DoubleNaN, DoubleNaN, false);
    private static final Units InternalZero = new Units(DoubleZero, DoubleZero, false);
    private static final Units InternalOne = new Units(DoubleOne, DoubleZero,false);
    private static final Units InternalTen = new Units(DoubleOne, DoubleOne,false);

    public static final Units PositiveInfinity = new Units(InternalPositiveInfinity);
    public static final Units NegativeInfinity = new Units(InternalNegativeInfinity);
    public static final Units NaN = new Units(InternalNaN);
    public static final Units Zero = new Units(InternalZero);
    public static final Units One = new Units(InternalOne);
    public static final Units Ten = new Units(InternalTen);

    private double value;
    private double scale;

    public Units(double value, double scale) {
        this(value, scale, true);
    }

    private Units(double value, double scale, boolean normalize) {
        this.value = value;
        this.scale = scale;
        if (normalize) {
            normalize();
        }
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

    public void negate(){
        value =-value;
    }

    public void add(Units units) {
        if(units.isZero()){
            return;
        }
        if (evaluateToNaNIfTrue(units, u -> isNaN() || u.isNaN())) {
            return;
        }
        if (evaluateToNegInfIfTrue(units,
                u -> isNegInfinity() && !u.isPosInfinity() || !isPosInfinity() && u.isNegInfinity())) {
            return;
        }
        if (evaluateToPosInfIfTrue(units,
                u -> isPosInfinity() && !u.isNegInfinity() || !isNegInfinity() && u.isPosInfinity())) {
            return;
        }
        if (evaluateToZeroIfTrue(units, u -> isInfinity() && u.isInfinity())) {
            return;
        }
        if(isZero()){
            value = units.getValue();
            scale = units.getScale();
            return;
        }
        if (scale > units.scale) {
            value += rescaleValue(units.value, scale, units.scale);
        } else if (scale < units.scale) {
            value = units.value+rescaleValue(value, scale, units.scale);
            scale = units.scale;
        } else {
            value += units.value;
        }
        normalize();
    }

    public void subtract(Units units) {
        if(units.isZero()){
            return;
        }
        if (evaluateToNaNIfTrue(units, u -> isNaN() || u.isNaN())) {
            return;
        }
        if (evaluateToNegInfIfTrue(units,
                u -> isNegInfinity() && !u.isNegInfinity() || !isPosInfinity() && u.isPosInfinity())) {
            return;
        }
        if (evaluateToPosInfIfTrue(units,
                u -> isPosInfinity() && !u.isPosInfinity() || !isNegInfinity() && u.isNegInfinity())) {
            return;
        }
        if (evaluateToZeroIfTrue(units, u -> isInfinity() && u.isInfinity())) {
            return;
        }
        if(isZero()){
            value = -units.getValue();
            scale = units.getScale();
            return;
        }
        if (scale > units.scale) {
            value -= rescaleValue(units.value, scale, units.scale);
        } else if (scale < units.scale) {
            value= units.value+ rescaleValue(value, scale, units.scale);
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
        if (evaluateToNegInfIfTrue(units,
                u -> isNegInfinity() && u.isBiggerThan(Zero) || isPosInfinity() && u.isSmallerThan(Zero)
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
        if (Double.isInfinite(root)) {
            value = InternalOne.value;
            scale = InternalOne.scale;
            return;
        }
        scale /= root;
        double scaleDiff = scale - Math.floor(scale);
        value = Math.pow(value, 1 / root)*Math.pow(10.0 ,scaleDiff);
        scale-=scaleDiff;
        normalize();
    }

    public void log10() {
        if(value != DoubleOne) {
            value = Math.log10(value);
        }
         value*= scale;
        scale = DoubleZero;
        normalize();
    }

    public void log(double base) {
        if(value != DoubleOne) {
            value = (Math.log(value) / Math.log(base));
        }
        value*= (scale / Math.log10(base));
        scale = DoubleZero;
        normalize();
    }

    public boolean isBiggerThan(Units other) {
        if(isNegative() && !other.isNegative()){
            return false;
        }
        if(!isNegative() && other.isNegative()){
            return true;
        }
        boolean valuesAreNegative = isNegative();
        if (scale > other.scale) {
            return valuesAreNegative ^ true;
        }
        if (scale < other.scale) {
            return valuesAreNegative || false;
        }
        return value > other.value;
    }

    public boolean isSmallerThan(Units other) {
        if(isNegative() && !other.isNegative()){
            return true;
        }
        if(!isNegative() && other.isNegative()){
            return false;
        }
        boolean valuesAreNegative = isNegative();
        if (scale < other.scale) {
            return valuesAreNegative ^ true;
        }
        if (scale > other.scale) {
            return valuesAreNegative || false;
        }
        return value < other.value;
    }

    public boolean isInfinity() {
        return Double.isInfinite(scale) || Double.isInfinite(value);
    }

    public boolean isPosInfinity() {
        return isInfinity() && isPositive();
    }

    public boolean isNegInfinity() {
        return isInfinity() && isNegative();
    }

    public boolean isNaN() {
        return Double.isNaN(scale) || Double.isNaN(value);
    }

    public boolean isZero() {
        return Double.compare(value, DoubleZero) == 0;
    }

    public boolean isOne() {return Double.compare(value, DoubleOne) == 0 && Double.compare(scale, DoubleZero) == 0; }

    public boolean isNegOne(){ return Double.compare(value, DoubleNegOne) == 0 && Double.compare(scale, DoubleZero) == 0;}

    public boolean isPositive(){
        return value > 0;
    }

    public boolean isNegative(){
        return value < 0;
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
        return value*Math.pow(10, scale);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Units units = (Units) o;
        return Double.compare(units.value, value) == 0 && Double.compare(units.scale, scale) == 0;
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
            value = InternalNaN.value;
            scale = InternalNaN.scale;
            return true;
        }
        return false;
    }

    private boolean evaluateToPosInfIfTrue(Units units, Predicate<Units> predicate) {
        if (predicate.test(units)) {
            value = InternalPositiveInfinity.value;
            scale = InternalPositiveInfinity.scale;
            return true;
        }
        return false;
    }

    private boolean evaluateToNegInfIfTrue(Units units, Predicate<Units> predicate) {
        if (predicate.test(units)) {
            value = InternalNegativeInfinity.value;
            scale = InternalNegativeInfinity.scale;
            return true;
        }
        return false;
    }

    private boolean evaluateToZeroIfTrue(Units units, Predicate<Units> predicate) {
        if (predicate.test(units)) {
            value = InternalZero.value;
            scale = InternalZero.scale;
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
        int scaleDiff = calcScaleDiff(value);
        value = rescaleValue(value, scaleDiff);
        scale += scaleDiff;
    }

    private double rescaleValue(double value, double firstScale, double secondScale){
        return rescaleValue(value, firstScale-secondScale);
    }

    private double rescaleValue(double value, double rawScaleDiff){
        int scaleDiff = (int)Math.abs(rawScaleDiff);
        return rescaleValue(value,scaleDiff);
    }

    private double rescaleValue(double value, int scaleDiff){
        if(scaleDiff != 0){
            value *= Math.pow(10,-scaleDiff);
        }
        return value;
    }

    private int calcScaleDiff(double rawValueDiff){
        double scaleDiffRaw = Math.log10(Math.abs(rawValueDiff));
        return (int)(scaleDiffRaw+(scaleDiffRaw >= 0 ? 0:-1));
    }
}
