package edu.msudenver.mnewma12.as4.PiEstimator;

import edu.msudenver.mnewma12.as4.cli.CLI;

import java.util.Objects;

public class Point {

    public static void main(String[] args) {
        Point center = new Point(0, 0);
        Point o = new Point(5, 0);
        center.distanceSquared(o);
    }

    static double getRandom() { return Math.random() * 2 - 1; }

    static Point getRandomPoint() {
        Point p = new Point(getRandom(), getRandom());
//        CLI.echoLn("RANDOM POINT: " + p);
        return p;
    }

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceSquared(Point o) {
        double xdelt = Math.pow(x - o.x, 2);
        double ydelt = Math.pow(y - o.y, 2);
//        CLI.echoLn(xdelt + "");
//        CLI.echoLn(ydelt + "");
        return xdelt + ydelt;
    }

    // bean spec?

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
