package com.codecool.controller;


import com.codecool.model.board.Board;
import com.codecool.model.board.Cell;
import com.codecool.model.creature.Subscriber;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

// TODO: remove unused methods
public class FoodDispenser extends Thread implements Subscriber {
    private AtomicBoolean isNewTurn = new AtomicBoolean(false);
    private Board board;
    private AtomicInteger height;
    private AtomicInteger width;
    private AtomicInteger startFoodQuantity;
    private AtomicInteger cauntTurn = new AtomicInteger(0);
    private AtomicBoolean isInit = new AtomicBoolean(true);


    public FoodDispenser(Board board) {
        this.board = board;
        this.height = new AtomicInteger(board.getHeight());
        this.width = new AtomicInteger(board.getWidth());
        this.startFoodQuantity = new AtomicInteger(height.get() * width.get()*3);
    }


    private void switchTurn() {
        isNewTurn.set(!isNewTurn.get());

    }

    public void onNotify(){

        if (cauntTurn.get() > 2) {
            switchTurn();
            cauntTurn.set(0);
        }
        cauntTurn.incrementAndGet();
    }

    public void run() {
        while(!this.isInterrupted()) {
            if (isInit.get()) {
                setFood(startFoodQuantity.get()/10);
                isInit.set(false);
            }
            if (isNewTurn.get()) {
                setFood(startFoodQuantity.get()/50);
                switchTurn();
            }
        }

    }

    // TODO: make it functional
    private void setFood(int foodQuantity) {
        Random generator = new Random();
        for (int i = 0; i < (foodQuantity); i++) {
            board.addFood(generator.nextInt(width.get()), generator.nextInt(height.get()));
        }
    }

    @Deprecated
    public double foodPercent() {
        double allCells = height.get() * width.get();
        int foodCells = board.countFoodCell();

        return foodCells/allCells;
    }

}
