package edu.msudenver.mnewma12.as4.luckySearch;

import edu.msudenver.mnewma12.as4.cli.CLI;
import edu.msudenver.mnewma12.as4.tools.NumberTools;

import java.util.ArrayList;

public class SearchRunner {

    static class Stats {
        int foundCount;

        int totalSearches;

        ArrayList<LuckySearch> searches;

        Stats() { searches = new ArrayList<>(); }

        void add(LuckySearch search) {
            searches.add(search);
            if (search.isFound()) foundCount++;
            totalSearches += search.getSearchCount();
        }

        Stats combine(Stats o) {
            foundCount += o.foundCount;
            totalSearches += o.totalSearches;
            searches.addAll(o.searches);
            return this;
        }

        double avgComparisons() { return ((double) totalSearches) / RUN_COUNT; }

        double percentFound() { return ((double) foundCount) / RUN_COUNT; }

    }

    static int RUN_COUNT = 100;

    ArrayList<LuckySearch> searches;

    SearchRunner() {
        searches = new ArrayList<>(RUN_COUNT);
        for (int i = 0; i < RUN_COUNT; i++) { searches.add(null); }
    }

    void run() {
        Stats stats = searches.stream().parallel()
                .map(_null -> new LuckySearch())
                .map(LuckySearch::run)
                .collect(Stats::new, Stats::add, Stats::combine);

        CLI.echoLn(
                "Ran " + RUN_COUNT + " searches."
                + "\nFound the key " + NumberTools.formatPercent(stats.percentFound()) + " of the time."
                + "\nTook an average of " + NumberTools.formatPercent(stats.avgComparisons()) + " comparisons."

        );
    }

}
