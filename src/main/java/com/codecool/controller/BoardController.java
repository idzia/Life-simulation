package com.codecool.controller;

import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.board.Board;
import com.codecool.model.board.Cell;
import com.codecool.model.creature.Creature;


public class BoardController {
    private Cell[][] board;

    public boolean moveCreature(Board board, Position pos, Directions dir) {
        this.board = board.getBoard();
        Creature creature = this.board[pos.getY()][pos.getX()].getCurrentCreature();
        Position currPos = new Position();
        currPos.setX(pos.getX());
        currPos.setY(pos.getY());

        if (makeMove(pos, dir, creature.getSpeed())) {
            Cell cell = this.board[currPos.getY()][currPos.getX()];
            cell.setCreature(null);
            cell.unlock();
            cell = this.board[pos.getY()][pos.getX()];
            cell.setCreature(creature);
            cell.lock();
            return true;
        } else {
            return false;
        }
    }

//    private boolean isMoveValid(Board board, Position pos, Directions dir) { return false;}

    private boolean makeMove(Position pos, Directions dir, int speed) {
        while (speed > 0) {
            switch (dir) {
                case N: {
                    moveNorth(pos);
                    break;
                }
                case NE: {
                    moveNorthEast(pos);
                    break;
                }
                case NW: {
                    moveNorthWest(pos);
                    break;
                }
                case W: {
                    moveWest(pos);
                    break;
                }
                case E: {
                    moveEast(pos);
                    break;
                }
                case S: {
                    moveSouth(pos);
                    break;
                }
                case SE: {
                    moveSouthEast(pos);
                    break;
                }
                case SW: {
                    moveSouthWest(pos);
                    break;
                }
                default:
                    return false;
            }
            handleBorder(pos);
            speed--;
        }
        return board[pos.getY()][pos.getX()].isLock();
    }

    private void handleBorder(Position pos) {
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

    private void moveNorth(Position pos) {
        int y = pos.getY();
        pos.setY(y--);
    }

    private void moveNorthEast(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        pos.setX(x++);
        pos.setY(y--);
    }

    private void moveNorthWest(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        pos.setX(x--);
        pos.setY(y--);
    }

    private void moveSouth(Position pos) {
        int y = pos.getY();
        pos.setY(y++);
    }

    private void moveSouthEast(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        pos.setX(x++);
        pos.setY(y++);
    }

    private void moveSouthWest(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        pos.setX(x--);
        pos.setY(y++);
    }

    private void moveEast(Position pos) {
        int x = pos.getX();
        pos.setX(x++);
    }

    private void moveWest(Position pos) {
        int x = pos.getX();
        pos.setX(x--);
    }
}
