package edu.msudenver.mnewma12.as4.integration;

import edu.msudenver.mnewma12.as4.PiEstimator.Point;
import edu.msudenver.mnewma12.as4.cli.CLI;

import java.util.function.UnaryOperator;

public interface MFunction extends UnaryOperator<Double> {

    double expected();

    default boolean isUnderCurve(Point p) {
        boolean isUnder;
        double y = p.getY();
        double x = p.getX();
        double fAtX = apply(x);


//        CLI.echoLn("Evaluating point " + p);
//        CLI.echoLn(" f(" + x + ") = " + fAtX);
//        CLI.echoLn(" Point Y: " + y);

        if (y == 0) {
            isUnder = false;
        } else {
//            fAtX = apply(p.getX());
            if (y < 0) isUnder = fAtX < y;
            else isUnder = fAtX > y;
        }

//        CLI.echoLn(isUnder ? " INSIDE" : " OUTSIDE");
        return isUnder;
    }


    double maxYBetween0And(double n);

    double minYBetween0And(double n);
}
