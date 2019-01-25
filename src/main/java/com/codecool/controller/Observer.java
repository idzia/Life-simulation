package com.codecool.controller;

import com.codecool.model.creature.Subscriber;

// TODO: make more observers (more precise)
public interface Observer {

    void subscribe(Subscriber subscriber);

    void unsubscribe(Subscriber subscriber);
    void shout();

    boolean isAliveCreature();
}
