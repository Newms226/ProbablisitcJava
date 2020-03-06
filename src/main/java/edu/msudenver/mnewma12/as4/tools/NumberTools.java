package edu.msudenver.mnewma12.as4.tools;

public class NumberTools {

    public static String formatPercent(double raw) {
        String percent = Double.toString(raw * 100);
        int min = Math.min(4, percent.length());
        return percent.substring(0, min) + " %";
    }
}
