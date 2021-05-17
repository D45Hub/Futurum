package com.futurumgame.base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.futurumgame.base.additionalDatatypes.Units;

public class UnitsTest {

    @Test
    public void testAddition() {
        Units simpleTestUnit = new Units(5, 0.0);
        Units simpleTestUnit2 = new Units(13, 0.0);
        Units simpleTestUnit3 = new Units(6, 2.0);
        Units simpleTestUnit4 = new Units(-5, 0.0);

        Units positiveInfiniteUnit = Units.PositiveInfinity.copy();
        Units negativeInfiniteUnit = Units.NegativeInfinity.copy();

        simpleTestUnit.add(simpleTestUnit2);
        assertEquals(new Units(18, 0.0), simpleTestUnit);

        simpleTestUnit2.add(simpleTestUnit4);
        assertEquals(new Units(8, 0.0), simpleTestUnit2);

        simpleTestUnit2.add(simpleTestUnit3);
        assertEquals(new Units(6.08, 2.0), simpleTestUnit2);

        positiveInfiniteUnit.add(simpleTestUnit);
        assertEquals(Units.PositiveInfinity, positiveInfiniteUnit);

        negativeInfiniteUnit.add(simpleTestUnit4);
        assertEquals(Units.NegativeInfinity, negativeInfiniteUnit);

        simpleTestUnit.add(Units.NaN);
        assertEquals(Units.NaN, simpleTestUnit);

        Units infiniteCancellationUnit = new Units(0, 0);
        infiniteCancellationUnit.add(Units.PositiveInfinity);
        infiniteCancellationUnit.add(Units.NegativeInfinity);
        assertEquals(Units.Zero, infiniteCancellationUnit);

    }

    @Test
    public void testSubtraction() {
        Units simpleTestUnit = new Units(5, 0.0);
        Units simpleTestUnit2 = new Units(13, 0.0);
        Units simpleTestUnit3 = new Units(-5, 0.0);

        simpleTestUnit2.subtract(simpleTestUnit);
        assertEquals(new Units(8, 0.0), simpleTestUnit2);

        simpleTestUnit3.subtract(simpleTestUnit3);
        assertEquals(Units.Zero, simpleTestUnit3);

        simpleTestUnit3.subtract(new Units(2, 1.0));
        assertEquals(new Units(-2, 1.0), simpleTestUnit3);

        simpleTestUnit3.subtract(Units.NaN);
        assertEquals(Units.NaN, simpleTestUnit3);

        simpleTestUnit.subtract(Units.Zero);
        assertEquals(new Units(5, 0.0), simpleTestUnit);

        simpleTestUnit.subtract(Units.NegativeInfinity);
        assertEquals(Units.PositiveInfinity, simpleTestUnit);

        simpleTestUnit.subtract(Units.PositiveInfinity);
        assertEquals(Units.Zero, simpleTestUnit);
    }

    @Test
    public void testMultiplication() {
        Units simpleTestUnit = new Units(5, 0.0);
        Units simpleTestUnit2 = new Units(13, 0.0);
        Units simpleTestUnit3 = new Units(-5, 0.0);

        simpleTestUnit.multiply(simpleTestUnit3);
        assertEquals(new Units(-25, 0.0), simpleTestUnit);

        simpleTestUnit2.multiply(new Units(5, 0.0));
        assertEquals(new Units(65, 0.0), simpleTestUnit2);

        simpleTestUnit.multiply(Units.Zero);
        assertEquals(Units.Zero, simpleTestUnit);

        simpleTestUnit2.multiply(Units.NaN);
        assertEquals(Units.NaN, simpleTestUnit2);

        simpleTestUnit3.multiply(Units.NegativeInfinity);
        assertEquals(Units.PositiveInfinity, simpleTestUnit3);

        simpleTestUnit3.multiply(Units.PositiveInfinity);
        assertEquals(Units.PositiveInfinity, simpleTestUnit3);

        simpleTestUnit3.multiply(Units.NegativeInfinity);
        assertEquals(Units.NegativeInfinity, simpleTestUnit3);
    }

    @Test
    public void testDivision() {
        Units simpleTestUnit = new Units(5, 0.0);
        Units simpleTestUnit2 = new Units(20, 0.0);
        Units simpleTestUnit3 = new Units(-5, 0.0);

        simpleTestUnit2.divide(simpleTestUnit);
        assertEquals(new Units(4, 0.0), simpleTestUnit2);

        simpleTestUnit.divide(simpleTestUnit3);
        assertEquals(new Units(-1, 0.0), simpleTestUnit);

        simpleTestUnit.divide(Units.PositiveInfinity);
        assertEquals(Units.Zero, simpleTestUnit);

        simpleTestUnit2.divide(Units.Zero);
        assertEquals(Units.NaN, simpleTestUnit2);

        simpleTestUnit3.divide(Units.NegativeInfinity);
        assertEquals(Units.Zero, simpleTestUnit3);

        simpleTestUnit.divide(Units.NaN);
        assertEquals(Units.NaN, simpleTestUnit);
    }

