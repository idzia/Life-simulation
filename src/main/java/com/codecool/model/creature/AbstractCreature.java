package com.codecool.model.creature;

import com.codecool.SimulationConfig;
import com.codecool.controller.CreatureController;
import com.codecool.controller.ThreadsManager;
import com.codecool.model.Position;
import com.codecool.model.creature.strategy.BehavioralStrategy;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractCreature extends Thread implements Creature{
    private int energy = SimulationConfig.START_ENERGY;
    private static final int energyPerFood = SimulationConfig.ENERGY_PER_FOOD;
    private Position position;
    private BehavioralStrategy strategy;
    private AtomicBoolean doneMove = new AtomicBoolean(false);
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
        this.energy--;
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
        return doneMove.get();
    }

    public void setDoneMove(boolean doneMove) {
        this.doneMove.set(doneMove);
    }
}
