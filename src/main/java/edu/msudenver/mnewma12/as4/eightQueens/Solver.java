package edu.msudenver.mnewma12.as4.eightQueens;

import edu.msudenver.mnewma12.as4.cli.CLI;

import static edu.msudenver.mnewma12.as4.eightQueens.Board.SIZE;

public class Solver implements Runnable {

    private Board board;

    public Solver(Board start) {
        this.board = start;
    }

    @Override
    public void run() {
        if (solve(board, 0)) {
            CLI.echoLn("FOUND SOLUTION");
            CLI.echoLn(board);
        } else {
            CLI.echoLn("NO SOLUTION");
            CLI.echoLn(board);
        }
    }

    // TODO: Cite Geeks4Geeks
    boolean solve(Board board, int y) {
//        CLI.echoLn("EVAL COL " + y);
        if (y >= SIZE) return true;

        // TODO backtrack past the random values
        if (!board.isColumnEmpty(y)) {
            if(solve(board, y + 1)) { return true; }
        }

        IntPoint test = new IntPoint(0, y);
        for (int x = 0; x < SIZE; x++) {
            test.setX(x);

            if (board.canPlaceQueenIn(test)) {
//                CLI.echoLn("VALID LOCATION AT: " + test);
                board.placeQueen(test);
//                CLI.echoLn("NEW BOARD");
//                CLI.echoLn(board);

                if(solve(board, y + 1)) { return true; }

                // backtrack
//                CLI.echoLn("BACK TRACK");
                board.removeQueen(test);
//                CLI.echoLn("NEW BOARD");
//                CLI.echoLn(board);
            }
        }

        // no spots avail in col
//        CLI.echoLn("NO VALID LOCATIONS IN COL " + y);
        return false;
    }

}
