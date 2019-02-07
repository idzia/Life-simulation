package com.codecool.controller;

import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.board.Cell;
import com.codecool.model.creature.AbstractCreature;
import com.codecool.model.creature.Creature;
import com.codecool.model.creature.CreatureBuilder;

import java.util.ArrayList;
import java.util.List;

public class ThreadsManager {
    volatile private BoardController boardController;
    private CreatureBuilder creatureBuilder;
    volatile  private Observer obs;

    public ThreadsManager(BoardController boardController, Observer obs) {
        this.obs = obs;
        this.boardController = boardController;
        this.creatureBuilder = new CreatureBuilder(this);
    }

    public Cell[][] cutBoard(Creature creature) {
        return boardController.getCellsFrom(creature.getPosition());
    }

    public synchronized boolean moveCreature(Creature creature, Directions direction) {
        Position current = creature.getPosition();
        if (creature.getEnergy() >= 120) {
            splitCreature(creature);
            return true;
        } else {
            if (direction.equals(Directions.PASS)) {
                handleEating(creature, current);
                return true;
            }
            return makeMove(direction, current);
        }
    }

    private void handleEating(Creature creature, Position current) {
        Cell c = boardController.getCell(current);
        if (c.getFoodAmount() > 0) {
            creature.eat();
            c.reduceFoodAmount();
        }
    }

    private boolean makeMove(Directions direction, Position current) {
        Cell target = this.boardController.getNextCell(current.getX(), current.getY(), direction);
        if (target.isLock()) {
            return false;
        } else {
            this.boardController.lockCell(this.boardController.getNextCell(current.getX(), current.getY(), direction));
            this.boardController.moveCreature(current, direction);
            this.boardController.getCell(current).unlock();
            return true;
        }
    }

    private void splitCreature(Creature creature) {
        Cell cell = findCellToSpawn(boardController.getCellsFrom(creature.getPosition().getX(), creature.getPosition().getY(), 1, false));
        if (cell != null) {
            spawnNewCreature(creature, cell);
        } else {
            creature.setEnergy(creature.getEnergy() / 2);
        }
    }

    private void spawnNewCreature(Creature creature, Cell cell) {
        int energy = creature.getEnergy() / 2;
        Creature newCreature = creatureBuilder.getCreature();
        cell.setCreature(newCreature);
        newCreature.setPosition(cell.getPosition());
        newCreature.setEnergy(energy);
        creature.setEnergy(energy);
        startCreature((AbstractCreature) newCreature);
        this.obs.subscribe(newCreature);
    }

    // TODO: handle this with Stream
    private Cell findCellToSpawn(Cell[][] surrounding) {
        for (Cell[] cells : surrounding) {
            for (Cell cell : cells) {
                if (!cell.isLock()) {
                    cell.lock();
                    return cell;
                }
            }
        }
        return null;
    }

    public List<Creature> getCreatures(int amount) {
        List<Creature> creatures = new ArrayList<>();
        for (int i=0;i<amount;i++) {
            creatures.add(creatureBuilder.getCreature());
        }
        return creatures;
    }

    public void startCreatures(List<Creature> creatures) {
        for(Creature creature : creatures){
            startCreature((AbstractCreature) creature);
        }
    }

    private synchronized void startCreature(AbstractCreature creature) {
        Thread cow = new Thread(creature);
        cow.setPriority(1);
        cow.start();
    }

    public void unlockDeadCell(AbstractCreature c) {
        Position current = c.getPosition();
        Cell target = this.boardController.getCell(current);
        target.unlock();
        target.setCreature(null);
    }
}
