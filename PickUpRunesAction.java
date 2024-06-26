package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.RuneManager;
import edu.monash.fit2099.game.items.Runes;

/**
 * Much like PickUpItemAction, but picks up runes instead of items
 * Created By:
 * @author Benjamin Saunders
 * Last Modified: 02/05/2023
 * @see edu.monash.fit2099.engine.items.PickUpItemAction
 * @see edu.monash.fit2099.game.items.Runes
 * @see PickUpAction
 */
public class PickUpRunesAction extends PickUpAction {
    private final Runes runes;
    /**
     * Constructor.
     *
     * @param runes the runes to pick up
     */
    public PickUpRunesAction(Runes runes) {
        super(runes);
        this.runes = runes;
    }


    /**
     * Removes the runes from the ground, then adds them to the actor's inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String, e.g. "Player picks up 10 runes"
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager.getInstance().addRunes(actor, runes.getValue());
        return super.execute(actor, map);
    }

    /**
     * Returns a description of the action suitable to display in the menu.
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player picks up 10 runes"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up " + runes.getValue() + " runes";
    }

}
