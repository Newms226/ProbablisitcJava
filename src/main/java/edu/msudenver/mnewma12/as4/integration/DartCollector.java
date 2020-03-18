package edu.msudenver.mnewma12.as4.integration;

import edu.msudenver.mnewma12.as4.PiEstimator.Point;
import edu.msudenver.mnewma12.as4.core.EstimationBox;
import edu.msudenver.mnewma12.as4.tools.NumberTools;

import java.util.ArrayList;

import static edu.msudenver.mnewma12.as4.integration.Runner.DART_COUNT;
import static edu.msudenver.mnewma12.as4.integration.Runner.X_MAX;

public class DartCollector {

    ArrayList<Point> points = new ArrayList<>();

    int bellowCount;

    MFunction f;

    EstimationBox box;

    public DartCollector(MFunction f, EstimationBox box) {
        this.f = f;
        this.box = box;
    }

    void add(Point p) {
        points.add(p);
        if (f.isUnderCurve(p)) { bellowCount++; };
    }

    DartCollector combine(DartCollector o) {
        points.addAll(o.points);
        bellowCount += o.bellowCount;
        return this;
    }

    double estimate() {
        return (((double) bellowCount) / DART_COUNT) * X_MAX * box.getyRange();
    }

    @Override
    public String toString() {
        return "Dart Estimation"
                + "\nBellow Count: " + bellowCount + "/" + DART_COUNT
                + "(" + NumberTools.formatPercent(((double) bellowCount) / DART_COUNT) + ")"
                + "\nEstimated value: " + estimate()
                + "\nExpected value: " + f.expected();
    }
}
