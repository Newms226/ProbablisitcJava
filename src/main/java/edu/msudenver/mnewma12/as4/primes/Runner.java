package edu.msudenver.mnewma12.as4.primes;

import edu.msudenver.mnewma12.as4.cli.CLI;
import edu.msudenver.mnewma12.as4.tools.NumberTools;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {

    /**
     * Amount of random, composite numbers to generate
     */
    static int N = 1000;

    static int MAX_K = 1_000_000;

    static double MAX_RAN = Integer.MAX_VALUE;

    public static void main(String[] args) {
        for (int k = 10; k <= MAX_K; k *= 10) {
            List<Result> results = test(k);

            double falsePrimes = results.stream().filter(Result::isPrime).count();

            CLI.echoLn(
                    k + "," +  falsePrimes
//                "k = " + k
//              + "\nFailed " + falsePrimes + "(" + NumberTools.formatPercent( falsePrimes / N) + ")"
//              + "\n\n"
            );
        }
    }

    static double ran() { return Math.random() * MAX_RAN; }

    static double genComposite() {
        return ran() * ran();
    }

    static List<Result> test(int k) {
        return Stream.generate(Runner::genComposite)
                .limit(N)
                .parallel()
                .map(d -> Result.iterativeEvaluate(d, k))
                .collect(Collectors.toList());
    }
}
