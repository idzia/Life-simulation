package com.codecool.model.creature.strategy;

import com.codecool.model.Directions;
import com.codecool.model.board.Cell;
import com.codecool.model.creature.AbstractCreature;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SmartHerbivoreStrategy extends HerbivoreStrategy {

    private Directions lastChosenDirection;

    public SmartHerbivoreStrategy() {
        super();
        this.lastChosenDirection = chooseDirectionDifferentThanPass();
    }

    @Override
    Directions chooseOtherDirection() {
        setLastApprovedDirection();
        return this.lastChosenDirection;
    }

    @Override
    Directions chooseBestWithFood() {
        Cell cell = getClosestWithBiggestFoodAmount();
        return getPositionController().getDirections(cell.getPosition());
    }

    @Override
    void addTarget(int i, int j) {
        if (getHerbivoreSight()[i][j].getFoodAmmount() > 0 && !getHerbivoreSight()[i][j].isLock()) {
            Cell cell = getHerbivoreSight()[i][j];
            getTargets().put(cell, getPositionController().calculateDistance(cell.getPosition()));
        }
    }

    private void setLastApprovedDirection(AbstractCreature creature) {
        if (getRand().nextInt(10) % 4 == 0) {
            this.lastChosenDirection = chooseDirectionDifferentThanPass();
        }
    }

    private Directions chooseDirectionDifferentThanPass() {
        Directions direction;
        do {
            direction = chooseRandomDirection();
        } while (direction.equals(Directions.PASS));
        return direction;
    }
}
