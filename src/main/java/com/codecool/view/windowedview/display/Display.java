package com.codecool.view.windowedview.display;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Display {

    private Frame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay(){
        frame = new Frame(title);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
                                    public void windowClosing(WindowEvent we) {
                                        System.exit(0);
                                    }
                                }
        );

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public Frame getFrame(){
        return frame;
    }

}