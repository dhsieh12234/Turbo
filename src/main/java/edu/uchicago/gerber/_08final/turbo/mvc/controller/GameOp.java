package edu.uchicago.gerber._08final.turbo.mvc.controller;

import edu.uchicago.gerber._08final.turbo.mvc.model.Movable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 The GameOp (short for Game Operation) simply associates a movable with an action. Once
 the GameOpsQueue is processed it will add/remove movables from their appropriate list
 in the CommandCenter depending on the movable's team.
 */

//the lombok @Data gives us automatic getters and setters
@Data
//the lombok @AllArgsConstructor gives us an All-Args-Constructor :)
@AllArgsConstructor
public class GameOp {

    // Members
    private Movable movable;
    private Movable other; // Optional, to store the other object in a COLLIDE operation (can be null for ADD/REMOVE actions)
    private Action action;

    // Enum to specify the type of action
    public enum Action {
        ADD, REMOVE, COLLIDE
    }

    // Add a convenience constructor for ADD/REMOVE actions (single-movable operations)
    public GameOp(Movable movable, Action action) {
        this.movable = movable;
        this.other = null; // No other object involved
        this.action = action;
    }
}
