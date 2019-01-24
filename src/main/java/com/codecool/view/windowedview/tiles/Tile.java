package com.codecool.view.windowedview.tiles;

import com.codecool.view.windowedview.tiles.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile implements TileInterface {
    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
    protected BufferedImage background = Assets.ground;
    protected BufferedImage food;
    protected BufferedImage creature;
    protected BufferedImage lock = Assets.none;
    protected BufferedImage finalTile;

    public Tile(int foodQuantity, int creatureType, int lock){

        getFoodAsset(foodQuantity);
        getCreatureAsset(creatureType);
        getLockAsset(lock);
        finalTile = joinBufferedImage(background,food,this.lock,creature);

    }

    private void getLockAsset(int lock) {
        switch (lock) {
            case 1:
                this.lock = Assets.lock;
                break;
            default:
                this.lock = Assets.none;
        }
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(finalTile, x, y, TILEWIDTH, TILEHEIGHT, null);


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

    public static BufferedImage joinBufferedImage(BufferedImage ... images) {

        BufferedImage newImage = new BufferedImage(32, 32,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        for(BufferedImage image: images) {
            g2.drawImage(image, null, 0, 0);
        }
        g2.dispose();
        return newImage;
    }
}