package edu.monash.fit2099.game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * FollowBehaviour which facilitates the enemies automatically following the players (very similar to Adrian's)
 * Created By:
 * @author Joshua Leong
 * Edited By:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @see IBehaviour
 */
public class FollowBehaviour implements IBehaviour{
    /**
     * The Actor that is to be followed
     */
    private final Actor target;

    /**
     * Constructor
     *
     * @param target the Actor that is to be followed
     */
    public FollowBehaviour(Actor target){
        this.target = target;
    }


    /**
     * Gets the current location of the actor and gets the target location of the end point.
     * It does a distance formula, then receives a new end point to follow.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return Action to be executed
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (!map.contains(target) || !map.contains(actor)) {
            return null;
        }

        Location actorCurrent = map.locationOf(actor);
        Location targetCurrent = map.locationOf(getTarget());

        int currentDiffDistance = distanceBetween(actorCurrent, targetCurrent);

        for (Exit exit : actorCurrent.getExits()) {
            Location endPoint = exit.getDestination();

            if(endPoint.canActorEnter(actor)){
                int newDistance = distanceBetween(endPoint, targetCurrent);
                if(newDistance < currentDiffDistance) {
                    return new MoveActorAction(endPoint, "around", exit.getName());
                }
            }

        }

        return null;

    }

    /**
     * Gets the distance between two locations
     *
     * @param X the first location
     * @param Y the second location
     * @return the distance between the two locations
     */
    private int distanceBetween (Location X, Location Y){
        return Math.abs(X.x() - Y.x()) + Math.abs(X.y() - Y.y());
    }

    /**
     * Gets the target
     *
     * @return the target
     */
    public Actor getTarget() {
        return target;
    }
}
