package com.codecool.model.creature.strategy;

import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.board.Cell;

public interface BehavioralStrategy {

    Directions suggestMove();
    void setBoardView(Cell[][] board);
    void setCoordinates(Position currentPosition);
    void update(Cell[][] board);
}
