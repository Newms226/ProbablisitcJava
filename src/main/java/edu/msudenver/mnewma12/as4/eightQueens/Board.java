package edu.msudenver.mnewma12.as4.eightQueens;


import edu.msudenver.mnewma12.as4.cli.CLI;
import edu.msudenver.mnewma12.as4.eightQueens.IntPoint.Direction;

public class Board {

    public static int SIZE = 8;

    static IntPoint getRandomLocation() {
        return new IntPoint(ran(), ran());
    }

    static int ran() { return (int) Math.floor( Math.random() * SIZE ); }

    static Board withRandom(int count) {
        Board b = new Board();
        for (int i = 0; i < count; i++) {
            IntPoint p = null;
            do { p = getRandomLocation(); } while (! b.canPlaceQueenIn(p) );
            b.placeQueen(p);
        }
        return b;
    }

    private boolean[][] board = new boolean[SIZE][SIZE];

    Board() {}

    boolean get(IntPoint location) { return get(location.getX(), location.getY()); }

    boolean get(int x, int y) { return board[x][y]; }

    boolean hasQueen(IntPoint location) { return get(location); }

    boolean canPlaceQueenIn(IntPoint location) {
//        boolean row = isRowEmpty(location.getX());
//        boolean col = isColumnEmpty(location.getY());
//        boolean diag = isDiagnoalEmpty(location);
//        CLI.echoLn("ROW: " + row + " COL: "+ col + " DIAG: " + diag);

        return  isRowEmpty(location.getX())
                && isColumnEmpty(location.getY())
                && isDiagnoalEmpty(location);
    }

    public boolean isColumnEmpty(int y) { return hasQueen( getColumn(y) ); }

    private boolean isRowEmpty(int x) { return hasQueen( getRow(x) ); }

    private boolean hasQueen(boolean[] slice) {
        for (boolean b : slice) { if (b) return false; }
        /*else*/ return true;
    }

    private boolean[] getRow(int x) { return board[x]; }
    
    private boolean[] getColumn(int y) {
        boolean[] toReturn = new boolean[SIZE];
        for (int i = 0; i < SIZE; i++) {
            toReturn[i] = board[i][y];
        }
        return toReturn;
    }

    private boolean hasQueenInDirection(IntPoint location, Direction direction) {
        return diagonalEval(location.move(direction), direction);
    }

    private boolean diagonalEval(IntPoint location, Direction direction) {
        if (!location.isValid()) return false;
        else if (hasQueen(location)) return true;
        else return hasQueenInDirection(location, direction);
    }

    /**
     * See DeMorgan's Law
     * @param location
     * @return
     */
    private boolean isDiagnoalEmpty(IntPoint location) {
        return !(hasQueenInDirection(location, Direction.NW)
                || hasQueenInDirection(location, Direction.NE)
                || hasQueenInDirection(location, Direction.SE)
                || hasQueenInDirection(location, Direction.SW));
    }

    void placeQueen(int x, int y) {
        placeQueen( new IntPoint(x, y) );
    }

    void placeQueen(IntPoint location) {
        if (canPlaceQueenIn(location)) {
            board[location.getX()][location.getY()] = true;
        } else if (hasQueen(location)) {
            Exception e = new IllegalArgumentException();
            CLI.fail("Tried to place a queen where there already was one", e);
        } else {
            Exception e = new IllegalArgumentException();
            CLI.fail(location + " is NOT a valid location.", e);
        }
    }

    void removeQueen(IntPoint location) {
        board[location.getX()][location.getY()] = false;
    }

    public int queenCount() {
        int count = 0;
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (get(x, y)) count++;
            }
        }
        return count;
    }

    public boolean isComplete() { return queenCount() == SIZE; }

    String printSpot(int x, int y) {
        return board[x][y] ? "Q" : " ";
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer("  |0|1|2|3|4|5|6|7|\n");
        for (int x = 0; x < SIZE; x++) {
            buf.append(x).append(" |");
            for (int y = 0; y < SIZE; y++) {
                buf.append(printSpot(x, y) + "|");
            }
            buf.append("\n");
        }
        return buf.toString();
    }
}
