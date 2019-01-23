package com.codecool.model.creature.strategy;

import com.codecool.controller.PositionController;
import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.board.Cell;

import java.util.*;

public class HerbivoreBehavioralStrategy implements BehavioralStrategy {

    private Cell[][] herbivoreSight;
    private PositionController positionController;
    private Map<Cell, int> targets;

    public void getBoardView(Cell[][] board) {
        this.herbivoreSight = board;
    }

    public void getCoordinates(Position currentPosition) {
        this.positionController.setCurrentPosition() = currentPosition;
    }

    private void findTargets() {
        this.targets = new HashMap<>();

        int rows = herbivoreSight.length;
        int columns = herbivoreSight[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                addTarget(i, j);
            }
        }
    }

    private void addTarget(int i, int j) {
        if (herbivoreSight[i][j].getFoodAmmount() > 0) {
            Cell cell = herbivoreSight[i][j];
            targets.put(cell, positionController.calculateDistance(cell.getPosition()));
        }
    }

    private Directions chooseTarget() {
        if (targets.size() > 0) {
            return chooseClosest();
        }
    }

    private Directions chooseClosest() {
        int min = Collections.min(targets.values());
        Cell cell = getCellByDiastance(min);
        return positionController.getDirections(cell.getPosition());
    }

    private Cell getCellByDiastance(int distance) {
        for (Map.Entry<Cell, int> entry : targets.entrySet()) {
            if (entry.getValue() == distance) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Directions suggestMove() {
    return null;
    }
}
