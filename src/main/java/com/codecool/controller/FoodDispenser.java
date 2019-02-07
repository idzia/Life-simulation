package com.codecool.controller;


import com.codecool.model.creature.Subscriber;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

// TODO: remove unused methods
public class FoodDispenser extends Thread implements Subscriber {
    private AtomicBoolean isNewTurn = new AtomicBoolean(false);
    private BoardController boardController;
    private int height;
    private int width;
    private final int startFoodQuantity;
    private AtomicInteger countTurn = new AtomicInteger(0);
    private AtomicBoolean isInit = new AtomicBoolean(true);


    public FoodDispenser(BoardController boardController) {
        this.boardController = boardController;
        this.height = boardController.getBoard().getHeight();
        this.width = boardController.getBoard().getWidth();
        this.startFoodQuantity = height * width*3;
    }


    private void switchTurn() {
        isNewTurn.set(!isNewTurn.get());
    }

    public void onNotify(){

        if (countTurn.get() > 2) {
            switchTurn();
            countTurn.set(0);
        }
        countTurn.incrementAndGet();
    }

    public void run() {
        while(!this.isInterrupted()) {
            if (isInit.get()) {
                setFood(startFoodQuantity/10);
                isInit.set(false);
            }
            if (isNewTurn.get()) {
                setFood(startFoodQuantity/50);
                switchTurn();
            }
        }

    }

    // TODO: make it functional
    private void setFood(int foodQuantity) {
        Random generator = new Random();
        for (int i = 0; i < (foodQuantity); i++) {
            boardController.addFood(generator.nextInt(width), generator.nextInt(height));
        }
    }
}
