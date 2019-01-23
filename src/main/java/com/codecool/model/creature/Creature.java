package com.codecool.model.creature;

import com.codecool.model.Directions;
import com.codecool.model.Position;

public interface Creature {

    Directions move();
    Position getPosition();
    void setPosition(Position pos);
}
