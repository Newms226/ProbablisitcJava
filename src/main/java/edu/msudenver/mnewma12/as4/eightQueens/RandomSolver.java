package edu.msudenver.mnewma12.as4.eightQueens;

import edu.msudenver.mnewma12.as4.cli.CLI;


import java.util.*;

import static edu.msudenver.mnewma12.as4.eightQueens.Board.*;

public class RandomSolver implements Runnable {

    private Board board;

    int randomValuesPopped = 0;

    int ranCount;

    /**
     * Maps Y -> X (or col -> row)
     */
    private Map<Integer, Integer> ranValues;

    public RandomSolver(int ranCount) {
        assert ranCount <= 8;

        board = new Board();
        this.ranCount = ranCount;
    }

    @Override
    public void run() {
        randomSolve(0, ranCount);
//            CLI.echoLn("FOUND SOLUTION");
//            CLI.echoLn(board);
//        } else {
//            CLI.echoLn("NO SOLUTION");
//            CLI.echoLn(board);

    }

    boolean randomSolve(int col, int count) {
        if (col >= SIZE) return true;
        if (count <= 0) return solve(col);

        IntPoint test = getRandomLocation(col);
        if (test == null) return false;

        board.placeQueen(test);
//        CLI.echoLn("Random placed in " + test);
//        CLI.echoLn(board);

        if (randomSolve(col + 1, count - 1)) return true;
        // else
        board.removeQueen(test);
        randomValuesPopped++;

//        CLI.echoLn("RANDOM HAD TO BACK TRACK ON COL" + col);
//        CLI.echoLn(board);
        return solve(col, test.getX());
    }

    private Random rgen = new Random();

    private int[] shuffle(int[] rows) {
        for (int i=0; i < rows.length; i++) {
            int randomPosition = rgen.nextInt(SIZE);
            int temp = rows[i];
            rows[i] = rows[randomPosition];
            rows[randomPosition] = temp;
        }
        return rows;
    }

    private IntPoint getRandomLocation(int col) {
        IntPoint test = new IntPoint(0, col);
        for (int row : shuffle( getAllRows() )) {
            test.setX(row);

            if(board.canPlaceQueenIn(test)) return test;
        }

        return null;
    }




    static final int[] getAllRows() { return new int[]{0, 1, 2, 3, 4, 5, 6, 7}; }

    boolean solve(int col) { return place(col, getAllRows()); }

    boolean solve(int col, int invalidRow) {
        return place(col, getRowsWithInvalid(invalidRow));
    }

    int[] getRowsWithInvalid(int invalidRow) {
        int[] possibleRows = new int[SIZE - 1];
        for (int i = 0; i < invalidRow; i++) { possibleRows[i] = i; }
        for (int i = invalidRow + 1; i < SIZE; i++) { possibleRows[i-1] = i; }
        return possibleRows;
    }

    // CITE Geeks4Geeks
    boolean place(int col, int[] possibleRowValues) {
        if (col >= SIZE) return true;

        IntPoint test = new IntPoint(0, col);
        for (int x : possibleRowValues) {
            test.setX(x);

            if (board.canPlaceQueenIn(test)) {
                // CLI.echoLn("VALID LOCATION AT: " + test);
                board.placeQueen(test);
                // CLI.echoLn("NEW BOARD");
                // CLI.echoLn(board);

                if(solve(col + 1)) { return true; }

                // backtrack
                // CLI.echoLn("BACK TRACK");
                board.removeQueen(test);
                // CLI.echoLn("NEW BOARD");
                // CLI.echoLn(board);
            }
        }

        // no spots avail in col
        // CLI.echoLn("NO VALID LOCATIONS IN COL " + col);
        return false;
    }

    boolean hadToPopRanQueen() { return randomValuesPopped != 0; }

}
