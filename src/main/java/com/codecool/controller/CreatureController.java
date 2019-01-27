package com.codecool.controller;

import com.codecool.model.Directions;
import com.codecool.model.creature.AbstractCreature;
import com.codecool.model.creature.Creature;

public class CreatureController {
    private AbstractCreature creature;
    private ThreadsManager manager;

    public CreatureController(Creature creature, ThreadsManager manager) {
        this.creature = (AbstractCreature) creature;
        this.manager = manager;
    }

    public void move() {
        creature.getStrategy().update(manager.cutBoard(creature));
        Directions direction = creature.getStrategy().suggestMove();
        if (manager.moveCreature(creature, direction)) {
            creature.setDoneMove(true);
        }
    }

}
