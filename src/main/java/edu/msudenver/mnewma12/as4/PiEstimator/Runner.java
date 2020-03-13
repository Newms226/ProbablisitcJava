package edu.msudenver.mnewma12.as4.PiEstimator;

import edu.msudenver.mnewma12.as4.cli.CLI;

public class Runner {

    public static void main(String[] args) {
        int n = 100_000_000;
        DartThrower thrower = new DartThrower(n);

        double pi = thrower.run();
        CLI.echoLn("ESTIMATED PI AS: " + pi);
    }

}
