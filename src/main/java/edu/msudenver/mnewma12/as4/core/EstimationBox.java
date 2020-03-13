package edu.msudenver.mnewma12.as4.core;

import edu.msudenver.mnewma12.as4.PiEstimator.Point;
import edu.msudenver.mnewma12.as4.integration.MFunction;

public class EstimationBox {

    static double yRange(double yMin, double yMax) {
        return Point.distance(0, yMin, 0, yMax);
    }

    double xMin, xMax, yMin, yMax, yRange;

    MFunction f;

    public EstimationBox(MFunction f, double xMin, double xMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.f = f;

        yMax = f.maxYBetween0And(xMax);
        yMin = f.minYBetween0And(xMin);

        yRange = yRange(yMin, yMax);
    }

    public double ranX() { return Math.random() * xMax; }

    public double ranY() {
        return Math.random() * yRange + yMin;
    }

    public Point getRandomPoint() {
        return new Point(ranX(), ranY());
    }

    public double getxMin() {
        return xMin;
    }

    public double getxMax() {
        return xMax;
    }

    public double getyMin() {
        return yMin;
    }

    public double getyMax() {
        return yMax;
    }

    public double getyRange() {
        return yRange;
    }
}
