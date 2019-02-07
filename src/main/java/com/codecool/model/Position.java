package com.codecool.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Position {
    private AtomicInteger x;
    private AtomicInteger y;

    public Position() {
        this.x = new AtomicInteger();
        this.y = new AtomicInteger();
    }

    public Position(int y, int x) {
        this.x = new AtomicInteger(x);
        this.y = new AtomicInteger(y);
    }

    public int getX() {
        return x.get();
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public int getY() {
        return y.get();
    }

    public void setY(int y) {
        this.y.set(y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x.get() +
                ", y=" + y.get() +
                '}';
    }
}
