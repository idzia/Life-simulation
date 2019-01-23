package com.codecool.model.creature.strategy;

import com.codecool.controller.PositionController;
import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.board.Cell;

import java.util.*;

public class HerbivoreBehavioralStrategy implements BehavioralStrategy {

    private Cell[][] herbivoreSight;
    private PositionController positionController;
    private Map<Cell, Integer> targets;
    private final Random rand;

    private static final List<Directions> VALUES =
            Collections.unmodifiableList(Arrays.asList(Directions.values()));
    private static final int SIZE = Directions.values().length;


    public HerbivoreBehavioralStrategy() {
        this.positionController = new PositionController();
        this.rand = new Random();
    }

    public void setBoardView(Cell[][] board) {
        this.herbivoreSight = board;
    }

    public void setCoordinates(Position relativePosition) {
        this.positionController.setCurrentPosition(relativePosition);
    }

    public void updateStrategy(Cell[][] board) {
        setBoardView(board);
        int boardCenter = board.length / 2;
        setCoordinates(new Position(boardCenter, boardCenter));
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

    private Cell getCreatureCell() {
        return herbivoreSight[positionController.getCurrentPosition().getX()][positionController.getCurrentPosition().getY()];
    }

    private void addTarget(int i, int j) {
        if (herbivoreSight[i][j].getFoodAmmount() > 0) {
            Cell cell = herbivoreSight[i][j];
            targets.put(cell, positionController.calculateDistance(cell.getPosition()));
        }
    }

    private Directions chooseTarget() {
        if (targets.size() > 0) {
            return chooseClosestWithFood();
        }
        return chooseRandomDirection();
    }

    private Directions chooseClosestWithFood() {
        int min = Collections.min(targets.values());
        Cell cell = getCellByDiastance(min);
        return positionController.getDirections(cell.getPosition());
    }

    private Directions chooseRandomDirection() {
        return VALUES.get(rand.nextInt(SIZE));
    }

    private Cell getCellByDiastance(int distance) {
        for (Map.Entry<Cell, Integer> entry : targets.entrySet()) {
            if (entry.getValue() == distance) {
                return entry.getKey();
            }
        }
        return null;
    }

    private boolean stillHasFood() {
        return getCreatureCell().getFoodAmmount() > 0;
    }
    public Directions suggestMove() {
        if (stillHasFood()) return Directions.PASS;

        findTargets();
        return chooseTarget();
    }
}
