package edu.monash.fit2099.game.grounds;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.game.utils.State;

/**
 * A class that represents the floor, enemies cannot spawn on the floor
 * Created by:
 * @author Joshua Leong
 * Modified By:
 * @author Benjamin Saunders
 * Last Modified: 27/04/2023
 * @version 1.0.0
 * @see Ground
 */
public class Floor extends Ground {

    /**
     * Constructor for the Floor class
     */
    public Floor() {
        super('_');
    }

    /**
     * Returns true if the actor is not an enemy
     * @param actor the actor to check
     * @return true if the actor is not an enemy
     */
    @Override
    public boolean canActorEnter(Actor actor) {
    	return !actor.hasCapability(State.IS_ENEMY);
    }

}