package com.codecool.model.creature;

import com.codecool.controller.ThreadsManager;
import com.codecool.model.creature.strategy.BehavioralStrategy;

public class CreatureFactory {
    private ThreadsManager manager;

    public CreatureFactory(ThreadsManager manager) {
        this.manager = manager;
    }

    public Creature getCreature(BehavioralStrategy strategy) {
        return new Herbivore(strategy, manager);
    }
}
