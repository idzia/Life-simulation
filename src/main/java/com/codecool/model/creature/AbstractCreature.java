package com.codecool.model.creature;

import com.codecool.model.Position;
import com.codecool.model.creature.strategy.BehavioralStrategy;

public abstract class AbstractCreature {
    private int energy = 100;
    Position position;
//    private BehavioralStrategy strategy;
    private int speed = 1;

//    public AbstractCreature(BehavioralStrategy strategy){
//        this.strategy = strategy;
//    }

    public AbstractCreature(){

    }

    public int getEnergy() {
        return energy;
    }
    public boolean isAlive() {
       return this.energy > 0;
    }

    public void eat(int value){
        this.energy += value;
    }
    public void starve(int value){
        this.energy -= value;
    }

//    public BehavioralStrategy getStrategy() {
//        return strategy;
//    }
//
//    public void setStrategy(BehavioralStrategy strategy) {
//        this.strategy = strategy;
//    }

    public int getSpeed() {
        return speed;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
