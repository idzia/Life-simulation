package com.codecool.model.board;

import com.codecool.controller.BoardController;
import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.creature.Herbivore;

public class Board {

     private Cell[][] board;
     private BoardController boardController;

    public void initialize(int width,int height) {
        this.board = new Cell[height][width];

        for (int i = 0; i<height;i++){

            for (int j = 0; j<width; j++){
                board[i][j] = new Cell();
            }
        }

        this.boardController = new BoardController(this);
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

    public Cell getNextCell(int column, int row, Directions direction) {
        Position pos = new Position();
        pos.setY(row);
        pos.setX(column);
        return boardController.getNextCell(pos, direction);
    }

    public void moveCreature(int column, int row, Directions direction) {
        Position pos = new Position();
        pos.setY(row);
        pos.setX(column);
        Cell current = board[row][column];
        Cell target = boardController.getNextCell(pos, direction);
        swapCells(current, target);
    }

    private void swapCells(Cell current, Cell target) {
        // todo: set creature pos
        target.setCreature(current.getCurrentCreature());
        current.setCreature(null);
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
