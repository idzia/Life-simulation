package com.codecool.model.creature;

import com.codecool.model.Directions;
import com.codecool.model.Position;

public interface Creature extends Subscriber {

    void move();
    Position getPosition();
    void setPosition(Position pos);
    boolean isDead();
    void eat();
    int getEnergy();
    void setEnergy(int energy);
}
