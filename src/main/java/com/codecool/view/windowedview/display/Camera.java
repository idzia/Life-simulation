package com.codecool.view.windowedview.display;

import com.codecool.view.windowedview.WindowHandler;
import com.codecool.view.windowedview.tiles.Tile;

public class Camera {
    public static final float DEFAULT_SPEED = 3.0f;


    private WindowHandler windowHandler;
    private float xOffset, yOffset;

    public Camera(WindowHandler windowHandler, float xOffset, float yOffset){
        this.windowHandler = windowHandler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void checkBlankSpace() throws NullPointerException{
        if(xOffset < 0){
            xOffset = 0;
        }else if(xOffset > windowHandler.getBoardWidth() * Tile.TILEWIDTH - windowHandler.getWidth()){
            xOffset = windowHandler.getBoardWidth() * Tile.TILEWIDTH - windowHandler.getWidth();
        }

        if(yOffset < 0){
            yOffset = 0;
        }else if(yOffset > windowHandler.getBoardHeight() * Tile.TILEHEIGHT - windowHandler.getHeight()){
            yOffset = windowHandler.getBoardHeight() * Tile.TILEHEIGHT - windowHandler.getHeight();
        }
    }

    public void centerOnEntity(CamerMan e){
        xOffset = e.getX() - windowHandler.getWidth() / 2 + 64 / 2;
        yOffset = e.getY() - windowHandler.getHeight() / 2 + 64 / 2;
        checkBlankSpace();
    }

    public void move(float xAmt, float yAmt){
        xOffset += xAmt;
        yOffset += yAmt;
        try{
        checkBlankSpace();} catch (NullPointerException e){
            System.out.println("board not yet created");
        }
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
