package edu.monash.fit2099.game.utils;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that contains utility methods.
 * Created by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 */
public class Utils {

    /**
     * Checks if an actor can attack another actor.
     * @param target The actor to be attacked.
     * @param self The actor that is attacking.
     * @return True if the actor can attack the other actor, false otherwise.
     */
    public static boolean canAttack(Actor target, Actor self) {
        boolean playerCheck = (target.hasCapability(State.IS_PLAYER) && self.hasCapability(State.HOSTILE_TO_PLAYER));
        boolean enemyCheck = (target.hasCapability(State.IS_ENEMY) && self.hasCapability(State.HOSTILE_TO_ENEMY));
        boolean typeCheck = false;
        for (EnemyType type : EnemyType.values()) {
            if (target.hasCapability(type) && self.hasCapability(type)) {
                typeCheck = true;
                break;
            }
        }
        return playerCheck || (enemyCheck && !typeCheck);
    }

    /**
     * Calculates the map side (EAST or WEST) of a location.
     * @param location The location to be checked.
     * @return The map side of the location.
     */
    public static MapSide calculateMapSide(Location location) {
        int x = location.x();
        int width = location.map().getXRange().max();

        if (x < width / 2) {
            return MapSide.WEST;
        } else {
            return MapSide.EAST;
        }

    }
}
