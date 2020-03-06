package edu.msudenver.mnewma12.as4.PiEstimator;

public class Circle {

    private Point center;

    private final double r, rSquared;

    public Circle(Point center, double radius) {
        this.center = center;
        r = radius;
        rSquared = Math.pow(r, 2);
    }

    /**
     * Note that we are NOT considering a point ON the line as being inside it.
     * @param p
     * @return
     */
    boolean isInside(Point p) {
        return p.distanceSquared(p) < rSquared;
    }
}
