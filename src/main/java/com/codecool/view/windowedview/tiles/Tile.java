package com.codecool.view.windowedview.tiles;

import com.codecool.view.windowedview.tiles.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile implements TileInterface {
    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
    protected BufferedImage background = Assets.ground;
    protected BufferedImage food;
    protected BufferedImage creature;

    public Tile(int foodQuantity, int creatureType){

        getFoodAsset(foodQuantity);
        getCreatureAsset(creatureType);

    }

    private void getCreatureAsset(int creatureType) {
        switch (creatureType) {
            case 1:
                creature = Assets.herbivore;
                break;
            case 2:
                creature = Assets.predator;
                break;
            default:
                creature = Assets.none;
        }
    }

    private void getFoodAsset(int foodQuantity) {
        switch (foodQuantity) {
            case 0:
                food = Assets.none;
                break;
            case 1:
                food = Assets.food[0];
                break;
            case 2:
                food = Assets.food[1];
                break;
            case 3:
                food = Assets.food[2];
                break;
            case 4:
                food = Assets.food[3];
                break;
            case 5:
                food = Assets.food[4];
                break;
            case 6:
                food = Assets.food[5];
                break;
            case 7:
                food = Assets.food[6];
                break;
            case 8:
                food = Assets.food[7];
                break;
            case 9:
                food = Assets.food[8];
                break;
            default:
                food = Assets.food[9];
        }
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(background, x, y, TILEWIDTH, TILEHEIGHT, null);
        g.drawImage(food, x, y, TILEWIDTH, TILEHEIGHT, null);
        g.drawImage(creature, x, y, TILEWIDTH, TILEHEIGHT, null);


    }
}