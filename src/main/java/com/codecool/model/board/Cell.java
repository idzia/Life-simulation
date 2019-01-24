package com.codecool.model.board;

import com.codecool.model.Position;
import com.codecool.model.creature.Creature;

public class Cell {
    private Creature currentCreature = null;
    private int foodAmount = 0;
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
    public int getFoodAmount(){
        return foodAmount;
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

    public void addFoodAmount(int foodAmmount) {
        this.foodAmount += foodAmmount;
    }

    public void reduceFoodAmount(int foodAmmount) {
        this.foodAmount -= foodAmmount;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    private void setFoodAmount(int amount) {
        this.foodAmount = amount;
    }

    public Cell copy() {
        Cell newCell = new Cell();
        newCell.setLock(this.lock);
        newCell.setCreature(this.currentCreature);
        newCell.setPosition(this.position);
        newCell.setFoodAmount(this.foodAmount);
        return newCell;
    }

}
