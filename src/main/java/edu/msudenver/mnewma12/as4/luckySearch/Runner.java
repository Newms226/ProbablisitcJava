package edu.msudenver.mnewma12.as4.luckySearch;

import edu.msudenver.mnewma12.as4.cli.CLI;

import java.util.ArrayList;

public class Runner {
    static final int ARRAY_RUN_COUNT = 10_000;

    public static void main(String[] args) {

        ArrayList<LuckySearch> searches = new ArrayList<>(ARRAY_RUN_COUNT);
        for (int i = 0; i < ARRAY_RUN_COUNT; i++) { searches.add(null); }

        Stats stats = searches.stream().parallel()
                .map(_null -> new LuckySearch())
                .map(LuckySearch::run)
                .collect(Stats::new, Stats::add, Stats::combine);

        CLI.echoLn(stats.toString());
    }
}
