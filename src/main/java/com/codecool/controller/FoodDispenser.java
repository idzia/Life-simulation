package com.codecool.controller;


import com.codecool.model.board.Board;
import com.codecool.model.board.Cell;
import com.codecool.model.creature.Subscriber;

import java.util.Random;

public class FoodDispenser extends Thread implements Subscriber {
    private volatile boolean isNewTurn = false;
    private Board board;
    private int height;
    private int width;
    int startFoodQuantity;
    int cauntTurn = 0;


    public FoodDispenser(Board board) {
        this.board = board;
        this.height = board.getHeight();
        this.width = board.getWidth();
        this.startFoodQuantity = height * width;

    }

    public void switchTurn() {
        isNewTurn = !isNewTurn;

    }

    public void onNotify(){

        if (cauntTurn > 5) {
            switchTurn();
            cauntTurn = 0;
        }
        cauntTurn++;
    }

    public void run() {
        setFood(startFoodQuantity/5);
        while(!this.interrupted()) {

            if (isNewTurn) {
                setFood(startFoodQuantity/10);
                switchTurn();
            }
        }
    }

    public void setFood(int foodQuantity) {
        Random generator = new Random();
        for (int i = 0; i < (foodQuantity); i++) {
            board.addFood(generator.nextInt(width), generator.nextInt(height));
        }
    }

    public double foodPercent() {
        double allCells = height * width;
        int foodCells = board.countFoodCell();

        return foodCells/allCells;
    }

}
