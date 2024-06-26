package edu.monash.fit2099.game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actions.DespawnAction;
import edu.monash.fit2099.game.actions.DropRunesAction;
import edu.monash.fit2099.game.actions.PickUpRunesAction;
import edu.monash.fit2099.game.reset.ResetManager;
import edu.monash.fit2099.game.reset.Resettable;

/**
 * A class to represent the runes that a player has, its sole purpose is to be able to be dropped
 * Technically created by:
 * @author Mahdee Islam
 * Actually implemented by:
 * @author Benjamin Saunders
 * Last Modified: 02/05/2023
 * @see Item
 * @see Resettable
 */
public class Runes extends Item implements Resettable {


    private int value;
    private boolean endOfLife;
    private Location location;
    /**
     * Constructor.
     * Creates a Rune.
     * @param value The value of the rune
     */
    public Runes(int value) {
        super("Runes", '$', true);
        this.value = value;
        this.endOfLife = false;
        this.location = null;

        ResetManager.getInstance().registerRune(this);
    }

    /**
     * Returns the action for this rune to be dropped on the floor
     * @param location The location to drop the rune
     * @return The action to drop the rune
     */
    public Action drop(Location location) {
        this.location = location;
        return new DropItemAction(this);
    }

    /**
     * Executes the reset method for the runes on the floor (will only run when the player dies)
     * @param map The map the actor is on.
     */
    @Override
    public void reset(GameMap map) {
        if (this.endOfLife) {
            this.location.removeItem(this);
        } else {
            this.endOfLife = true;
        }
    }

    /**
     * Returns the action for this rune to be picked up
     * @param actor The actor picking up the rune
     * @return The action to pick up the rune
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        return new PickUpRunesAction(this);
    }

    /**
     * Returns the action for this rune to be dropped on the floor
     * @param actor The actor dropping the rune
     * @return The action to drop the rune
     */
    @Override
    public DropAction getDropAction(Actor actor) {
        return new DropRunesAction(this.value);
    }


    /**
     * Returns the value of the rune
     * @return The value of the rune
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the rune
     * @param value The value of the rune
     */
    public void setValue(int value) {
        this.value = value;
    }
}
