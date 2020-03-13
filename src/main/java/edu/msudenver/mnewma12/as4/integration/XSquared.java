package edu.msudenver.mnewma12.as4.integration;

import edu.msudenver.mnewma12.as4.PiEstimator.Point;
import edu.msudenver.mnewma12.as4.cli.CLI;

import static edu.msudenver.mnewma12.as4.integration.Runner.X_MAX;

public class XSquared implements MFunction {

    @Override
    public Double apply(Double x) {
        return Math.pow(x, 2);
    }

    /**
     * This is predicated on the range being (0, {@link @Runner.MONTEB_MAX_X}
     */
    public double expected() {
        return Math.pow(X_MAX, 3) / 3;
    }


    @Override
    public double maxYBetween0And(double n) { return apply(n); }

    @Override
    public double minYBetween0And(double n) {
        return 0;
    }

    @Override
    public String toString() {
        return "x^2";
    }



}
