package com.codecool.view.windowedview.tiles.gfx;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;
    private static final int FOOD_QUANTITY = 10;

    public static BufferedImage ground;
    public static BufferedImage[] food = new BufferedImage[FOOD_QUANTITY];
    public static BufferedImage herbivore, predator,none;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/TileSet.png"));
        ground = sheet.crop(0, 0, width, height);

        sheet = new SpriteSheet(ImageLoader.loadImage("/SpriteSheet.png"));

        for(int i=0; i<FOOD_QUANTITY; i++){
        food[i] = sheet.crop(width * i, height * 0, width, height);
        }

        herbivore = sheet.crop(width * 0, height*1, width, height);
        predator = sheet.crop(width * 1, height*1, width, height);
        none = sheet.crop(width * 3, height*1, width, height);
    }
}