package com.codecool.view.windowedview.display;

import com.codecool.view.windowedview.WindowHandler;
import com.codecool.view.windowedview.tiles.Tile;
import com.codecool.view.windowedview.tiles.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CamerMan {
    BufferedImage cursor = Assets.predator;
    WindowHandler handler;
    int x,y;
    int yMove, xMove;
    int speed = 5;
    int maxX, maxY;

    public CamerMan(WindowHandler handler, int x, int y) {
        this.handler = handler;
        this.x = (x-1)* Tile.TILEWIDTH + Tile.TILEWIDTH/4;
        this.y = (y-1)* Tile.TILEHEIGHT+ Tile.TILEHEIGHT/4;
        this.maxX = (handler.getBoardWidth()-1)* Tile.TILEWIDTH;
        this.maxY = (handler.getBoardHeight()-1)* Tile.TILEHEIGHT;
    }

    public void update() {
        getInput();
        move();
        //System.out.println("CAMERA X AND Y PIXEL:" + x+" "+y);
        handler.getGameCamera().centerOnEntity(this);
    }

    public void render(Graphics g) {
        g.drawImage(cursor, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), 32, 32, null);
    }


    public void move(){
            moveX();
            moveY();
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;


        if (this.x < 0) this.x = maxX;
        else if(this.x > maxX) this.x = 0;

        if (this.y < 0) this.y = maxY;
        else if(this.y > maxY) this.y = 0;

    }


    public void moveX(){
        x += xMove;
    }

    public void moveY(){
        y += yMove;
    }

    public float getX() { return x;
    }
    public float getY() {return y;}
}