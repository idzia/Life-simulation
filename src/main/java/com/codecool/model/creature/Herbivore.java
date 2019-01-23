package com.codecool.model.creature;

import com.codecool.controller.BoardObserver;
import com.codecool.controller.ThreadsManager;
import com.codecool.model.Directions;
import com.codecool.model.creature.strategy.BehavioralStrategy;

public class Herbivore extends AbstractCreature implements Creature {

    public Herbivore(BehavioralStrategy strategy, ThreadsManager tm) {
        super(strategy,tm);
    }
    public void move() {
        System.out.println(this.toString()+"JA SIE ruszam");

        getManager().moveCreature(this, Directions.N);
        this.setDoneMove(true);
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
    }
    public void onNotify(){
        this.starve();
        this.setDoneMove(false);
    }
}
