package com.futurumgame.base.additionalDatatypes;

import com.futurumgame.base.enums.Notation;
import com.futurumgame.base.exceptions.UnitsFormatException;
import com.futurumgame.base.serialization.parsing.ParseResult;
import com.futurumgame.base.serialization.parsing.UnitsParseRule;
import com.futurumgame.base.util.ExceptionUtil;

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
    private static final Units InternalOne = new Units(DoubleOne, DoubleZero, false);
    private static final Units InternalTen = new Units(DoubleOne, DoubleOne, false);

    public static final Units PositiveInfinity = new Units(InternalPositiveInfinity);
    public static final Units NegativeInfinity = new Units(InternalNegativeInfinity);
    public static final Units NaN = new Units(InternalNaN);
    public static final Units Zero = new Units(InternalZero);
    public static final Units One = new Units(InternalOne);
    public static final Units Ten = new Units(InternalTen);
    private static final UnitsParseRule ParseRule = new UnitsParseRule();

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

    public void negate() {
        value = -value;
    }

    public void add(Units units) {
        boolean isUnneededAddition = checkIfUnneededAddition(units);

        if (!isUnneededAddition && !checkAndExecuteZeroAddition(units)) {
            executeAddition(units);
            normalize();
        }
    }

    public void subtract(Units units) {
        boolean isUnneededSubtraction = checkIfUnneededSubtraction(units);

        if (!isUnneededSubtraction && !checkAndExecuteZeroSubtraction(units)) {
            executeSubtraction(units);
            normalize();
        }
    }

    public void multiply(Units units) {
        if (evaluateToNaNIfTrue(units, u -> isNaN() || u.isNaN())) {
            return;
        }
        if (evaluateToZeroIfTrue(units, u -> isZero() || u.isZero())) {
            return;
        }
        if (evaluateToNegInfIfTrue(units,
                u -> checkForMultiplicationNegativeInfinity(u))) {
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
        value = Math.pow(value, 1 / root) * Math.pow(10.0, scaleDiff);
        scale -= scaleDiff;
        normalize();
    }

    public void log10() {
        if (value != DoubleOne) {
            value = Math.log10(value);
        }
        value *= scale;
        scale = DoubleZero;
        normalize();
    }

    public void log(double base) {
        if (value != DoubleOne) {
            value = (Math.log(value) / Math.log(base));
        }
        value *= (scale / Math.log10(base));
        scale = DoubleZero;
        normalize();
    }

    public boolean isBiggerThan(Units other) {
        if (isNegative() && !other.isNegative()) {
            return false;
        }
        if (!isNegative() && other.isNegative()) {
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
        if (isNegative() && !other.isNegative()) {
            return true;
        }
        if (!isNegative() && other.isNegative()) {
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

    public boolean isOne() {
        return Double.compare(value, DoubleOne) == 0 && Double.compare(scale, DoubleZero) == 0;
    }

    public boolean isNegOne() {
        return Double.compare(value, DoubleNegOne) == 0 && Double.compare(scale, DoubleZero) == 0;
    }

    public boolean isPositive() {
        return value > 0;
    }

    public boolean isNegative() {
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
        return value * Math.pow(10, scale);
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

    private double rescaleValue(double value, double firstScale, double secondScale) {
        return rescaleValue(value, firstScale - secondScale);
    }

    private double rescaleValue(double value, double rawScaleDiff) {
        int scaleDiff = (int) Math.abs(rawScaleDiff);
        return rescaleValue(value, scaleDiff);
    }

    private double rescaleValue(double value, int scaleDiff) {
        if (scaleDiff != 0) {
            value *= Math.pow(10, -scaleDiff);
        }
        return value;
    }

    private int calcScaleDiff(double rawValueDiff) {
        double scaleDiffRaw = Math.log10(Math.abs(rawValueDiff));
        return (int) (scaleDiffRaw + (scaleDiffRaw >= 0 ? 0 : -1));
    }

    private boolean checkIfUnneededAddition(Units unit) {
        boolean isUnneededAddition = false;

        isUnneededAddition = unit.isZero();

        isUnneededAddition = evaluateToNaNIfTrue(unit, u -> isNaN() || u.isNaN());

        isUnneededAddition = evaluateToNegInfIfTrue(unit,
                u -> checkAdditionUnitsForNegativeInfinity(u));

        isUnneededAddition = evaluateToPosInfIfTrue(unit,
                u -> checkAdditionUnitsForPositiveInfinity(u));

        isUnneededAddition = evaluateToZeroIfTrue(unit, u -> isInfinity() && u.isInfinity());

        return isUnneededAddition;
    }

    private boolean checkAdditionUnitsForPositiveInfinity(Units otherUnit) {
        boolean isPrimaryUnitPositiveInfinity = isPosInfinity() && !otherUnit.isNegInfinity();
        boolean isSecondaryUnitPositiveInfinity = !isNegInfinity() && otherUnit.isPosInfinity();

        return isPrimaryUnitPositiveInfinity || isSecondaryUnitPositiveInfinity;
    }

    private boolean checkAdditionUnitsForNegativeInfinity(Units otherUnit) {
        boolean isPrimaryUnitNegativeInfinity = isNegInfinity() && !otherUnit.isPosInfinity();
        boolean isSecondaryUnitNegativeInfinity = !isPosInfinity() && otherUnit.isNegInfinity();

        return isPrimaryUnitNegativeInfinity || isSecondaryUnitNegativeInfinity;
    }

    private boolean checkIfUnneededSubtraction(Units unit) {
        boolean isUnneededSubtraction = false;

        isUnneededSubtraction = unit.isZero();

        boolean finalIsUnneededSubtraction = isUnneededSubtraction;
        isUnneededSubtraction = evaluateToNaNIfTrue(unit, u -> checkForNaNEvaluation(u, finalIsUnneededSubtraction));

        boolean finalIsUnneededSubtraction1 = isUnneededSubtraction;
        isUnneededSubtraction = evaluateToNegInfIfTrue(unit, u -> checkSubtractionUnitsForNegativeInfinity(u) && !finalIsUnneededSubtraction1);

        boolean finalIsUnneededSubtraction2 = isUnneededSubtraction;
        isUnneededSubtraction = evaluateToPosInfIfTrue(unit, u -> checkSubtractionUnitsForPositiveInfinity(u) && !finalIsUnneededSubtraction2);

        boolean finalIsUnneededSubtraction3 = isUnneededSubtraction;
        isUnneededSubtraction = evaluateToZeroIfTrue(unit, u -> checkForInfinityEvaluation(u, finalIsUnneededSubtraction3));

        return isUnneededSubtraction;
    }

    private boolean checkForNaNEvaluation(Units unit, boolean enableEvaluation) {
        return (isNaN() || unit.isNaN()) && !enableEvaluation;
    }

    private boolean checkForInfinityEvaluation(Units unit, boolean enableEvaluation) {
        return isInfinity() && unit.isInfinity() && !enableEvaluation;
    }

    private boolean checkSubtractionUnitsForPositiveInfinity(Units otherUnit) {
        boolean isPrimaryUnitPositiveInfinity = isPosInfinity() && !otherUnit.isPosInfinity();
        boolean isSecondaryUnitPositiveInfinity = !isNegInfinity() && otherUnit.isNegInfinity();

        return isPrimaryUnitPositiveInfinity || isSecondaryUnitPositiveInfinity;
    }

    private boolean checkSubtractionUnitsForNegativeInfinity(Units otherUnit) {
        boolean isPrimaryUnitNegativeInfinity = isNegInfinity() && !otherUnit.isNegInfinity();
        boolean isSecondaryUnitNegativeInfinity = !isPosInfinity() && otherUnit.isPosInfinity();

        return isPrimaryUnitNegativeInfinity || isSecondaryUnitNegativeInfinity;
    }

    private boolean checkAndExecuteZeroAddition(Units addedUnit) {
        boolean isZero = isZero();

        if (isZero) {
            value = addedUnit.getValue();
            scale = addedUnit.getScale();
        }

        return isZero;
    }

    private void executeAddition(Units addedUnit) {
        if (scale > addedUnit.scale) {
            value += rescaleValue(addedUnit.value, scale, addedUnit.scale);
        } else if (scale < addedUnit.scale) {
            value = addedUnit.value + rescaleValue(value, scale, addedUnit.scale);
            scale = addedUnit.scale;
        } else {
            value += addedUnit.value;
        }
    }

    private boolean checkAndExecuteZeroSubtraction(Units subtractedValue) {
        boolean isZero = isZero();

        if (isZero) {
            value = -subtractedValue.getValue();
            scale = subtractedValue.getScale();
        }

        return isZero;
    }

    private void executeSubtraction(Units subtractedUnit) {
        if (scale > subtractedUnit.scale) {
            value -= rescaleValue(subtractedUnit.value, scale, subtractedUnit.scale);
        } else if (scale < subtractedUnit.scale) {
            value = subtractedUnit.value + rescaleValue(value, scale, subtractedUnit.scale);
            scale = subtractedUnit.scale;
        } else {
            value -= subtractedUnit.value;
        }
    }

    private boolean checkForMultiplicationNegativeInfinity(Units unit) {
        boolean isNegInfTimesPositiveValue = isNegInfinity() && unit.isBiggerThan(Zero);
        boolean isPosInfTimesNegativeValue = isPosInfinity() && unit.isSmallerThan(Zero);
        boolean isPositiveValueTimesNegInf = isBiggerThan(Zero) && unit.isNegInfinity();
        boolean isNegativeValueTimesPosInf = isSmallerThan(Zero) && unit.isPosInfinity();

        return isNegInfTimesPositiveValue || isPosInfTimesNegativeValue || isPositiveValueTimesNegInf || isNegativeValueTimesPosInf;
    }

    public static Units parse(String string) throws UnitsFormatException {
        ParseResult<Units> result = ParseResult.failResult();
        for (char c : string.toCharArray()) {
            result = ParseRule.next(c);
        }
        ParseRule.clearAll();
        if (!result.parseSuccess()) {
            ExceptionUtil.throwUnitsFormatException(string);
        }
        return result.getResult();
    }
}
