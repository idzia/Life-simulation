package com.codecool.view.windowedview;

import com.codecool.view.windowedview.display.Camera;
import com.codecool.view.windowedview.input.KeyManager;

public class WindowHandler {

    private Camera camera = new Camera(this, 0,0);
    private KeyManager keyManager = new KeyManager();
    private int width=1800,height=800;
    private WindowRunnable loop;


    public WindowHandler(WindowRunnable loop){
        this.loop = loop;
    }

    public Camera getGameCamera(){
        return camera;
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }


    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
