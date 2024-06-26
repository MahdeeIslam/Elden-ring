package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.players.Player;
import edu.monash.fit2099.game.reset.ResetManager;
import edu.monash.fit2099.game.reset.Resettable;

/**
 * A class that represents the Flask of Crimson Tears.
 * Technically Created By:
 * @author Mahdee Islam
 * Actually implemented By:
 * @author Benjamin Saunders
 * Last Modified: 02/05/2023
 * @see Item
 * @see Resettable
 */

public class FlaskOfCrimsonTears extends Item implements Resettable {
    private int charges;
    private final int initialCharges = 2;
    private final int healingAmount = 250;

    /**
     * Constructor.
     * Creates a Flask of Crimson Tears.
     */
    public FlaskOfCrimsonTears() {
        super("Flask of Crimson Tears", '.', false);
        this.charges = initialCharges;
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Returns if the Flask of Crimson Tears can be used.
     * @return true if the Flask of Crimson Tears can be used, false otherwise
     */
    public boolean hasCharges(){
        return charges > 0;
    }

    /**
     * Ensures the flask can never be dropped
     * @param actor The actor performing the action.
     * @return null
     */
    @Override
    public DropAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Resets the Flask of Crimson Tears to its initial state.
     * @param map The map the actor is on.
     */
    public void reset(GameMap map) {
        this.charges = initialCharges;
    }

    /**
     * Returns the current number of charges the flask has.
     * @return the current number of charges the flask has
     */
    public int getCurrentCharge(){
        return this.charges;
    }

    /**
     * Returns the initial number of charges the flask has.
     * @return the initial number of charges the flask has
     */
    public int getInitialCharges(){
        return this.initialCharges;
    }

    /**
     * Removes a charge from the flask.
     */
    public void removeCharge(){
        this.charges -= 1;
    }

    /**
     * Returns the amount of HP the flask heals.
     * @return the amount of HP the flask heals
     */
    public int getHealAmount(){
        return this.healingAmount;
    }
}
