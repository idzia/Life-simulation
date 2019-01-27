package com.codecool.model.creature;

import com.codecool.controller.CreatureController;
import com.codecool.controller.ThreadsManager;
import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.creature.strategy.BehavioralStrategy;

//TODO: extract CreatureController
//TODO: switch fields to Atomic
//TODO: clear deprecated methods
public abstract class AbstractCreature extends Thread implements Creature{
    private int energy = 100;
    private int energyPerFood = 5;
    private Position position;
    private BehavioralStrategy strategy;
    volatile private boolean doneMove =false;
    private CreatureController controller;

    public AbstractCreature(BehavioralStrategy strategy, ThreadsManager manager){
        this.strategy = strategy;
        this.controller = new CreatureController(this, manager);
    }

    @Override
    public void run() {
        while (!this.isDead()) {
            while (!this.isDoneMove()) {
                controller.move();
            }
        }
        controller.unlockDeadCells();
    }

    public void onNotify(){
        this.starve();
        this.setDoneMove(false);
    }

    private void starve(){
        this.energy --;
    }

    public int getEnergy() {
        return energy;
    }

    @Override
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public boolean isDead() {
       return this.energy <= 0;
    }

    public synchronized void eat(){
        this.energy += energyPerFood;
    }

    public BehavioralStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(BehavioralStrategy strategy) {
        this.strategy = strategy;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    private boolean isDoneMove() {
        return doneMove;
    }

    public void setDoneMove(boolean doneMove) {
        this.doneMove = doneMove;
    }
}
