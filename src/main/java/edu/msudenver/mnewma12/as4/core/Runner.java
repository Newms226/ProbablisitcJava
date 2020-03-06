package edu.msudenver.mnewma12.as4.core;

import edu.msudenver.mnewma12.as4.PiEstimator.DartThrower;
import edu.msudenver.mnewma12.as4.cli.CLI;

public class Runner {

    public static void main(String[] args) {
        calculatePi();
    }

    public static void calculatePi() {
        int n = 100_000_000;
        DartThrower thrower = new DartThrower(n);

        double pi = thrower.run();
        CLI.echoLn("ESTIMATED PI AS: " + pi);
    }

    public static void testPrimes() {}

    public static void randomArray() {}

    public static void monteCarlo() {}

    public static void eightQueens() {}


}
