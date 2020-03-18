package edu.msudenver.mnewma12.as4.PiEstimator;

import edu.msudenver.mnewma12.as4.cli.CLI;
import edu.msudenver.mnewma12.as4.tools.NumberTools;

public class Runner {

    public static void main(String[] args) {
//        StringBuffer buf = new StringBuffer();
//        for (int i = 1_000; i <= 100_000_000 ; i *= 10) {
//            DartThrower thrower = new DartThrower(i);
//            double pi = thrower.run();
//            buf.append(i + "," + pi + "," + NumberTools.percentDifference(Math.PI, pi) + "\n");
//        }
//        CLI.echoLn(buf.toString());
//        CLI.echoLn(Math.PI);

        DartThrower thrower = new DartThrower(100_000);
        long start = System.nanoTime();
        double pi = thrower.run();
        long end = System.nanoTime();
        CLI.echoLn("TOOK: " + NumberTools.formatNanoseconds(end - start));
    }


}
