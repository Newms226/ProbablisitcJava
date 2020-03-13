package edu.msudenver.mnewma12.as4.eightQueens;

import edu.msudenver.mnewma12.as4.cli.CLI;

public class Runner {

    public static void main(String[] args) {
        Board b = new Board();
        b.placeQueen(new IntPoint(5, 2));
        CLI.echoLn(b);
    }

}
