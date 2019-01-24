package com.codecool.model.creature;

import com.codecool.controller.ThreadsManager;
import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.creature.strategy.BehavioralStrategy;

import java.util.Random;

public abstract class AbstractCreature extends Thread implements Creature{
    private int energy = 10;
    private int energyPerFood = 5;
    private Position position;
    private BehavioralStrategy strategy;
    private boolean doneMove =false;
    private ThreadsManager manager;

    public AbstractCreature(BehavioralStrategy strategy, ThreadsManager manager){
        this.strategy = strategy;
        this.manager = manager;
    }

    // for test usage
    private Directions getRandomDirection() {
        Random random = new Random();
        switch (random.nextInt(7)) {
            case 0:
                return Directions.N;
            case 1:
                return Directions.E;
            case 2:
                return Directions.W;
            case 3:
                return Directions.S;
            default:
                    return Directions.PASS;
        }
    }

    public void move() {

        Directions direction = getRandomDirection();
            if (manager.moveCreature(this, direction)) {
                this.setDoneMove(true);
            }
    }

    @Override
    public void run() {
        while (!this.isDead()) {
            while (!this.isDoneMove()) {
                move();
            }
            try {
                sleep(0); //WTF
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //here we shoudl ask for lock remove.
        manager.unlockDeadCell(this);
    }

    public void onNotify(){
        this.starve();

        this.setDoneMove(false);
    }


    public void starve(int value){
        this.energy -= value;
    }
    public void starve(){
        this.energy --;
    }

    public int getEnergy() {
        return energy;
    }
    public boolean isDead() {
       return this.energy <= 0;
    }

    public synchronized void eat(){
        this.energy += energyPerFood;
        System.out.println("mniam");
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

    public boolean isDoneMove() {
        return doneMove;
    }

    public void setDoneMove(boolean doneMove) {
        this.doneMove = doneMove;
    }

    public ThreadsManager getManager() {
        return manager;
    }
}