    @Test
    public void testPow() {
        Units simpleTestUnit = new Units(5, 0.0);
        Units simpleTestUnit2 = new Units(2, 0.0);
        Units simpleTestUnit3 = new Units(-5, 0.0);

        simpleTestUnit.pow(2);
        assertEquals(new Units(25, 0.0), simpleTestUnit);

        simpleTestUnit3.pow(3);
        assertEquals(new Units(-125, 0.0), simpleTestUnit3);

        simpleTestUnit2.pow(Double.POSITIVE_INFINITY);
        assertEquals(Units.NaN, simpleTestUnit2);

        simpleTestUnit.pow(Double.NEGATIVE_INFINITY);
        assertEquals(Units.Zero, simpleTestUnit);

        simpleTestUnit2.pow(5);
        assertEquals(Units.NaN, simpleTestUnit2);

        simpleTestUnit.pow(Double.NaN);
        assertEquals(Units.NaN, simpleTestUnit);
    }

    @Test
    public void testNthRoot() {
        Units simpleTestUnit = new Units(125, 0.0);
        Units simpleTestUnit2 = new Units(-125, 0.0);
        Units simpleTestUnit3 = new Units(4.0, 0.0);

        Units positiveInfinityUnit = Units.PositiveInfinity.copy();

        simpleTestUnit.nRoot(3.0);
        assertEquals(new Units(5, 0.0), simpleTestUnit);

        simpleTestUnit2.nRoot(3.0);
        assertEquals(Units.NaN, simpleTestUnit2);

        positiveInfinityUnit.nRoot(5.0);
        assertEquals(Units.NaN, positiveInfinityUnit);

        simpleTestUnit3.nRoot(-2.0);
        assertEquals(new Units(0.5, 0.0), simpleTestUnit3);

        simpleTestUnit3.nRoot(Double.NaN);
        assertEquals(Units.NaN, simpleTestUnit3);
    }

    @Test
    public void testLog10() {
        Units simpleTestUnit = new Units(10, 20.0);
        Units zeroUnit = new Units(0.0, 0.0);
        Units negativeUnit = new Units(-15,  1.0);

        Units positiveInfiniteUnit = Units.PositiveInfinity.copy();
        Units negativeInfiniteUnit = Units.NegativeInfinity.copy();
        Units nanUnit = Units.NaN.copy();

        simpleTestUnit.log10();
        zeroUnit.log10();
        negativeUnit.log10();

        positiveInfiniteUnit.log10();
        negativeInfiniteUnit.log10();
        nanUnit.log10();

        assertEquals(new Units(2.1, 1.0), simpleTestUnit);
        assertEquals(Units.NaN, zeroUnit);
        assertEquals(Units.NaN, negativeUnit);
        assertEquals(Units.PositiveInfinity, positiveInfiniteUnit);
        assertEquals(Units.NaN, negativeInfiniteUnit);
        assertEquals(Units.NaN, nanUnit);
    }

    @Test
    public void testLog() {
        double eValue = 2.718281828459045;
        Units simpleTestUnit = new Units(16, 0.0);
        Units zeroUnit = new Units(0.0, 0.0);
        Units negativeUnit = new Units(-15,  1.0);
        Units eTestUnit = new Units(eValue, 0.0);

        Units positiveInfiniteUnit = Units.PositiveInfinity.copy();
        Units negativeInfiniteUnit = Units.NegativeInfinity.copy();
        Units nanUnit = Units.NaN.copy();

        eTestUnit.pow(2.0);
        eTestUnit.log(eValue);
        assertEquals(new Units(2.0, 0.0), eTestUnit);

        simpleTestUnit.log(2.0);
        assertEquals(new Units(4.0, 0.0), simpleTestUnit);

        zeroUnit.log(5.0);
        assertEquals(Units.NaN, zeroUnit);

        negativeUnit.log(3.0);
        assertEquals(Units.NaN, negativeUnit);

        simpleTestUnit.log(-4.0);
        assertEquals(Units.NaN, simpleTestUnit);

        positiveInfiniteUnit.log(3.0);
        assertEquals(Units.PositiveInfinity, positiveInfiniteUnit);

        negativeInfiniteUnit.log(4.0);
        assertEquals(Units.NaN, negativeInfiniteUnit);

        nanUnit.log(7.0);
        assertEquals(Units.NaN, nanUnit);

        eTestUnit.log(Double.NaN);
        assertEquals(Units.NaN, eTestUnit);

        simpleTestUnit = new Units(16, 0.0);
        simpleTestUnit.log(Double.POSITIVE_INFINITY);

        assertEquals(Units.Zero, simpleTestUnit);
    }

