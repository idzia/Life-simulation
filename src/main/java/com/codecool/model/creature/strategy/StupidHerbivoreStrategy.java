package com.codecool.model.creature.strategy;

import com.codecool.controller.PositionController;
import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.board.Cell;

import java.util.*;

//TODO: remove unused methods
public class StupidHerbivoreStrategy extends HerbivoreStrategy {


    public StupidHerbivoreStrategy() {
        super();
    }

    @Override
    Directions chooseOtherDirection() {
        return chooseRandomDirection();
    }

    @Override
    Directions chooseBestWithFood() {
        int min = Collections.min(getTargets().values());
        Cell cell = getCellByDiastance(min);
        return getPositionController().getDirections(cell.getPosition());

    }

    private Cell[][] createTestBoard() {
        Cell[][] board = new Cell[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j< 3; j++) {
                Cell c = new Cell();
                c.setPosition(new Position(j, i));
                board[i][j] = c;
            }
        }

        Cell cSuper = new Cell();
        Cell cSuper2 = new Cell();

        cSuper.addFoodAmount(5);
        cSuper.setPosition(new Position(1,1));
        cSuper2.addFoodAmount(2);
        cSuper2.setPosition(new Position(1,0));


        board[1][1] = cSuper;
        board[0][1] = cSuper2;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j< 3; j++) {
            }
        }
        return board;
    }
}
