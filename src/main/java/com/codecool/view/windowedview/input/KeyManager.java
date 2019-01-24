package com.codecool.view.windowedview.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    private boolean aUp, aDown, aLeft, aRight;

    public KeyManager() {
        keys = new boolean[256];
    }

    public void update() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
    }

    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e) {

    }
}