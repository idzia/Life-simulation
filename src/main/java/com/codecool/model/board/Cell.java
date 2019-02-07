package com.codecool.model.board;

import com.codecool.model.Position;
import com.codecool.model.creature.Creature;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Cell {
    volatile private Creature currentCreature = null;
    private AtomicInteger foodAmount = new AtomicInteger(0);
    private AtomicBoolean lock = new AtomicBoolean(false);
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
        return foodAmount.get();
    }

    synchronized public void lock(){
        this.lock.set(true);
    }
    synchronized public void unlock(){
        this.lock.set(false);
    }

    synchronized public boolean isLock() {
        return lock.get();
    }

    public void setCreature(Creature creature) {
        this.currentCreature = creature;
    }

    public void addFoodAmount(int foodAmount) {
        this.foodAmount.set(this.foodAmount.get() + foodAmount);
    }

    public void reduceFoodAmount() {
        this.foodAmount.decrementAndGet();
    }

    synchronized public void setLock(boolean lock) {
        this.lock.set(lock);
    }

    private void setFoodAmount(int amount) {
        this.foodAmount.set(amount);
    }

     public Cell copy() {
        Cell newCell = new Cell();
        newCell.setLock(this.lock.get());
        newCell.setCreature(this.currentCreature);
        newCell.setPosition(this.position);
        newCell.setFoodAmount(this.foodAmount.get());
        return newCell;
    }

}
