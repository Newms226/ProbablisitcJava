package edu.msudenver.mnewma12.as4.eightQueens;

import java.util.Objects;

import static edu.msudenver.mnewma12.as4.eightQueens.Board.SIZE;

public class IntPoint {

    public enum Direction {
        NE(-1, 1),
        NW(-1, -1),
        SW(1, -1),
        SE(1, 1);

        final int deltaX, deltaY;

        Direction(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
    }

    private int x, y;

    public IntPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public IntPoint move(Direction d) {
        return new IntPoint(x + d.deltaX, y + d.deltaY);
    }

    public boolean isValid() {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

//    public DIRECTION compareTo(IntPoint o) {
//        double deltaX = Math.pow(o.x - x, 2);
//        double deltaY = Math.pow(o.y - y, 2);
//
//        if (deltaX == 0 && deltaY == 0) return DIRECTION.EQUAL;
//        if (deltaX == 0)
//
//    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntPoint intPoint = (IntPoint) o;
        return x == intPoint.x &&
                y == intPoint.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
