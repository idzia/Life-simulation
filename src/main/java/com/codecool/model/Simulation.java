package com.codecool.model;

import com.codecool.controller.BoardObserver;
import com.codecool.model.board.Board;
import com.codecool.view.View;

public class Simulation implements Runnable {
    private View view;
    private Board board;
    private BoardObserver observer;
    private boolean isRunning;

    public Simulation(View view){
        this.isRunning = true;
        this.view = view;
        this.board = new Board();
        this.observer = new BoardObserver();

    }

    public void run() {
        board.initialize(100,100,2);
        while(isRunning){
            isRunning = update();
            render();
        }
    }

    private void render() {
    }

    private boolean update() {
        return true;
       //todo: notifyAll();
    }
}