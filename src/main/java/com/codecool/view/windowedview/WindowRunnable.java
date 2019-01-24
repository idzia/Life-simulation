package com.codecool.view.windowedview;

import com.codecool.model.board.Board;
import com.codecool.view.windowedview.display.CamerMan;
import com.codecool.view.windowedview.display.Camera;
import com.codecool.view.windowedview.display.Display;
import com.codecool.view.windowedview.input.KeyManager;
import com.codecool.view.windowedview.tiles.Tile;
import com.codecool.view.windowedview.tiles.TileBoard;
import com.codecool.view.windowedview.tiles.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class WindowRunnable implements Runnable {

    private Display display;
    private int width, height;
    private String title;
    private TileBoard tiles;
    private boolean running = false;
    private Thread thread;
    private Board board;

    private BufferStrategy bs;
    private Graphics g;

    //Input
    private KeyManager keyManager;

    //Camera
    private Camera camera;
    private CamerMan camerMan;

    //WindowHandler
    private WindowHandler windowHandler;

    public WindowRunnable(Board board, String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        this.board = board;

        keyManager = new KeyManager();
    }

    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        windowHandler = new WindowHandler(this, width, height, board.getHeight(), board.getWidth());
        this.tiles = new TileBoard(board, windowHandler);
        camera = new Camera(windowHandler, 1, 1);
        this.camerMan = new CamerMan(windowHandler, 3,4);
    }
    public static void processInput(KeyManager input) {
        input.update();
    }

    private void update(){
        camerMan.update();
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here!
        tiles.render(g);
        camerMan.render(g);


        //End Drawing
        bs.show();
        g.dispose();
    }

    public void run(){

        init();
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){

            processInput(keyManager);
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                update();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                ticks = 0;
                timer = 0;
            }
        }

        stop();

    }

    public KeyManager getKeyManager(){
        return keyManager;
    }


    public Camera getCamera(){
        return camera;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public TileBoard getBoard() {
        return tiles;
    }
}
