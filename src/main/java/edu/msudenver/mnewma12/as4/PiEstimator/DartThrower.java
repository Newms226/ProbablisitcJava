package edu.msudenver.mnewma12.as4.PiEstimator;

import edu.msudenver.mnewma12.as4.cli.CLI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class DartThrower {

    /*
    1. Calculate a point
    2. Decide if its in the circle


     */

    final double n;

    final Circle circle;

    private final ArrayList<Point> points;

    public DartThrower(int n) {
        circle = new Circle(new Point(0, 0), 1);
        this.n = n;
        points = new ArrayList<>(n);
        for(int i = 0; i < n; i++) { points.add( null ); }
    }

    public double run() {
        CLI.echoLn("Estimating pi...");
        double insideCount = points.stream()
                .map(_null -> Point.getRandomPoint())
                .map(circle::isInside) // would it be faster w/o an obj? just a fn?
                .filter(inside -> inside)
                .count();
        return (insideCount / n) * 4;
    }

}
