package com.codecool.view.windowedview.display;

import com.codecool.view.windowedview.WindowHandler;

public class Camera {
    public static final float DEFAULT_SPEED = 3.0f;


    private WindowHandler windowHandler;
    private float xOffset, yOffset;

    public Camera(WindowHandler windowHandler, float xOffset, float yOffset){
        this.windowHandler = windowHandler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void checkBlankSpace(){
        if(xOffset < 0){
            xOffset = 0;
 //       }else if(xOffset > windowHandler.getBoard().getWidth() * Tile.TILEWIDTH - windowHandler.getWidth()){
 //           xOffset = windowHandler.getLevel1().getWidth() * Tile.TILEWIDTH - windowHandler.getWidth();
        }

        if(yOffset < 0){
            yOffset = 0;
 //       }else if(yOffset > windowHandler.getLevel1().getHeight() * Tile.TILEHEIGHT - windowHandler.getHeight()){
 //           yOffset = windowHandler.getLevel1().getHeight() * Tile.TILEHEIGHT - windowHandler.getHeight();
        }
    }

    public void centerOn(int x,int y){
        xOffset = x - windowHandler.getWidth() / 2;
        yOffset = y - windowHandler.getHeight() / 2;
        //checkBlankSpace();
    }

    public void move(float xAmt, float yAmt){
        xOffset += xAmt;
        yOffset += yAmt;
        //checkBlankSpace();
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
