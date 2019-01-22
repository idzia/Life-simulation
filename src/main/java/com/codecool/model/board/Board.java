package com.codecool.model.board;

import com.codecool.model.Directions;
import com.codecool.model.creature.Herbivore;

import java.util.Random;

public class Board {

     private Cell[][] board;
     private int width;
     private int height;

    public void initialize(int width,int height,int populateQuantity) {
        this.board = createBoard(width, height);
        populate(width, height, populateQuantity);
        setWidth(width);
        setHeight(height);
    }


    private Cell[][] createBoard(int width,int height) {
        Cell[][] board = new Cell[height][width];
        for (int i = 0; i<height;i++){

            for (int j = 0; j<width; j++){
                board[i][j] = new Cell();
            }
        }
        return board;
    }

    private void populate(int width,int height,int quantity) {
        Random generator = new Random();
        int i = 0;
        while (i<quantity) {
            int y = generator.nextInt(height);
            int x = generator.nextInt(width);
            if (board[y][x].getCurrentCreature()==null) {
                board[y][x].setCreature(new Herbivore());
                i++;
            }
        }

    }

    public Cell[][] getBoard(){
        return board;
    }

    public Cell[][] getCellsFrom(int x,int y,int radius){
        int h = radius * radius + 1;
        int w = radius * radius + 1;
        Cell[][] cellInRange = new Cell[h][w];
        int radiusY = radius;
        for (int i = 0; i<h;i++){
            int radiusX = radius;
            for (int j = 0; j<w; j++){
                if((y-radiusY) <= height && (x-radiusX) <= width) {
                    cellInRange[i][j] = board[y-radiusY][x-radiusX];
                } else if ((y-radiusY) >= height && (x-radiusX) <= width) {
                    cellInRange[i][j] = board[-1-radiusY][x-radiusX];
                } else if ((y-radiusY) <= height && (x-radiusX) >= width) {
                    cellInRange[i][j] = board[y-radiusY][-1-radiusX];
                } else if ((y-radiusY) >= height && (x-radiusX) >= width) {
                    cellInRange[i][j] = board[-1 - radiusY][-1 - radiusX];
                }

                radiusX--;
            }
            radiusY--;
        }
        return cellInRange;
    }

    public Cell[][] getCellsFrom(int x,int y) {
        int defaultRadius = 2;
        return getCellsFrom(x,y,defaultRadius);
    }

    public Cell getNextCell(int x, int y, Directions direction) {
        return null;
    }

    public void addFood(int x, int y) {
    }
    public void removeFood(int x, int y) {
    }

    public boolean lockCell(int x,int y) {
        return true;
        //todo: locking if not locked. otherwise return false
    }
    public void unlockCells(){
        //todo: unlock all cells (after turn)
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}


