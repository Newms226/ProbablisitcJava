package edu.msudenver.mnewma12.as4.core;

import edu.msudenver.mnewma12.as4.PiEstimator.DartThrower;
import edu.msudenver.mnewma12.as4.cli.CLI;
import edu.msudenver.mnewma12.as4.luckySearch.LuckySearch;
import edu.msudenver.mnewma12.as4.luckySearch.Stats;
import edu.msudenver.mnewma12.as4.tools.NumberTools;

import java.util.ArrayList;

public class Runner {

    public static void main(String[] args) {
        randomArray();
    }

    public static void calculatePi() {
        int n = 100_000_000;
        DartThrower thrower = new DartThrower(n);

        double pi = thrower.run();
        CLI.echoLn("ESTIMATED PI AS: " + pi);
    }

    public static void testPrimes() {}

    public static final int ARRAY_RUN_COUNT = 10_000;

    public static void randomArray() {

        ArrayList<LuckySearch> searches = new ArrayList<>(ARRAY_RUN_COUNT);
        for (int i = 0; i < ARRAY_RUN_COUNT; i++) { searches.add(null); }

        Stats stats = searches.stream().parallel()
                .map(_null -> new LuckySearch())
                .map(LuckySearch::run)
                .collect(Stats::new, Stats::add, Stats::combine);

        CLI.echoLn(stats.toString());
    }

    public static void monteCarlo() {}

    public static void eightQueens() {}


}
