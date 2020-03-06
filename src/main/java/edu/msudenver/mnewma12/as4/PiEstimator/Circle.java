package edu.msudenver.mnewma12.as4.PiEstimator;

import edu.msudenver.mnewma12.as4.cli.CLI;

public class Circle {

    private final Point center;

    private final double r, rSquared;

    public Circle(Point center, double radius) {
        this.center = center;
        r = radius;
        rSquared = Math.pow(r, 2);
        CLI.echoLn("CIRCLE INIT. R: " + r + " R^2: " + rSquared);
    }

    /**
     * Note that we are NOT considering a point ON the line as being inside it.
     * @param p
     * @return
     */
    boolean isInside(Point p) {
        double d =  center.distanceSquared(p);
//        CLI.echoLn("d: " + d);
        return d < rSquared;
    }
}
