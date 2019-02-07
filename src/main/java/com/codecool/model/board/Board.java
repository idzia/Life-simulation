package com.codecool.model.board;

import com.codecool.controller.BoardController;
import com.codecool.model.Position;

//TODO: change as many methods functional
public class Board {

    volatile private Cell[][] board;
    private int width;
    private int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = createBoard(width, height);
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

    public Cell getCell(int y, int x) {
        return board[y][x];
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

    public Cell[][] getBoard() {
        return board;
    }
}