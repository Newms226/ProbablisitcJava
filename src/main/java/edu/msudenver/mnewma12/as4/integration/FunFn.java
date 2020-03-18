package edu.msudenver.mnewma12.as4.integration;

public class FunFn implements MFunction {
    @Override
    public double expected() {
        return 176_290;
    }

    @Override
    public double maxYBetween0And(double n) {
        return 52_679;
    }

    @Override
    public double minYBetween0And(double n) {
        return -521;
    }

    @Override
    public Double apply(Double aDouble) {
        return (-5 * Math.pow(aDouble, 3)) + (582 * Math.pow(aDouble, 2)) - 521;
    }

    @Override
    public String toString() {
        return "-5x^³ + 582x² - 521";
    }
}
