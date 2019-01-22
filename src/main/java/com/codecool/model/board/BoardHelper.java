package com.codecool.model.board;

import com.codecool.model.Directions;
import com.codecool.model.Position;

class BoardHelper {
    private Board board;

    BoardHelper(Board board) {
        this.board = board;
    }

    Cell getNextCell(Position pos, Directions dir) {
        pos = getPositionOfCellInDirection(pos, dir);
        handleBorder(pos);
        return board.getBoard()[pos.getY()][pos.getX()];
    }
    
    private Position getPositionOfCellInDirection(Position pos, Directions dir) throws IllegalArgumentException {
        switch (dir) {
            case N: {
                return getNorthIndex(pos);
            }
            case NE: {
                return getNorthEastIndex(pos);
            }
            case NW: {
                return getNorthWestIndex(pos);
            }
            case W: {
                return getWestIndex(pos);
            }
            case E: {
                return getEastIndex(pos);
            }
            case S: {
                return getSouthIndex(pos);
            }
            case SE: {
                return getSouthEastIndex(pos);
            }
            case SW: {
                return indexSouthWestIndex(pos);
            }
            default:
                throw new IllegalArgumentException();
        }
    }

    private void handleBorder(Position pos) {
        Cell[][] board = this.board.getBoard();
        int upperBorder = 0;
        int bottomBorder = board.length - 1;
        int rightBorder = board[0].length - 1;
        int leftBorder = 0;

        if (pos.getX() < leftBorder) {
            pos.setX(rightBorder);
        } else if (pos.getX() > rightBorder)  {
            pos.setX(leftBorder);
        }

        if (pos.getY() < upperBorder) {
            pos.setY(bottomBorder);
        } else if (pos.getY() > bottomBorder) {
            pos.setY(upperBorder);
        }
    }

    private Position indexSouthWestIndex(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        pos.setX(--x);
        pos.setY(--y);
        return pos;
    }

    private Position getSouthEastIndex(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        pos.setX(++x);
        pos.setY(++y);
        return pos;
    }

    private Position getSouthIndex(Position pos) {
        int y = pos.getY();
        pos.setY(++y);
        return pos;
    }

    private Position getEastIndex(Position pos) {
        int x = pos.getX();
        pos.setX(++x);
        return pos;
    }

    private Position getWestIndex(Position pos) {
        int x = pos.getX();
        pos.setX(--x);
        return pos;
    }

    private Position getNorthWestIndex(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        pos.setX(--x);
        pos.setY(--y);
        return pos;
    }

    private Position getNorthEastIndex(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        pos.setX(++x);
        pos.setY(--y);
        return pos;
    }

    private Position getNorthIndex(Position pos) {
        int y = pos.getY();
        pos.setY(--y);
        return pos;
    }
}
