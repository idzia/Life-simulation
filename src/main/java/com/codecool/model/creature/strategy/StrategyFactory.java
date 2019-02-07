package com.codecool.model.creature.strategy;

import java.util.Random;

public class StrategyFactory {
    // random strategy
    public BehavioralStrategy getStrategy() {
        Random rand = new Random();
        int bound = Strategies.values().length;
        return getStrategyInstance(Strategies.values()[rand.nextInt(bound)]);
    }

    // todo: if more strategies will implemented change to switch
    private BehavioralStrategy getStrategyInstance(Strategies strategy) {
        if (strategy == Strategies.STUPIDHERBIVORESTRATEGY) {
            return new StupidHerbivoreStrategy();
        }
        return null;
    }
}
