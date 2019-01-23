package com.codecool;


import com.codecool.model.Simulation;
import com.codecool.model.board.Board;
import com.codecool.view.View;
import com.codecool.view.windowedview.WindowedView;

public class App {

    public static void main(String[] args){
        Board board =new Board();
        View view = new WindowedView(board);
        view.draw(board);

        new Simulation(view, board).run();
    }
}
