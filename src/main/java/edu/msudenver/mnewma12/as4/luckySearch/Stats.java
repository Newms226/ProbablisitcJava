package edu.msudenver.mnewma12.as4.luckySearch;

import edu.msudenver.mnewma12.as4.tools.NumberTools;

import java.util.ArrayList;

import static edu.msudenver.mnewma12.as4.luckySearch.Runner.ARRAY_RUN_COUNT;

public class Stats {

    int foundCount;

    int foundSearchCount;

    int totalSearches;

    ArrayList<LuckySearch> searches;

    public Stats() { searches = new ArrayList<>(); }

    public void add(LuckySearch search) {
        searches.add(search);
        if (search.isFound()) {
            foundCount++;
            foundSearchCount += search.getSearchCount();
        }
        totalSearches += search.getSearchCount();
    }

    public Stats combine(Stats o) {
        foundCount += o.foundCount;
        totalSearches += o.totalSearches;
        foundSearchCount += o.foundSearchCount;
        searches.addAll(o.searches);
        return this;
    }

    public double avgComparisons() { return ((double) totalSearches) / ARRAY_RUN_COUNT; }

    public double percentFound() { return ((double) foundCount) / ARRAY_RUN_COUNT; }

    public double avgComparisonsWhenFound() {
        return ((double) foundSearchCount) / ARRAY_RUN_COUNT;
    }

    @Override
    public String toString() {
        return "Ran " + ARRAY_RUN_COUNT + " searches."
           + "\nFound the key " + NumberTools.formatPercent(percentFound()) + " of the time."
           + "\nTook an average of " + ((int) avgComparisons()) + " comparisons."
           + "\nWhen the key was found, it took an average of " + ((int) avgComparisonsWhenFound()) + " comparisons.";
    }
}