    @Test
    public void testComparisons() {
        Units simpleTestUnit = new Units(15, 0.0);
        Units simpleTestUnit2 = new Units(-23, 0.0);
        Units simpleTestUnit3 = new Units(55, 0.0);
        Units simpleTestUnit4 = new Units(10, 2.0);
        Units simpleTestUnit5 = new Units(-10, 2.0);
        Units simpleTestUnit6 = new Units(-25, 0.0);

        Units positiveInfiniteUnit = Units.PositiveInfinity.copy();
        Units negativeInfiniteUnit = Units.NegativeInfinity.copy();
        Units nanUnit = Units.NaN.copy();
        Units zeroUnit = Units.Zero.copy();

        assertEquals(true, simpleTestUnit.isBiggerThan(simpleTestUnit2));
        assertEquals(false, simpleTestUnit2.isBiggerThan(simpleTestUnit));
        assertEquals(true, simpleTestUnit2.isBiggerThan(simpleTestUnit6));
        assertEquals(false, simpleTestUnit.isBiggerThan(simpleTestUnit3));
        assertEquals(true, simpleTestUnit3.isBiggerThan(simpleTestUnit));
        assertEquals(true, simpleTestUnit4.isBiggerThan(simpleTestUnit));
        assertEquals(true, simpleTestUnit6.isBiggerThan(simpleTestUnit5));
        assertEquals(true, simpleTestUnit4.isBiggerThan(simpleTestUnit6));
        assertEquals(false, simpleTestUnit5.isBiggerThan(simpleTestUnit));

        assertEquals(true, positiveInfiniteUnit.isBiggerThan(simpleTestUnit2));
        assertEquals(true, positiveInfiniteUnit.isBiggerThan(simpleTestUnit));
        assertEquals(false, simpleTestUnit4.isBiggerThan(positiveInfiniteUnit));
        assertEquals(false, simpleTestUnit6.isBiggerThan(positiveInfiniteUnit));

        assertEquals(false, negativeInfiniteUnit.isBiggerThan(simpleTestUnit2));
        assertEquals(false, negativeInfiniteUnit.isBiggerThan(simpleTestUnit));
        assertEquals(true, simpleTestUnit4.isBiggerThan(negativeInfiniteUnit));
        assertEquals(true, simpleTestUnit6.isBiggerThan(negativeInfiniteUnit));

        assertEquals(false, nanUnit.isBiggerThan(simpleTestUnit));
        assertEquals(true, nanUnit.isBiggerThan(simpleTestUnit2));

        assertEquals(true, zeroUnit.isBiggerThan(simpleTestUnit2));
        assertEquals(false, zeroUnit.isBiggerThan(simpleTestUnit));

        assertEquals(false, simpleTestUnit.isSmallerThan(simpleTestUnit2));
        assertEquals(true, simpleTestUnit2.isSmallerThan(simpleTestUnit));
        assertEquals(false, simpleTestUnit2.isSmallerThan(simpleTestUnit6));
        assertEquals(true, simpleTestUnit.isSmallerThan(simpleTestUnit3));
        assertEquals(false, simpleTestUnit3.isSmallerThan(simpleTestUnit));
        assertEquals(false, simpleTestUnit4.isSmallerThan(simpleTestUnit));
        assertEquals(false, simpleTestUnit6.isSmallerThan(simpleTestUnit5));
        assertEquals(false, simpleTestUnit4.isSmallerThan(simpleTestUnit6));
        assertEquals(true, simpleTestUnit5.isSmallerThan(simpleTestUnit));

        assertEquals(false, positiveInfiniteUnit.isSmallerThan(simpleTestUnit2));
        assertEquals(false, positiveInfiniteUnit.isSmallerThan(simpleTestUnit));
        assertEquals(true, simpleTestUnit4.isSmallerThan(positiveInfiniteUnit));
        assertEquals(true, simpleTestUnit6.isSmallerThan(positiveInfiniteUnit));

        assertEquals(true, negativeInfiniteUnit.isSmallerThan(simpleTestUnit2));
        assertEquals(true, negativeInfiniteUnit.isSmallerThan(simpleTestUnit));
        assertEquals(false, simpleTestUnit4.isSmallerThan(negativeInfiniteUnit));
        assertEquals(false, simpleTestUnit6.isSmallerThan(negativeInfiniteUnit));

        assertEquals(false, nanUnit.isSmallerThan(simpleTestUnit));
        assertEquals(false, nanUnit.isSmallerThan(simpleTestUnit2));

        assertEquals(false, zeroUnit.isSmallerThan(simpleTestUnit2));
        assertEquals(true, zeroUnit.isSmallerThan(simpleTestUnit));
    }

