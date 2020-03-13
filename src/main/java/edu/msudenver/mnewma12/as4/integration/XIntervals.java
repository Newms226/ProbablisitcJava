package edu.msudenver.mnewma12.as4.integration;

import edu.msudenver.mnewma12.as4.PiEstimator.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class XIntervals {

    ArrayList<Point> intervals;

    double xMin, xMax, size;

    int n;

    XIntervals(double xMin, double xMax, int n) {
        this.xMax = xMax;
        this.xMin = xMin;
        this.n = n;

        size = xMax / n;

        intervals = new ArrayList<>(n);
        double start = 0, end = 0;
        for (int i = 0; i < n - 1; i++) {
            start = i * size;
            end = start + size;
            intervals.add(new Point(start, end));
        }

        intervals.add(new Point(end, xMax));
    }

    public Stream<Point> stream() { return intervals.stream(); }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer("{");

        for (int i = 0; i < n; i++) {
            buf.append(intervals.get(i));
            if (i != n - 1) buf.append(", ");
        }

        buf.append("}");
        return buf.toString();
    }
}
