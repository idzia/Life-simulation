package com.codecool.controller;

import com.codecool.model.creature.Creature;
import com.codecool.model.creature.Subscriber;

import java.util.ArrayList;
import java.util.List;

//TODO: change as many methods functional
//TODO: switch fields to Atomic
public class BoardObserver implements Observer {
    private List<Subscriber> subscribers;

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
}
