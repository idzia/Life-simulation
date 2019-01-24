package com.codecool.controller;

import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.board.Board;
import com.codecool.model.board.Cell;
import com.codecool.model.creature.AbstractCreature;
import com.codecool.model.creature.Creature;
import com.codecool.model.creature.Subscriber;

public class ThreadsManager implements Subscriber {
    private Board board;

    public ThreadsManager(Board board) {
        this.board = board;
    }

    public Cell[][] cutBoard(Creature creature) {
        return board.getCellsFrom(creature.getPosition());
    }

    public Boolean isMoveValidLocked(Creature creature, Directions direction) {
        Position pos = creature.getPosition();
        Cell target = board.getNextCell(pos.getX(), pos.getY(), direction);
        return !target.isLock() || target.getCurrentCreature() != null;
    }

    private void removeDeadCreatures(){
        System.out.println("clear board from dead creatures");
        for (Cell[] row : board.getBoard()) {
            for(Cell cell : row) {
                Creature creature = cell.getCurrentCreature();
                if (creature != null && creature.isDead()) {
                    cell.setCreature(null);
                }
            }
        }
    }

    public synchronized boolean moveCreature(Creature creature, Directions direction){
        Position current = creature.getPosition();
        if (direction.equals(Directions.PASS)) {
            Cell c = board.getCell(current.getX(), current.getY());
            if (c.getFoodAmount() > 0) {
                creature.eat();
                c.reduceFoodAmount(1);
            }
            return true;
        }
        Cell target = this.board.getNextCell(current.getX(), current.getY(), direction);
        if (target.isLock()) {
            return false;
        } else {
            this.board.lockCell(this.board.getNextCell(current.getX(), current.getY(), direction));
            this.board.moveCreature(current, direction);
            this.board.getCell(current.getX(), current.getY()).unlock();
            return true;
        }
    }

    @Override
    public void onNotify() {
        removeDeadCreatures();
    }

    public void unlockDeadCell(AbstractCreature c) {
        Position current = c.getPosition();
        Cell target = this.board.getCell(current.getX(), current.getY());
        target.unlock();
    }
}
