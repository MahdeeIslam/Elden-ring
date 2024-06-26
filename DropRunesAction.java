package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.RuneManager;
import edu.monash.fit2099.game.items.Runes;

/**
 * Much like DropItemAction, but drops runes instead of items
 * Created By:
 * @author Benjamin Saunders
 * Last Modified: 02/05/2023
 * @see edu.monash.fit2099.engine.items.DropItemAction
 * @see edu.monash.fit2099.game.items.Runes
 * @see DropAction
 */
public class DropRunesAction extends DropAction {

    private int value;

    /**
     * Constructor.
     *
     * @param value the number of runes to drop
     */
    public DropRunesAction(int value) {
        super(new Runes(value));
        this.value = value;
    }

    /**
     * Returns a description of the action suitable to display in the menu.
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player drops 10 runes"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drops " + value + " runes";
    }

    /**
     * Removes the runes from the actor's inventory, then drops the item on the ground
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the drop action, after calling the superclasses constructor execute method aswell.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager.getInstance().removeRunes(actor, value);
        return super.execute(actor, map);
    }
}
