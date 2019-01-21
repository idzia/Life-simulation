package com.codecool.model.board;

import com.codecool.model.creature.Creature;

public class Cell {
    Creature currentCreature = null;
    int foodAmmount = 0;
    boolean lock;// x2

    public Creature getCurrentCreature(){
        return currentCreature;
    }
    public int getFoodAmmount(){
        return foodAmmount;
    }

    public void lock(){
        this.lock = true;
    }
    public void unlock(){
        this.lock = false;
    }

    public void setCreature(Creature creature) {
        this.currentCreature = creature;
    }
}
