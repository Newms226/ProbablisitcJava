package edu.msudenver.mnewma12.as4.eightQueens;

import edu.msudenver.mnewma12.as4.cli.CLI;
import edu.msudenver.mnewma12.as4.tools.NumberTools;

import static edu.msudenver.mnewma12.as4.tools.NumberTools.formatNanoseconds;

public class Runner {

    static StringBuffer buf = new StringBuffer();

    public static void main(String[] args) {
        test(0);
        buf = new StringBuffer();
        for (int i = 0; i <= 8; i++) {
            buf.append(i).append(",");
            for (int j = 0; j < 10; j++) {
                test(i);
            }
            buf.append("\n");
        }

        CLI.echoLn(buf.toString());
    }

    static void test(int count) {
        long start, end;
        RandomSolver randomSolver = new RandomSolver(count);

        start = System.nanoTime();
        randomSolver.run();
        end = System.nanoTime();

        buf.append((end - start)).append(",");
//        CLI.echoLn(count + "," + (end - start));
    }

}
