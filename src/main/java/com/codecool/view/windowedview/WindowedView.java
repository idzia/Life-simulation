package com.codecool.view.windowedview;

import com.codecool.model.board.Board;
import com.codecool.view.View;
import com.codecool.view.windowedview.tiles.gfx.Assets;

public class WindowedView implements View  {

    WindowRunnable runnable;
    public WindowedView(Board board, int width, int height){
        Assets.init();
        runnable = new WindowRunnable(board,"Life-Simulator", width, height);
        Thread myThread = new Thread(runnable);
        runnable.start();



    }
}
