package edu.msudenver.mnewma12.as4.integration;

import edu.msudenver.mnewma12.as4.PiEstimator.Point;
import edu.msudenver.mnewma12.as4.cli.CLI;
import edu.msudenver.mnewma12.as4.core.EstimationBox;

import java.util.Arrays;

public class TrapezoidBox extends EstimationBox {

    XIntervals intervals;

    MFunction f;

    double evaluation;

    /**
     * This constructor assumes that the xMin is 0 atm // TODO
     * @param f
     * @param xMin
     * @param xMax
     * @param n how many intervals to form
     */
    public TrapezoidBox(MFunction f, double xMin, double xMax, int n) {
        super(f, xMin, xMax);

        intervals = new XIntervals(xMin, xMax, n);
        this.f = f;
//        CLI.echoLn("Generated intervals: " + intervals);
    }

    public double evaluate() {
        evaluation = intervals.stream()
                .parallel()
                .mapToDouble(this::evaluateInterval)
                .sum();

        return evaluation;
    }

    double evaluateInterval(Point interval) {
        double avg = (f.apply(interval.getX()) + f.apply(interval.getY())) / 2;
        return avg * intervals.size;
    }

    @Override
    public String toString() {
        return "Trapezoid Method"
//           + "\nEvaluating " + f
           + "\nExpected: " + f.expected()
           + "\nEvaluated: " + evaluation;
    }
}
