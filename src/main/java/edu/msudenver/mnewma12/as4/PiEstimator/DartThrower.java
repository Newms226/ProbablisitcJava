package edu.msudenver.mnewma12.as4.PiEstimator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DartThrower {

    /*
    1. Calculate a point
    2. Decide if its in the circle


     */

    int inside, outside;

    Circle circle;

    DartThrower() {
        circle = new Circle(new Point(0, 0), 1);
        int n = 1000;
        ArrayList<Point> points = new ArrayList<>(n);
        for(Point p : points) { p = Point.getRandomPoint(); }

        points.stream()
                .map(circle::isInside);
    }

    void throwDart() {

    }

}
