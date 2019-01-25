package com.codecool.model.board;

import com.codecool.model.Directions;
import com.codecool.model.Position;

// TODO: refactor cell getters
// TODO : move here methods from Board
// TODO: move it to the controller package
// TODO: change as many methods functional

class BoardHelper {
    private Board board;

    BoardHelper(Board board) {
        this.board = board;
    }

    Cell getNextCell(Position pos, Directions dir) {
        pos = getPositionOfCellInDirection(pos, dir);
        return board.getBoard()[pos.getY()][pos.getX()];
    }
    
    Position getPositionOfCellInDirection(Position pos, Directions dir) throws IllegalArgumentException {
        Position newPos = new Position(pos.getY(), pos.getX());
        switch (dir) {
            case N: {
                getNorthIndex(newPos);
                handleBorder(newPos);
                break;
            }
            case NE: {
                getNorthEastIndex(newPos);
                handleBorder(newPos);
                break;
            }
            case NW: {
                getNorthWestIndex(newPos);
                handleBorder(newPos);
                break;
            }
            case W: {
                getWestIndex(newPos);
                handleBorder(newPos);
                break;
            }
            case E: {
                getEastIndex(newPos);
                handleBorder(newPos);
                break;
            }
            case S: {
                getSouthIndex(newPos);
                handleBorder(newPos);
                break;
            }
            case SE: {
                getSouthEastIndex(newPos);
                handleBorder(newPos);
                break;
            }
            case SW: {
                indexSouthWestIndex(newPos);
                handleBorder(newPos);
                break;
            }
            case PASS: {
                return pos;
            }
            default:
                throw new IllegalArgumentException();
        }
        return newPos;
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

    private void indexSouthWestIndex(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        pos.setX(--x);
        pos.setY(--y);
    }

    private void getSouthEastIndex(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        pos.setX(++x);
        pos.setY(++y);
    }

    private void getSouthIndex(Position pos) {
        int y = pos.getY();
        pos.setY(++y);
    }

    private void getEastIndex(Position pos) {
        int x = pos.getX();
        pos.setX(++x);
    }

    private void getWestIndex(Position pos) {
        int x = pos.getX();
        pos.setX(--x);
    }

    private void getNorthWestIndex(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        pos.setX(--x);
        pos.setY(--y);
    }

    private void getNorthEastIndex(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        pos.setX(++x);
        pos.setY(--y);
    }

    private void getNorthIndex(Position pos) {
        int y = pos.getY();
        pos.setY(--y);
    }
}
