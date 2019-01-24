package com.codecool.model.board;

import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.creature.Creature;

import java.util.List;
import java.util.Random;

public class Board {

    private Cell[][] board;
    private BoardHelper boardHelper;
    private int width;
    private int height;


    public void initialize(int width, int height, int populateQuantity) {
        this.board = createBoard(width, height);
        setWidth(width);
        setHeight(height);
        int startFoodQuantity = 2 * (width * height);

        setFood(startFoodQuantity);
        this.boardHelper = new BoardHelper(this);
    }


    private Cell[][] createBoard(int width, int height) {
        Cell[][] board = new Cell[height][width];
        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {
                board[i][j] = new Cell();
            }
        }
        return board;
    }

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

    public Cell[][] getBoard() {
        return board;
    }

    public Cell getCell(int x, int y) {
        return board[y][x];
    }

    public Cell[][] getCellsFrom(int x, int y, int radius) {
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

                cellInRange[i][j] = board[temporaryY][temporaryX];


                radiusX--;
            }
            radiusY--;
        }

        return cellInRange;
    }

    public Cell[][] getCellsFrom(int x, int y) {
        int defaultRadius = 2;
        return getCellsFrom(x, y, defaultRadius);
    }

    public Cell getNextCell(int column, int row, Directions direction) {
        Position pos = new Position();
        pos.setY(row);
        pos.setX(column);
        return boardHelper.getNextCell(pos, direction);
    }

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


    public void setFood(int foodQuantity) {
        Random generator = new Random();
        for (int i = 0; i < (foodQuantity); i++) {
            addFood(generator.nextInt(width), generator.nextInt(height));
        }
    }

    private void swapCells(Cell current, Cell target) {
        target.setCreature(current.getCurrentCreature());
        current.setCreature(null);
    }

    private void addFood(int x, int y) {
        board[y][x].addFoodAmmount(1);
    }

    public void reduceFood(int x, int y) {
        board[y][x].reduceFoodAmmount(1);
    }

    public boolean lockCell(Cell nextCell) {
        if (!(nextCell.isLock())) {
            nextCell.setLock(true);
            return true;
        }
        return false;


    }

    public void unlockCells() {

        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {
                if (board[i][j].getCurrentCreature() == null) {
                    board[i][j].setLock(false);
                }
            }
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell[][] getCellsFrom(Position position) {
        return getCellsFrom(position.getX(), position.getY());
    }
}