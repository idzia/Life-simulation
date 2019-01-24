package com.codecool.model.creature;

import com.codecool.controller.ThreadsManager;
import com.codecool.model.creature.strategy.BehavioralStrategy;
import com.codecool.model.creature.strategy.StupidHerbivoreStrategy;

import java.util.ArrayList;
import java.util.List;

public class CreatureFactory {
    private ThreadsManager manager;
    private int counter;

    public CreatureFactory(ThreadsManager manager) {
        this.manager = manager;
        this.counter = 1;
    }

    public List<Creature> getCreatures(int amount) {
        List<Creature> creatures = new ArrayList<>();
        for(int i = 0; i < amount; i++) {
            creatures.add(new Herbivore(new StupidHerbivoreStrategy(), manager));
        }
        return creatures;
    }
}
