package edu.msudenver.mnewma12.as4.integration;

public class FunFn implements MFunction {
    @Override
    public double expected() {
        return 7.8466942;
    }

    @Override
    public double maxYBetween0And(double n) {
        return 8;
    }

    @Override
    public double minYBetween0And(double n) {
        return -5;
    }

    @Override
    public Double apply(Double aDouble) {
        return aDouble * Math.sin(aDouble);
    }

    @Override
    public String toString() {
        return "x * sin(x)";
    }
}
