package com.codecool.model.board;

import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.creature.Creature;

import java.util.List;
import java.util.Random;

//TODO: switch fields to Atomic
//TODO: change as many methods functional
public class Board {

    volatile private Cell[][] board;
    private BoardHelper boardHelper;
    private int width;
    private int height;

    // TODO: move it to BoardHelper
    public void initialize(int width, int height) {
        this.board = createBoard(width, height);
        setWidth(width);
        setHeight(height);
        this.boardHelper = new BoardHelper(this);
    }


    private Cell[][] createBoard(int width, int height) {
        Cell[][] board = new Cell[height][width];
        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {
                Position p = new Position(i, j);
                board[i][j] = new Cell();
                board[i][j].setPosition(p);
            }
        }
        return board;
    }

    //TODO: move it to BoardHelper
    public void populate(List<Creature> creatures) {
        Random generator = new Random();
        int x = generator.nextInt(width);
        int y = generator.nextInt(height);
        for (Creature creature : creatures) {
            while (board[y][x].getCurrentCreature() != null) {
                y = generator.nextInt(height);
                x = generator.nextInt(width);
            }
            board[y][x].lock();
            Position p = new Position();
            p.setY(y);
            p.setX(x);
            creature.setPosition(p);
            board[y][x].setCreature(creature);
        }
    }

    //TODO: move it to BoardHelper
    public Cell[][] getBoard() {
        return board;
    }

    public Cell getCell(int x, int y) {
        return board[y][x];
    }

    //TODO: move it to BoardHelper
    public Cell[][] getCellsFrom(int x, int y, int radius, boolean copy) {
        int h = 2 * radius + 1;
        int w = 2 * radius + 1;
        Cell[][] cellInRange = new Cell[h][w];

        int radiusY = radius;

        for (int i = 0; i < h; i++) {
            int temporaryY = y - radiusY;
            if (temporaryY < 0) {
                temporaryY = height - radiusY;
            } else if (temporaryY >= height) {
                temporaryY = -1 - radiusY;
            }
            int radiusX = radius;


            for (int j = 0; j < w; j++) {

                int temporaryX = x - radiusX;
                if (temporaryX < 0) {
                    temporaryX = width - radiusX;
                } else if (temporaryX >= width) {
                    temporaryX = -1 - radiusX;
                }
                Position p = new Position(i, j);
                if (copy) {
                    cellInRange[i][j] = board[temporaryY][temporaryX].copy();
                    cellInRange[i][j].setPosition(p);
                } else {
                    cellInRange[i][j] = board[temporaryY][temporaryX];
                }

                radiusX--;
            }
            radiusY--;
        }

        return cellInRange;
    }


    //TODO: move it to BoardHelper
    public Cell[][] getCellsFrom(int column, int row) {
        int defaultRadius = 2;
        return getCellsFrom(column, row, defaultRadius, true);
    }

    //TODO: move it to BoardHelper
    public Cell[][] getCellsFrom(Position position) {
        return getCellsFrom(position.getX(), position.getY());
    }

    //TODO: move it to BoardHelper
    public Cell getNextCell(int column, int row, Directions direction) {
        Position pos = new Position();
        pos.setY(row);
        pos.setX(column);
        return boardHelper.getNextCell(pos, direction);
    }

    //TODO: move it to BoardHelper
    public synchronized void moveCreature(Position currentPos, Directions direction) {
        Cell currentCell = board[currentPos.getY()][currentPos.getX()];
        Position targetPosition = boardHelper.getPositionOfCellInDirection(currentPos, direction);
        if (targetPosition != currentPos && currentCell.getCurrentCreature()!=null) {
            Cell target = board[targetPosition.getY()][targetPosition.getX()];
            currentCell.getCurrentCreature().setPosition(targetPosition);
            swapCells(currentCell, target);
        } else {
            currentCell.unlock();
        }
    }

    //TODO: move it to BoardHelper
    private void swapCells(Cell current, Cell target) {
        target.setCreature(current.getCurrentCreature());
        current.setCreature(null);
    }

    public void addFood(int x, int y) {
        board[y][x].addFoodAmount(1);
    }

    public void reduceFood(int x, int y) {
        board[y][x].reduceFoodAmount(1);
    }

    //TODO: move it to BoardHelper
    public void lockCell(Cell nextCell) {
        if (!(nextCell.isLock())) {
            nextCell.setLock(true);
        }
    }

    private void setWidth(int width) {
        this.width = width;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    //TODO: move it to BoardHelper
    public int countFoodCell() {
        int foodCells = 0;
        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {
                if (board[i][j].getFoodAmount()>0) {
                    foodCells++;
                }
            }
        }
        return foodCells;
    }

}