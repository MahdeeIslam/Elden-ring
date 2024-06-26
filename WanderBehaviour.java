package edu.monash.fit2099.game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.Random;

/**
 * A behaviour that allows the actor to wander around the map.
 * Created by:
 * @author Joshua Leong
 * Edited by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.2.0
 * @see Action
 * @see IBehaviour
 */
public class WanderBehaviour implements IBehaviour{
    /** A random number generator */
    static private final Random random = new Random();
    static private int nextNumber(int x){
        return random.nextInt(x);
    }

    /**
     * Returns the action to move in a random direction
     * @param actor the actor performing the action
     * @param map the map the actor is on
     * @return the MoveACtorAction for a random direction
     */
    // allows the actor to get a random movement for wandering.
    @Override
    public Action getAction(Actor actor, GameMap map) {

        ArrayList<Action> actionList = new ArrayList<>();

        // add all possible actions to the list
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location target = exit.getDestination();
            if (target.canActorEnter(actor)) {
                actionList.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }

        // return a random action from the list
        if (!actionList.isEmpty()) {
            return actionList.get(nextNumber(actionList.size()));
        } else {
            return null;
        }
    }
}