    @Test
    public void testSpecialValues() {
        Units simpleTestUnit = new Units(15, 0.0);
        Units simpleTestUnit2 = new Units(-23, 0.0);

        Units positiveInfiniteUnit = Units.PositiveInfinity.copy();
        Units negativeInfiniteUnit = Units.NegativeInfinity.copy();
        Units nanUnit = Units.NaN.copy();
        Units zeroUnit = Units.Zero.copy();

        assertEquals(false, simpleTestUnit.isInfinity());
        assertEquals(false, simpleTestUnit.isPosInfinity());
        assertEquals(false, simpleTestUnit.isNegInfinity());

        assertEquals(false, simpleTestUnit2.isInfinity());
        assertEquals(false, simpleTestUnit2.isPosInfinity());
        assertEquals(false, simpleTestUnit2.isNegInfinity());

        assertEquals(true, positiveInfiniteUnit.isInfinity());
        assertEquals(true, positiveInfiniteUnit.isPosInfinity());
        assertEquals(false, positiveInfiniteUnit.isNegInfinity());

        assertEquals(true, negativeInfiniteUnit.isInfinity());
        assertEquals(false, negativeInfiniteUnit.isPosInfinity());
        assertEquals(true, negativeInfiniteUnit.isNegInfinity());

        assertEquals(false, nanUnit.isInfinity());
        assertEquals(false, nanUnit.isPosInfinity());
        assertEquals(false, nanUnit.isNegInfinity());
        assertEquals(true, nanUnit.isNaN());

        assertEquals(false, zeroUnit.isInfinity());
        assertEquals(false, zeroUnit.isPosInfinity());
        assertEquals(false, zeroUnit.isNegInfinity());
        assertEquals(true, zeroUnit.isZero());
    }

    @Test
    public void testAbsValues() {
        Units simpleTestUnit = new Units(15, 0.0);
        Units simpleTestUnit2 = new Units(-23, 0.0);

        Units positiveInfiniteUnit = Units.PositiveInfinity.copy();
        Units negativeInfiniteUnit = Units.NegativeInfinity.copy();

        Units zeroUnit = Units.Zero.copy();

        assertEquals(true, simpleTestUnit.isPositive());
        assertEquals(false, simpleTestUnit.isNegative());

        assertEquals(false, simpleTestUnit2.isPositive());
        assertEquals(true, simpleTestUnit2.isNegative());

        assertEquals(true, positiveInfiniteUnit.isPositive());
        assertEquals(false, positiveInfiniteUnit.isNegative());

        assertEquals(false, negativeInfiniteUnit.isPositive());
        assertEquals(true, negativeInfiniteUnit.isNegative());

        assertEquals(false, zeroUnit.isPositive());
        assertEquals(false, zeroUnit.isNegative());
    }

    @Test
    public void testCopy() {
        Units simpleTestUnit = new Units(15, 0.0);
        Units simpleTestUnit2 = new Units(-23, 0.0);

        Units positiveInfiniteUnit = Units.PositiveInfinity.copy();
        Units negativeInfiniteUnit = Units.NegativeInfinity.copy();

        Units zeroUnit = Units.Zero.copy();

        assertEquals(new Units(15, 0.0), simpleTestUnit.copy());
        assertEquals(new Units(-23, 0.0), simpleTestUnit2.copy());
        assertEquals(Units.PositiveInfinity, positiveInfiniteUnit.copy());
        assertEquals(Units.NegativeInfinity, negativeInfiniteUnit.copy());
        assertEquals(Units.Zero, zeroUnit.copy());
    }

    @Test
    public void testValueEvaluation() {
        Units simpleTestUnit = new Units(15, 1.0);
        Units simpleTestUnit2 = new Units(-23, 0.0);
        Units zeroUnit = new Units(0.0, 0.0);

        assertEquals(150, simpleTestUnit.intValue());
        assertEquals(-23, simpleTestUnit2.intValue());
        assertEquals(0, zeroUnit.intValue());
    }

}
