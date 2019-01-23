package com.codecool.controller;

import com.codecool.model.Directions;
import com.codecool.model.Position;

import java.util.ArrayList;
import java.util.List;

public class PositionController {

    private Position currentPosition;

    public PositionController(Position position) {
        this.currentPosition = position;
    }

    public int calculateDistance(Position position) {
        return calculate(position, detrmineDirection(position), 0);
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Directions getDirections(Position position) {

        int WE = 0;
        int SN = 1;

        List<Directions> directions = detrmineDirection(position);

        if (directions.contains(Directions.PASS)) {
            for (Directions dir : directions) {
                if (!dir.equals(Directions.PASS)) {
                    return dir;
                }
            }
        }
        
        for (Directions dir : Directions.values()) {
            if (dir.equals(directions.get(SN).toString() + directions.get(WE).toString())) {
                return dir;
            }
        }


        return Directions.PASS;
    }

    private List<Directions> detrmineDirection(Position position) {
        List<Directions> directions = new ArrayList<Directions>();
        directions.add(Directions.PASS);
        directions.add(Directions.PASS);

        if (currentPosition.getX() - position.getX() > 0) {
            directions.set(0, Directions.W);
        }
        if (currentPosition.getX() - position.getX() < 0) {
            directions.set(0, Directions.E);
        }
        if (currentPosition.getY() - position.getY() < 0) {
            directions.set(1, Directions.S);
        }
        if (currentPosition.getY() - position.getY() > 0) {
            directions.set(1, Directions.N);
        }
        return directions;
    }

    private int calculate(Position position, List<Directions> direction, int distance) {
        String direct = direction.get(1).toString() + direction.get(0).toString();

        if (direct.equals("PASSPASS")) {
            return distance;
        }
        else if (direction.contains(Directions.PASS)) {
            return distance += (direction.get(0).equals(Directions.PASS )
                    ? Math.abs(currentPosition.getY() - position.getY())
                    : Math.abs(currentPosition.getX() - position.getX()));
        }
        else if (direct.equals("NW")) {
            Position movedPosition = getSouthEastIndex(position);
            return calculate(movedPosition, detrmineDirection(movedPosition), ++distance);
        }
        else if (direct.equals("NE")) {
            Position movedPosition = getSouthWestIndex(position);
            return calculate(movedPosition, detrmineDirection(movedPosition), ++distance);
        }
        else if (direct.equals("SW")) {
            Position movedPosition = getNorthEastIndex(position);
            return calculate(movedPosition, detrmineDirection(movedPosition), ++distance);
        }
        else if (direct.equals("SE")) {
            Position movedPosition = getNorthWestIndex(position);
            return calculate(movedPosition, detrmineDirection(movedPosition), ++distance);
        }
        return -1;

    }

    public Position getSouthWestIndex(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        pos.setX(--x);
        pos.setY(--y);
        return pos;
    }

    public Position getSouthEastIndex(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        pos.setX(++x);
        pos.setY(++y);
        return pos;
    }

    public Position getSouthIndex(Position pos) {
        int y = pos.getY();
        pos.setY(++y);
        return pos;
    }

    public Position getEastIndex(Position pos) {
        int x = pos.getX();
        pos.setX(++x);
        return pos;
    }

    public Position getWestIndex(Position pos) {
        int x = pos.getX();
        pos.setX(--x);
        return pos;
    }

    public Position getNorthWestIndex(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        pos.setX(--x);
        pos.setY(--y);
        return pos;
    }

    public Position getNorthEastIndex(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        pos.setX(++x);
        pos.setY(--y);
        return pos;
    }

    public Position getNorthIndex(Position pos) {
        int y = pos.getY();
        pos.setY(--y);
        return pos;
    }

}
