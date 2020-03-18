package edu.msudenver.mnewma12.as4.tools;

import java.text.NumberFormat;

public class NumberTools {

    private static final NumberFormat formatter =
            NumberFormat.getIntegerInstance();

    public static String formatDouble(double elapsed) {
        synchronized (formatter) {
            return formatter.format(elapsed);
        }
    }


    /* percentDifference calculates the percent difference between two numbers.
     * @param: origional is the first number
     *         newNumber is the number on which to calculate the difference
     * @return: a double representation of the difference
     */
    public static double percentDifference(double original, double newNumber) {
        return (newNumber - original) / original;
    }

    /* percentDifferenceAsString generates a formated String representation of the
     * percent difference between two numbers, formated by the percent formatter
     * defined by this class
     * @param: origional is the first number
     *         newNumber is the number on which to calculate the difference
     * @return: a String representation of the difference, formatted according to the
     *          percent formatter of this class
     */
    public static String percentDifferenceAsString(double original, double newNumber) {
        return formatPercent((newNumber - original) / original);
    }

    public static String formatPercent(double raw) {
        String percent = Double.toString(raw * 100);
        int dot, max;

        if ((dot = percent.indexOf(".")) == -1 ) {
            max = percent.length();
        } else {
            max = dot + 2;
        }

        return percent.substring(0, max) + " %";
    }

    public static String formatNanoseconds(long nanoseconds) {
        return formatDouble(nanoseconds) + " nanosecond" + (nanoseconds > 1 ? "s": "")
                + " (" + nanoToReg(nanoseconds) + " seconds)";
    }

    public static double nanoToReg(long nanoseconds) {
        return ((double)nanoseconds) / 1_000_000_000;
    }
}
