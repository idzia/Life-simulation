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

    public void calculateDistance(Position position) {
        int distance = 0;
        List<Directions> direction = detrmineDirection(position);
        distance = calculate(position, direction, distance);

        System.out.println("FINAL dist: " + distance);

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
        if (currentPosition.getY() - position.getY() > 0) {
            directions.set(1, Directions.S);
        }
        if (currentPosition.getY() - position.getY() < 0) {
            directions.set(1, Directions.N);
        }
        return directions;
    }

    private int calculate(Position position, List<Directions> direction, int distance) {

        System.out.println("Current point: " + currentPosition.getX() + ", " + currentPosition.getY());
        System.out.println("Point to move: " + position.getX() + ", " + position.getY());
        System.out.println("Current distance: " + distance);
        String direct = direction.get(1).toString() + direction.get(0).toString();

        System.out.println(direct);

        if (direct.equals("PASSPASS")) {
            return distance;
        }
        else if (direction.contains(Directions.PASS)) {
            distance =+ (direction.get(0).equals(Directions.PASS )
                    ? Math.abs(currentPosition.getX() - position.getX()) : Math.abs(currentPosition.getY() - position.getY()));

            System.out.println("Final distance: " + distance);
            return distance;
        }
        else if (direct.equals("NW")) {
            Position movedPosition = getSouthEastIndex(position);
            return calculate(movedPosition, detrmineDirection(movedPosition), ++distance);
        }
        else if (direct.equals("NE")) {
            Position movedPosition = getSouthWestIndex(position);

            System.out.println("In NE mode, movedPos: x: " + movedPosition.getX() + " y: " + movedPosition.getY());

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

    public static void main(String[] args) {
        Position p = new Position(0,0);
        PositionController p1 = new PositionController(p);


        p1.calculateDistance(new Position(2,2));
        //p1.calculateDistance(new Position(0,-1));
    }
}
