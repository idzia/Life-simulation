package com.codecool.view.windowedview;

import com.codecool.view.windowedview.display.Camera;
import com.codecool.view.windowedview.input.KeyManager;
import com.codecool.view.windowedview.tiles.TileBoard;

public class WindowHandler {

    private final TileBoard tileboard;
    private int width,height, boardWidth, boardHeight;
    private WindowRunnable windowRunnable;


    public WindowHandler(WindowRunnable windowRunnable, int width, int height, int boardWidth, int boardHeight){
        this.windowRunnable = windowRunnable;
        this.width = width;
        this.height = height;
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.tileboard = windowRunnable.getBoard();
    }

    public Camera getGameCamera(){
        return windowRunnable.getCamera();
    }

    public KeyManager getKeyManager(){
        return windowRunnable.getKeyManager();
    }


    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public TileBoard getTileboard() {return tileboard;}

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }
}
