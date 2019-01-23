package com.codecool.controller;

import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.board.Board;
import com.codecool.model.board.Cell;
import com.codecool.model.creature.Creature;
import com.codecool.model.creature.Subscriber;

import java.util.List;
import java.util.Set;

public class ThreadsManager implements Subscriber {
    private Board board;
    Set<Creature> creatures;

    public ThreadsManager(Board board) {
        this.board = board;
    }

    public Cell[][] cutBoard(Creature creature) {
        return board.getCellsFrom(creature.getPosition());
    }

    void removeDeadCreatures(){
        //todo killthem all(remove dead threads)
    }
    synchronized boolean moveCreature(Creature creature, Directions direction){
        Position current = creature.getPosition();
        Cell target = this.board.getNextCell(current.getX(), current.getY(), direction);
        if (target.isLock()) {
            return false;
        } else {
            this.board.moveCreature(current, direction);
            return true;
        }
    }
    

    void addCreature(Creature creature){
        creatures.add(creature);
    }
    void addCreatures(List<Creature> creaturesList){
        creatures.addAll(creaturesList);
    }

    @Override
    public void onNotify() {
        removeDeadCreatures();
    }
}
