package com.codecool.model.creature;

import com.codecool.controller.BoardObserver;

public interface Subscriber {

    void onNotify();

    void Subscribe(BoardObserver boardObserver);
}
