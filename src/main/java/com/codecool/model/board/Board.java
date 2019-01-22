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
        int h = 2 * radius + 1;
        int w = 2 * radius + 1;
        Cell[][] cellInRange = new Cell[h][w];

        int radiusY = radius;

        for (int i = 0; i<h;i++){
            int temporaryY = y-radiusY;
            if (temporaryY < 0) {
                temporaryY = height-radiusY;
            } else if (temporaryY >= height) {
                temporaryY = -1 - radiusY;
            }
            int radiusX = radius;


            for (int j = 0; j<w; j++){

                int temporaryX = x-radiusX;
                if (temporaryX < 0) {
                    temporaryX = width-radiusX;
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


