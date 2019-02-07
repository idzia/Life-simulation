package com.codecool.controller;

import com.codecool.SimulationConfig;
import com.codecool.model.board.Board;
import com.codecool.model.creature.Creature;
import com.codecool.view.View;
import com.codecool.view.windowedview.WindowedView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

//TODO: use configuration class or file
public class Simulation implements Runnable {
    private View view;
    private Board board;
    private BoardObserver observer;
    private boolean isRunning;
    private ThreadsManager threadsManager;

    public Simulation(){
        this.isRunning = true;
        this.board = new Board();
        this.observer = new BoardObserver();
        this.threadsManager = new ThreadsManager(this.board, this.observer);
    }

    public void run() {
        List<Creature> creatures = this.threadsManager.getCreatures(SimulationConfig.START_AMOUNT_OF_CREATURES);
        this.observer.subscribe(new ConcurrentLinkedQueue<>(creatures));
        this.board.initialize(SimulationConfig.BOARD_WIDTH,SimulationConfig.BOARD_HEIGHT);
        FoodDispenser food = new FoodDispenser(this.board);
        this.observer.subscribe(food);
        food.start();

        board.populate(creatures);
        this.threadsManager.startCreatures(creatures);
        this.view = new WindowedView(board, SimulationConfig.WINDOW_WIDTH, SimulationConfig.WINDOW_HEIGHT);

        int fps = SimulationConfig.ROUNDS_PER_SEC; //1x per sec
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
                update();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                ticks = 0;
                timer = 0;
            }
        }
        observer.shout();
    }

    private void update() {
        observer.shout();
        }
}