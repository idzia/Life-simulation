package com.codecool.model.creature.strategy;

import com.codecool.model.Directions;

public class DummyHerbivoreStrategy extends HerbivoreStrategy {

    @Override
    Directions chooseOtherDirection() {
        return chooseRandomDirection();
    }

    @Override
    Directions chooseBestWithFood() {
        return chooseRandomDirection();
    }
}
