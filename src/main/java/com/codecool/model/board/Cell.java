package com.codecool.model.board;

import com.codecool.model.creature.AbstractCreature;
import com.codecool.model.creature.Creature;

public class Cell {
    AbstractCreature currentCreature = null;
    int foodAmmount = 0;
    boolean lock = false;// x2

    public AbstractCreature getCurrentCreature(){
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

    public void setCreature(AbstractCreature creature) {
        this.currentCreature = creature;
    }

    public void addFoodAmmount(int foodAmmount) {
        this.foodAmmount += foodAmmount;
    }

    public void reduceFoodAmmount(int foodAmmount) {
        this.foodAmmount -= foodAmmount;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

}

