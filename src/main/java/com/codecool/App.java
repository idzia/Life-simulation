package com.codecool;

import com.codecool.model.Simulation;
import com.codecool.view.terminalview.TerminalView;

public class App {



    public static void main(String[] args)
    {

        new Simulation(new TerminalView()).run();
    }
}
