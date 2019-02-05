package com.codecool.model.creature;

import com.codecool.model.Position;
import com.codecool.model.creature.strategy.BehavioralStrategy;

public interface Creature extends Subscriber {

    BehavioralStrategy getStrategy();
    Position getPosition();
    void setPosition(Position pos);
    boolean isDead();
    void eat();
    int getEnergy();
    void setEnergy(int energy);
}
