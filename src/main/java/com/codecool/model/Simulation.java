package com.codecool.model;

import com.codecool.controller.BoardObserver;
import com.codecool.controller.FoodDispenser;
import com.codecool.controller.ThreadsManager;
import com.codecool.model.board.Board;
import com.codecool.model.creature.Creature;
import com.codecool.model.creature.CreatureFactory;
import com.codecool.view.View;
import com.codecool.view.windowedview.WindowedView;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {
    private View view;
    private Board board;
    private BoardObserver observer;
    private boolean isRunning = true;
    private CreatureFactory creatureFactory;
    private ThreadsManager threadsManager;
    private FoodDispenser food;

    public Simulation(){
        this.isRunning = true;
        this.board = new Board();
        this.observer = new BoardObserver();
    }

    public void run() {
        this.threadsManager = new ThreadsManager(this.board);
        this.creatureFactory = new CreatureFactory(this.threadsManager);
        this.observer = new BoardObserver();
        List<Creature> creatures = this.creatureFactory.getCreatures(4);
        this.observer.subscribe(threadsManager);
        this.observer.subscribe(new ArrayList<>(creatures));

        board.initialize(10,10, 1);
        this.food = new FoodDispenser(this.board);
        this.observer.subscribe(food);

        board.populate(creatures);
        this.observer.init();
        this.view = new WindowedView(board, 500, 500);

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
                board.unlockCells();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                ticks = 0;
                timer = 0;
            }
        }
        System.out.println("finnish");
        observer.shout();
    }

    private boolean update() {
        observer.shout();
        return observer.isAliveCreature();
    }
}