package edu.msudenver.mnewma12.as4.tools;

public class NumberTools {

    public static String formatPercent(double raw) {
        return Double.toString(raw * 100).substring(0, 4) + " %";
    }
}
