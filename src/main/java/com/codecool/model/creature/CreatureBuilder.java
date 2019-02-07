package com.codecool.model.creature;

import com.codecool.controller.ThreadsManager;
import com.codecool.model.creature.strategy.BehavioralStrategy;
import com.codecool.model.creature.strategy.StrategyFactory;

public class CreatureBuilder {
    private StrategyFactory strategyFactory;
    private CreatureFactory creatureFactory;

    public CreatureBuilder(ThreadsManager manager) {
        this.creatureFactory = new CreatureFactory(manager);
        this.strategyFactory = new StrategyFactory();
    }

    public Creature getCreature() {
        BehavioralStrategy strategy = strategyFactory.getStrategy();
        return creatureFactory.getCreature(strategy);
    }
}
