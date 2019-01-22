package com.codecool.model.creature.strategy;

import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.board.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HerbivoreBehavioralStrategy implements BehavioralStrategy {

    private List<List<Cell>> herbivoreSight;
    private Position position;
    //private Map

    public void getBoardView(List<List<Cell>> board) {
        this.herbivoreSight = board;
    }

    public void getCoordinates(Position currentPosition) {
        this.position = currentPosition;
    }

    public Directions suggestMove() {
    return null;
    }
}
