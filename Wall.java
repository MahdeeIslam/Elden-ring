package edu.monash.fit2099.game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents a wall, no actor can pass through a wall
 * Created by:
 * @author Joshua Leong
 * Last Modified: 27/04/2023
 * @version 1.0.0
 * @see Ground
 */
public class Wall extends Ground{
    /**
     * Constructor for the Wall class
     */
    public Wall() {super ('#');}

    /**
     * Returns false as no actor can enter a wall
     * @param actor the actor to check
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
