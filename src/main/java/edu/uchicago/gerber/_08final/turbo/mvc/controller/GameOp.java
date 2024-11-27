package edu.uchicago.gerber._08final.turbo.mvc.controller;

import edu.uchicago.gerber._08final.turbo.mvc.model.Movable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;

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
    //this could also be a boolean, but we want to be explicit about what we're doing
    public enum Action {
        ADD {
            @Override
            public void execute(Movable movable, LinkedList<Movable> list) {
                // Add the movable to the list
                list.add(movable);
            }
        },
        REMOVE {
            @Override
            public void execute(Movable movable, LinkedList<Movable> list) {
                // Remove the movable from the list
                list.remove(movable);
            }
        },
        COLLIDE {
            @Override
            public void execute(Movable movable, LinkedList<Movable> list) {
                // Handle collision
                movable.collidingToFriend(list);
            }
        };

        // Abstract method to define action-specific behavior
        public abstract void execute(Movable movable, LinkedList<Movable> list);
    }

    //members
    private Movable movable;
    private Action action;
}
