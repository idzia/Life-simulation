package com.codecool.controller;

import com.codecool.model.Position;
import com.codecool.model.board.Board;

import javax.print.attribute.standard.Destination;

public class BoardController {

    public boolean moveCreature(Board board, Position pos, Destination dest) { return false; }

    private boolean isMoveValid(Board board, Position pos, Destination dest) { return false;}

    private void makeMove(Position pos, Destination dest) {}

    private void moveNorth(Position pos) {}

    private void moveNorthEast(Position pos) {}

    private void moveNorthWest(Position pos) {}

    private void moveSouth(Position pos) {}

    private void moveSouthEast(Position pos) {}

    private void moveSouthWest(Position pos) {}

    private void moveEast(Position pos) {}

    private void moveWest(Position pos) {}
}
