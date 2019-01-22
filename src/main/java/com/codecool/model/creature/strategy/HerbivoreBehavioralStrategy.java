package com.codecool.model.creature.strategy;

import com.codecool.model.board.Cell;

import java.util.ArrayList;
import java.util.List;

public class HerbivoreBehavioralStrategy implements BehavioralStrategy {

    private List<List<Cell>> herbivoreSight;
    private int rowPosition;
    private int columnPosition;

    public void getBoardView(List<List<Cell>> board) {
        this.herbivoreSight = board;
    }

    public void getCoordinates(int row, int column) {
        this.rowPosition = row;
        this.columnPosition = column;
    }

    public List<int> suggestMove() {
        List<int> suggestedMove = new ArrayList<>();
    }
}
