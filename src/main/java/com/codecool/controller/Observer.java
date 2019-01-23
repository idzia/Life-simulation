package com.codecool.controller;

import com.codecool.model.creature.Subscriber;

public interface Observer {

    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void shout();

}
