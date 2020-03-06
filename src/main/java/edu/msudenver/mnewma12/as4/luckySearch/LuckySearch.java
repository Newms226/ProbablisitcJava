package edu.msudenver.mnewma12.as4.luckySearch;

import java.util.Random;

public class LuckySearch {

    static int MAX_SEARCHES = 5_000;

    static int N = 1_000;

    static int[] genArray(int n) {
        int max = 10_000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = (int) (Math.random() * max * 2) - max;
        }
        return a;
    }

    private int[] a;
    private Random random;
    private int key;
    private int searchCount;
    private boolean found;

    public LuckySearch() {
        a = genArray(N);
        random = new Random();
    }

    private int ran() { return random.nextInt(N); }

    private boolean search(int key) {
        int i = ran();
        return a[i] == key;
    }

    public LuckySearch run() {
        key = ran();

        for(searchCount = 0; searchCount < MAX_SEARCHES; searchCount++){
            if (found = search(key)) break;
        }

        return this;
    }

    // Getters

    public int getKey() {
        return key;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public boolean isFound() {
        return found;
    }
}
