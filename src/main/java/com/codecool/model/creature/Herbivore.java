package com.codecool.model.creature;

import com.codecool.controller.BoardObserver;
import com.codecool.controller.ThreadsManager;
import com.codecool.model.Directions;
import com.codecool.model.creature.strategy.BehavioralStrategy;

public class Herbivore extends AbstractCreature implements Creature {

    public Herbivore(BehavioralStrategy strategy, ThreadsManager tm) {
        super(strategy,tm);
    }
}
