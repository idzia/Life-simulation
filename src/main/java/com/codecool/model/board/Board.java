package com.codecool.model.board;

import com.codecool.model.Directions;
import com.codecool.model.creature.Herbivore;

public class Board {

     private Cell[][] board;

    public void initialize(int width,int height) {
        this.board = new Cell[height][width];

        for (int i = 0; i<height;i++){

            for (int j = 0; j<width; j++){
                board[i][j] = new Cell();
            }
        }
    }

    public void populate() {
        board[0][0].setCreature(new Herbivore());
    }

    public Cell[][] getBoard(){
        return board;
    }

    public Cell[][] getCellsFrom(int x,int y,int radius){
        return null;
        //todo: implement
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

    public boolean lockCell(int x,int y){
        return true;
        //todo: locking if not locked. otherwise return false
    }
    public void unlockCells(){
        //todo: unlock all cells (after turn)
    }

}
