package com.codecool.model.board;

import com.codecool.model.Position;
import com.codecool.model.creature.Creature;

public class Cell {
    private Creature currentCreature = null;
    private int foodAmmount = 0;
    private boolean lock = false;// x2
    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

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

    public boolean isLock() {
        return lock;
    }

    public void setCreature(Creature creature) {
        this.currentCreature = creature;
    }

    public void addFoodAmmount(int foodAmmount) {
        this.foodAmmount += foodAmmount;
    }

    public void reduceFoodAmmount(int foodAmmount) {
        this.foodAmmount -= foodAmmount;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

}
