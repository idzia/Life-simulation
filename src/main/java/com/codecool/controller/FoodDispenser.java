package com.codecool.controller;


import com.codecool.model.board.Board;
import com.codecool.model.board.Cell;

import java.util.Random;

public class FoodDispenser extends Thread {
    private volatile boolean isNewTurn = true;
    private Board board;
    private int height;
    private int width;
    int startFoodQuantity;


    public FoodDispenser(Board board) {
        this.board = board;
        this.height = board.getHeight();
        this.width = board.getWidth();
        this.startFoodQuantity = height * width;
    }

    public void switchTurn() {
        isNewTurn = !isNewTurn;
//        notify();
    }

    public void onNotify(){
        switchTurn();
    }

    public void run() {
        while(!this.interrupted()) {
            synchronized (this) {
                if (isNewTurn) {
                    if (foodPercent() == 0){
                        setFood(2*startFoodQuantity);
                    }
                    if (foodPercent()<0.15) {
                        setFood(startFoodQuantity/2);
                        System.out.println("food was added");
                    }
                    switchTurn();
                }
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
