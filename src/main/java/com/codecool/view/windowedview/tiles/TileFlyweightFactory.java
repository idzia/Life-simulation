package com.codecool.view.windowedview.tiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TileFlyweightFactory {
    //[0]-food amount 0-10;
    //[1]0-no Creature 1-Herb 2-Pred

    private static Map<ArrayList<Integer>, Tile> tileCache = new HashMap<>();

    public static Tile getTile(ArrayList<Integer> params) {
        Tile computedTile = tileCache.computeIfAbsent(params, tile -> {
            Tile newTile = new Tile(params.get(0), params.get(1), params.get(2));
            return newTile;
        });
        return computedTile;
    }
}