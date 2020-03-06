package edu.msudenver.mnewma12.as4.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.function.Function;
import java.util.function.Predicate;

public class CLI {

    private static final BufferedReader sysIn =
            new BufferedReader(new InputStreamReader(System.in));

    private static final NumberFormat formatter =
            NumberFormat.getIntegerInstance();


    public static void echoLn(String s) {
        System.out.println(s);
    }

    public static void echoLn(Thread currentThread, String s) {
        echoLn(currentThread + "::> " + s);
    }

    public static void echoWithThread(String s) {
        echoLn(Thread.currentThread(), s);
    }

    public static void echo(String s) {
        System.out.print(s);
    }

    public static <T> T getGeneric(
            String message,
            Function<String, T> mapperFn,
            Predicate<T> validator,
            String failureMsg)
    {
        String input;
        T toReturn = null;

        while(true) {
            echoLn(message);
            input = readLine();

            try {
                toReturn = mapperFn.apply(input);
            } catch (Exception e) {
                e.printStackTrace();
                echoLn(failureMsg);
                continue;
            }

            if (validator.test(toReturn)) { return toReturn; }
            else { echoLn(failureMsg); }
        }
    }

    public static <T> T getOrElse(
            String message,
            Function<String, T> mappingFn,
            Predicate<T> validator,
            String failureMsg,
            T orElse)
    {
        String input;
        T toReturn = null;

        while(true) {
            echoLn(message);
            input = readLineOrElse(orElse);

            if (input.equals("")) { return orElse; }

            try {
                toReturn = mappingFn.apply(input);
            } catch (Exception e) {
                e.printStackTrace();
                echoLn(failureMsg);
                continue;
            }

            if (validator.test(toReturn)) { return toReturn; }
            else { echoLn(failureMsg); }
        }
    }

    public static String getOrElse(String message, String orElse) {
        String input;

        while(true) {
            echoLn(message);
            input = readLineOrElse(orElse);

            if (input.equals("")) { return orElse; }
            else { return input; }
        }
    }

    private static <T> String readLineOrElse(T orElse) {
        return readLine("[" + orElse + "]");
    }

    public static String readLine() { return readLine(""); }

    public static String readLine(String prefix) {
        synchronized (sysIn) {
            try {
                echo(prefix + " > ");
                return sysIn.readLine();
            } catch (IOException e) {
                return CLI.fail("Cannot read line.", e);
            }
        }
    }

    public static String getLine(String message) {
        echoLn(message);
        return readLine();
    }

    /**
     *
     * @param message
     * @param e
     * @param <T>
     * @return never executes, system exits with code 1 before.
     */
    public static <T> T fail(String message, Exception e) {
        echoLn(message);
        return fail(e);
    }

    public static <T> T fail(Exception e) {
        e.printStackTrace();
        throw new RuntimeException();
    }

    public static <T> void warn(String message, Exception e) {
        echoLn("WARN: " + message + "\nSTACKTRACE:"); // TODO were getting really close to a logger here
        e.printStackTrace();
    }

    public static <T> T fail(String message) {
        echoLn(message);
        throw new RuntimeException();
    }

    public static String formatLong(long l) {
        synchronized (formatter) { return formatter.format(l); }
    }



    public static void exit(String s) {
        echoLn(s);
        echoLn("Goodbye! ^.^");
        System.exit(0);
    }

    public static String formatDouble(double elapsed) {
        synchronized (formatter) {
            return formatter.format(elapsed);
        }
    }

    public static String boolToChar(boolean toConvert) {
        return toConvert ? "y" : "n";
    }

    public static String validationString(boolean orElse) {
        return orElse ? "[Y/n]" : "[n/Y]";
    }

    public static boolean validate(String message, boolean orElse) {
        String raw;

        while (true) {
            raw = getOrElse(message + " " + validationString(orElse),
                    boolToChar(orElse));
            if (Character.toLowerCase(raw.charAt(0)) == 'y') return true;
            else if (Character.toLowerCase(raw.charAt(0)) == 'n') return false;
            else echoLn("Invalid entry, please enter 'y' or 'n'");
        }
    }
}
