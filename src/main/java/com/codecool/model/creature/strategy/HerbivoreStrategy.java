package com.codecool.model.creature.strategy;

import com.codecool.controller.PositionController;
import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.board.Cell;

import java.util.*;

public abstract class HerbivoreStrategy implements BehavioralStrategy {

    private static final List<Directions> VALUES =
            Collections.unmodifiableList(Arrays.asList(Directions.values()));
    private static final int SIZE = Directions.values().length;
    private final Random rand;
    private PositionController positionController;
    private Cell[][] herbivoreSight;
    private Map<Cell, Integer> targets;

    public HerbivoreStrategy() {
        this.positionController = new PositionController();
        this.rand = new Random();
    }

    public PositionController getPositionController() {
        return positionController;
    }

    public Cell[][] getHerbivoreSight() {
        return herbivoreSight;
    }

    public Map<Cell, Integer> getTargets() {
        return targets;
    }

    public void setBoardView(Cell[][] board) {
        this.herbivoreSight = board;
    }

    public void setCoordinates(Position relativePosition) {
        this.positionController.setCurrentPosition(relativePosition);
    }

    public void update(Cell[][] board) {
        setBoardView(board);
        int boardCenter = board.length / 2;
        setCoordinates(new Position(boardCenter, boardCenter));
    }

    protected void findTargets() {
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

    protected Directions chooseTarget() {
        if (targets.size() > 0) {
            return chooseBestWithFood();
        }
        return chooseOtherDirection();
    }

    abstract Directions chooseOtherDirection();

    abstract Directions chooseBestWithFood();

    protected Directions chooseRandomDirection() {
        return VALUES.get(rand.nextInt(SIZE));
    }

    protected Cell getCellByDiastance(int distance) {
        for (Map.Entry<Cell, Integer> entry : targets.entrySet()) {
            if (entry.getValue() == distance) {
                return entry.getKey();
            }
        }
        return null;
    }

    protected boolean stillHasFood() {
        return getCreatureCell().getFoodAmmount() > 0;
    }

    public Directions suggestMove() {
        if (stillHasFood()) return Directions.PASS;

        findTargets();
        return chooseTarget();
    }
}
