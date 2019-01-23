package com.codecool.view.windowedview.tiles;

import com.codecool.model.board.Board;
import com.codecool.model.board.Cell;
import com.codecool.model.creature.Creature;
import com.codecool.model.creature.Herbivore;
import com.codecool.view.windowedview.WindowHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TileBoard {

    private int width, height;
    private Board board;
    private WindowHandler handler;

    public TileBoard(Board board, WindowHandler handler){

        this.handler = handler;
        this.width = 10;
        this.height = 10;
        this.board = board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void render(Graphics g){
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for(int y = yStart;y < yEnd;y++){
            for(int x = xStart;x < xEnd;x++){
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
    }

    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height)
            return TileFlyweightFactory.getTile(new ArrayList<>(Arrays.asList(0,0)));
        Cell current = board.getBoard()[y][x];
        Tile t = TileFlyweightFactory
                .getTile(
                        new ArrayList<>(Arrays.asList(
                                current.getFoodAmmount(),
                                getIntRepresentationOfCreature(current.getCurrentCreature()
                                )
                        ))
                );
        return t;
    }

    private int getIntRepresentationOfCreature(Creature currentCreature) {
    if(currentCreature == null)                  return 0;
    if(currentCreature instanceof Herbivore)    return 1;
                                                return 2;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public WindowHandler getHandler() {
        return handler;
    }

    public void setHandler(WindowHandler handler) {
        this.handler = handler;
    }
}
