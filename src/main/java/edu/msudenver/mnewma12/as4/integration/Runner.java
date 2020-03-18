package edu.msudenver.mnewma12.as4.integration;

import edu.msudenver.mnewma12.as4.PiEstimator.Point;
import edu.msudenver.mnewma12.as4.cli.CLI;
import edu.msudenver.mnewma12.as4.core.EstimationBox;
import edu.msudenver.mnewma12.as4.tools.NumberTools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.stream.Stream;

public class Runner {

    public static void main(String[] args) {
        runAndSave("/Users/michaelnewman/Semester/CS4050/algs-as3/logs/latest.txt");
//        CLI.echoLn( monteCarloA( new FunFn() ) );
    }

    static StringBuffer buf = new StringBuffer();
    static int RUN_COUNT = 10;

    static String CSV_HEADERS = "Run,Dart Value,Dart Time,Mean Value,Mean Time,Trapezoid Value,Trapezoid Time";

    static void runAndSave(String file) {
        buf.append("Dart Count: " + DART_COUNT + "\n");
        buf.append("Random Count: " + MONTEB_RAN_COUNT + "\n");
        buf.append("Used trapezoid count: " + TRAPEZOID_N + "\n");

        MFunction f = new XSquared();
//        run(f);
//        f = new FunFn();
        run(f);

        CLI.echoLn(buf.toString());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(buf.toString());
            writer.flush();
        } catch (IOException e) {
            CLI.fail("Failed to write to file", e);
        }
    }

    static void run(MFunction f) {
        buf.append("\n\n" + f + "\n##############\n" + CSV_HEADERS + "\n");
        for (int i = 0; i < RUN_COUNT; i++) {
            buf.append(i + ",");
            monteCarloA(f);
            buf.append(",");
            monteCarloB(f);
            buf.append(",");
            trapezoid(f);
            buf.append("\n");
        }
    }

    static int DART_COUNT = 100_000;

    public static String monteCarloA(final MFunction f) {
        long start = System.nanoTime();

        final EstimationBox box = new EstimationBox(f, 0 , 10);
        DartCollector results = Stream
                .generate(box::getRandomPoint)
                .limit(DART_COUNT)
//                .parallel()
                .collect(
                    () -> new DartCollector(f, box),
                    DartCollector::add,
                    DartCollector::combine);

        long end = System.nanoTime();

        CLI.echoLn(results);

        buf.append(results.estimate() + "," + (end - start));

        return toString(f, results, results.estimate(), end - start);
    }

    static final int MONTEB_RAN_COUNT = 1_000;

    static final double X_MAX = 10;

    public static String monteCarloB(final MFunction f) {
        long start = System.nanoTime();

        EstimationBox box = new EstimationBox(f, 0, 10);
        MeanEstimation estimation = Stream
                .generate(box::ranX)
                .limit(MONTEB_RAN_COUNT)
                .parallel()
                .collect(
                    () -> new MeanEstimation(f),
                    MeanEstimation::add,
                    MeanEstimation::combine
                );

        long end = System.nanoTime();



        buf.append(estimation.estimate() + "," + (end - start));

        return toString(f, estimation, estimation.estimate(), end - start);
    }

    static int TRAPEZOID_N = 100;

    public static String trapezoid(final MFunction f) {
        long start = System.nanoTime();
        TrapezoidBox box = new TrapezoidBox(f, 0, 10, TRAPEZOID_N);
        box.evaluate();
        long end = System.nanoTime();

        buf.append(box.evaluation + "," + (end - start));

        return toString(f, box, box.evaluation, end - start);
    }

    private static void print(MFunction f, Object toPrint, double result, long time) {
        CLI.echoLn(toString(f, toPrint, result, time) + "\n");
    }

    private static String toString(MFunction f, Object toPrint, double result, long time) {
        return new StringBuffer(toPrint.toString())
                .append("\nTook ").append(NumberTools.formatNanoseconds(time))
                .append("\nWithin ").append(NumberTools.percentDifferenceAsString(f.expected(), result))
                .toString();
    }
}
