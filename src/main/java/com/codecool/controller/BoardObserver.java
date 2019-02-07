package com.codecool.controller;

import com.codecool.model.creature.Creature;
import com.codecool.model.creature.Subscriber;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

//TODO: change as many methods functional
public class BoardObserver implements Observer {
    private Queue<Subscriber> subscribers;

    public BoardObserver() {
        this.subscribers = new ConcurrentLinkedQueue<>();
    }

    @Override
     public void subscribe(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

     public void subscribe(Queue<Subscriber> subscribers) {
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
