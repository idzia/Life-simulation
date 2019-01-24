package com.codecool.controller;

import com.codecool.model.creature.AbstractCreature;
import com.codecool.model.creature.Creature;
import com.codecool.model.creature.Herbivore;
import com.codecool.model.creature.Subscriber;
import javafx.beans.InvalidationListener;

import java.util.ArrayList;
import java.util.List;

public class BoardObserver implements Observer {
    volatile private List<Subscriber> subscribers;

    public BoardObserver() {
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }
    public void subscribe(List<Subscriber> subscribers) {
        for (Subscriber subscriber: subscribers) {
            subscribe(subscriber);

        }
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    @Override
    public void shout() {
        for (Subscriber s : this.subscribers) {
            s.onNotify();
        }
    }

    @Override
    public boolean isAliveCreature() {
        for (Subscriber s : subscribers) {
            if (s instanceof Creature) {
                if (!((Creature) s).isDead()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void init() {
        for(Subscriber sub : subscribers){
            if(sub instanceof Creature) {
                Thread cow = new Thread((AbstractCreature)sub);
                cow.setDaemon(true);
                cow.start();

            }
        }
    }
}
