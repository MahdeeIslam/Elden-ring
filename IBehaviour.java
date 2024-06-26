package edu.monash.fit2099.game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An interface for behaviours
 * Created by:
 * @author Joshua Leong
 * Last Modified: 01/05/2023
 * @version 1.0.0
 */
public interface IBehaviour {
    /**
     * Gets the action to be executed
     * @param actor the actor to be acted upon
     * @param map the map the actor is on
     * @return the action to be executed
     */
    Action getAction(Actor actor, GameMap map);
}
