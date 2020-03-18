package edu.msudenver.mnewma12.as4.primes;

import java.util.Random;

import static edu.msudenver.mnewma12.as4.primes.Runner.N;

class Result {

    static Result evaluate(double value, int k) {
        Random ran = new Random();
        boolean isPrime = true;
        int i;
        int max = (int) Math.floor(value);

        for (i = 0; i < k; i++) {
            if (value % ran.nextInt(max) == 0) {
                isPrime = false;
                break;
            }
        }

        return new Result(value, isPrime, k, i);
    }

    static Result iterativeEvaluate(double value, int k) {
        boolean isPrime = true;
        int i, m;

        for (i = 0, m = 2; i < k && m < Double.MAX_VALUE; i++, m++) {
            if (value % m == 0) {
                isPrime = false;
                break;
            }
        }

        return new Result(value, isPrime, k, i);
    }


    // ----- Class Definition ----- //

    private boolean isPrime;

    private int kInput, kActual;

    private double value;

    private Result(double value, boolean isPrime, int kInput, int kActual) {
        this.isPrime = isPrime;
        this.kInput = kInput;
        this.kActual = kActual;
        this.value = value;
    }

    boolean isPrime() { return isPrime; }

    public int getkInput() {
        return kInput;
    }

    public int getkActual() {
        return kActual;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " is " + (isPrime ? "prime" : "not prime") + ". " +
                "(Took " + kActual + " tries)";
    }
}
