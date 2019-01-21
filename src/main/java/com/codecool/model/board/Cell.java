package com.codecool.model.board;

import com.codecool.model.creature.AbstractCreature;

public class Cell {
    AbstractCreature creature = null;
    int foodAmmount;
    boolean lock;

    public void lock(){
        this.lock = true;
    }
    public void unlock(){
        this.lock = false;
    }

    public void setCreature(AbstractCreature creature) {
        this.creature = creature;
    }
}
