package edu.uchicago.gerber._08final.turbo.mvc.controller;

import edu.uchicago.gerber._08final.turbo.mvc.model.Movable;

import java.util.concurrent.LinkedBlockingDeque;



/**
 * Effectively a Queue that enqueues and dequeues Game Operations (add/remove).
 * enqueue() may be called by main and animation threads simultaneously, therefore we
 * use a data structure from the java.util.concurrent package.
 */
public class GameOpsQueue extends LinkedBlockingDeque<GameOp> {

    public void enqueue(Movable mov, GameOp.Action action) {
        addLast(new GameOp(mov, action));
    }

    // Enqueue for COLLIDE action
    public void enqueue(Movable movable, Movable other, GameOp.Action action) {
        addLast(new GameOp(movable, other, action)); // Add to the end of the deque
    }

    public GameOp dequeue() {
        return removeFirst();
    }
}
