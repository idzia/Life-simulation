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
    private ThreadsManager threadsManager;
    private FoodDispenser food;

    public Simulation(){
        this.isRunning = true;
        this.board = new Board();
        this.observer = new BoardObserver();
    }

    public void run() {
        this.observer = new BoardObserver();
        this.threadsManager = new ThreadsManager(this.board, this.observer);
        List<Creature> creatures = this.threadsManager.getCreatures(40);
        this.observer.subscribe(threadsManager);
        this.observer.subscribe(new ArrayList<>(creatures));
        board.initialize(15,15, 1);
        this.food = new FoodDispenser(this.board);
        this.observer.subscribe(food);
        food.start();



        board.populate(creatures);
        this.threadsManager.startCreatures(creatures);
        this.view = new WindowedView(board, 1000, 1000);

        int fps = 1; //1x per sec
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;


        while(isRunning){

//            if(!observer.isAliveCreature()){
//                System.out.println("");
//                food.interrupt();
//                break;
//            }

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
        System.out.println("finnish");
        observer.shout();
    }

    private void update() {
        observer.shout();
        }
}