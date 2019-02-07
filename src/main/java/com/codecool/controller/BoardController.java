package com.codecool.controller;

import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.board.Board;
import com.codecool.model.board.Cell;
import com.codecool.model.creature.Creature;

import java.util.List;
import java.util.Random;

// TODO: refactor cell getters
// TODO: change as many methods functional

public class BoardController {
    private Board board;

    public BoardController() {
    }


    public void initialize(int width, int height) {
        this.board = new Board(width, height);
    }


    Cell getNextCell(Position pos, Directions dir) {
        pos = getPositionOfCellInDirection(pos, dir);
        return board.getCell(pos.getY(), pos.getX());
    }

    public Board getBoard() {
        return board;
    }

    public void populate(List<Creature> creatures) {
        Random generator = new Random();
        int x = generator.nextInt(board.getWidth());
        int y = generator.nextInt(board.getHeight());
        for (Creature creature : creatures) {
            while (board.getCell(y, x).getCurrentCreature() != null) {
                y = generator.nextInt(board.getHeight());
                x = generator.nextInt(board.getWidth());
            }
            board.getCell(y, x).lock();
            Position p = new Position();
            p.setY(y);
            p.setX(x);
            creature.setPosition(p);
            board.getCell(y, x).setCreature(creature);
        }
    }

    public Cell[][] getCellsFrom(int x, int y, int radius, boolean copy) {
        int h = 2 * radius + 1;
        int w = 2 * radius + 1;
        Cell[][] cellInRange = new Cell[h][w];

        int radiusY = radius;

        for (int i = 0; i < h; i++) {
            int temporaryY = y - radiusY;
            if (temporaryY < 0) {
                temporaryY = board.getHeight() - radiusY;
            } else if (temporaryY >= board.getHeight()) {
                temporaryY = -1 - radiusY;
            }
            int radiusX = radius;


            for (int j = 0; j < w; j++) {

                int temporaryX = x - radiusX;
                if (temporaryX < 0) {
                    temporaryX = board.getWidth() - radiusX;
                } else if (temporaryX >= board.getWidth()) {
                    temporaryX = -1 - radiusX;
                }
                Position p = new Position(i, j);
                if (copy) {
                    cellInRange[i][j] = board.getCell(temporaryY, temporaryX).copy();
                    cellInRange[i][j].setPosition(p);
                } else {
                    cellInRange[i][j] = board.getCell(temporaryY, temporaryX);
                }

                radiusX--;
            }
            radiusY--;
        }

        return cellInRange;
    }


    public void lockCell(Cell nextCell) {
        if (!(nextCell.isLock())) {
            nextCell.setLock(true);
        }
    }

    public Cell getCell(Position pos) {
        return board.getCell(pos.getY(), pos.getX());
    }


    public Cell[][] getCellsFrom(int column, int row) {
        int defaultRadius = 2;
        return getCellsFrom(column, row, defaultRadius, true);
    }

    public Cell[][] getCellsFrom(Position position) {
        return getCellsFrom(position.getX(), position.getY());
    }

    public Cell getNextCell(int column, int row, Directions direction) {
        Position pos = new Position();
        pos.setY(row);
        pos.setX(column);
        return getNextCell(pos, direction);
    }

    public synchronized void moveCreature(Position currentPos, Directions direction) {
        Cell currentCell = board.getCell(currentPos.getY(), currentPos.getX());
        Position targetPosition = getPositionOfCellInDirection(currentPos, direction);
        if (targetPosition != currentPos && currentCell.getCurrentCreature()!=null) {
            Cell target = board.getCell(targetPosition.getY(), targetPosition.getX());
            currentCell.getCurrentCreature().setPosition(targetPosition);
            swapCells(currentCell, target);
        } else {
            currentCell.unlock();
        }
    }

    private void swapCells(Cell current, Cell target) {
        target.setCreature(current.getCurrentCreature());
        current.setCreature(null);
    }


    public int countFoodCell() {
        int foodCells = 0;
        for (int i = 0; i < board.getHeight(); i++) {

            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getCell(i, j).getFoodAmount()>0) {
                    foodCells++;
                }
            }
        }
        return foodCells;
    }

    public void addFood(int x, int y) {
        board.getCell(y, x).addFoodAmount(1);
    }

    public void reduceFood(int x, int y) {
        board.getCell(y, x).reduceFoodAmount(1);
    }

    private Position getPositionOfCellInDirection(Position pos, Directions dir) throws IllegalArgumentException {
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
