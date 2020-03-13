package edu.msudenver.mnewma12.as4.integration;

import edu.msudenver.mnewma12.as4.cli.CLI;
import edu.msudenver.mnewma12.as4.tools.NumberTools;

import java.util.ArrayList;

import static edu.msudenver.mnewma12.as4.integration.Runner.X_MAX;

public class MeanEstimation {

    MFunction f;

    ArrayList<Double> results;

    public MeanEstimation(MFunction f) {
        this.f = f;
        results = new ArrayList<>();
    }

    public void add(Double x) {
        Double result = f.apply(x);
//        CLI.echoLn("f(" + x + ") = " + result);
        results.add(result);
    }

    public MeanEstimation combine(MeanEstimation o) {
        if(!f.equals(o.f)) throw new Error("Invalid combination");
        this.results.addAll(o.results);
        return this;
    }

    public double estimate() {
        return results.stream()
                .mapToDouble(d -> d)
                .average().getAsDouble() * X_MAX;
    }

    @Override
    public String toString() {
        return "Mean Estimation"
//           + "\nf(x) = " + f
           + "\nEstimated: " + estimate()
           + "\nExpected: " + f.expected();
    }
}
