package com.codecool.controller;

import com.codecool.model.Directions;
import com.codecool.model.Position;
import com.codecool.model.board.Board;
import com.codecool.model.board.Cell;
import com.codecool.model.creature.AbstractCreature;
import com.codecool.model.creature.Creature;
import com.codecool.model.creature.CreatureFactory;
import com.codecool.model.creature.Subscriber;
import com.codecool.model.creature.strategy.StupidHerbivoreStrategy;

import java.util.ArrayList;
import java.util.List;

public class ThreadsManager implements Subscriber {
    volatile private Board board;
    private CreatureFactory factory;
    volatile  private Observer obs;

    public ThreadsManager(Board board, Observer obs) {
        this.obs = obs;
        this.board = board;
        this.factory = new CreatureFactory(this);
    }

    public Cell[][] cutBoard(Creature creature) {
        return board.getCellsFrom(creature.getPosition());
    }

    private void removeDeadCreatures(){
        for (Cell[] row : board.getBoard()) {
            for(Cell cell : row) {
                Creature creature = cell.getCurrentCreature();
                if (creature != null && creature.isDead()) {
                    cell.setCreature(null);
                    cell.unlock();
                }
            }
        }
    }

    public synchronized boolean moveCreature(Creature creature, Directions direction) {
        Position current = creature.getPosition();
        if (creature.getEnergy() >= 120) {
            spawnNewCreature(creature);
            return true;
        } else {
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
    }

    private void spawnNewCreature(Creature creature) {
        Cell cell = findCellToSpawn(board.getCellsFrom(creature.getPosition().getX(), creature.getPosition().getY(), 1, false));
        if (cell != null) {
            int energy = creature.getEnergy() / 2;
            Creature newCreature = factory.getCreature(new StupidHerbivoreStrategy());
            cell.setCreature(newCreature);
            newCreature.setPosition(cell.getPosition());
            newCreature.setEnergy(energy);
            creature.setEnergy(energy);
            startCreature((AbstractCreature) newCreature);
            this.obs.subscribe(newCreature);
        } else {
            creature.setEnergy(creature.getEnergy() / 2);
        }
    }

    private Cell findCellToSpawn(Cell[][] surrounding) {
        for (int i=0;i<surrounding.length;i++) {
            for(int j=0;j<surrounding[i].length;j++) {
                if (!surrounding[i][j].isLock()) {
                    surrounding[i][j].lock();
                    return surrounding[i][j];
                }
            }
        }
        return null;
    }

    public List<Creature> getCreatures(int amount) {
        List<Creature> creatures = new ArrayList<>();
        for (int i=0;i<amount;i++) {
            creatures.add(factory.getCreature(new StupidHerbivoreStrategy()));
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
        cow.setDaemon(true);
        cow.start();
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
