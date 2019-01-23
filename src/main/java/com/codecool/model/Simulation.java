package com.codecool.model;

import com.codecool.controller.BoardObserver;
import com.codecool.model.board.Board;
import com.codecool.view.View;

public class Simulation implements Runnable {
    private View view;
    private Board board;
    private BoardObserver observer;
    private boolean isRunning = true;

    public Simulation(View view){
        this.isRunning = true;
        this.view = view;
        this.board = new Board();
        this.observer = new BoardObserver();

    }

    public Simulation(View view, Board board) {
        this.board = board;
        this.view = view;
    }

    public void run() {

        board.initialize(10,10,4);

        int fps = 1; //1x per sec
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;


        while(isRunning){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if(delta >= 1){
                isRunning = update();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                ticks = 0;
                timer = 0;
            }
        }
    }

    private boolean update() {
        //todo:
        //check if at least one creature is alive if not return nope
        //redistribute food
        //notifyall(turn)

        return true;
    }
}