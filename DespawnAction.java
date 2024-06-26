package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * An action executed if an actor is despawned.
 * Created By:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @see Action
 */

public class DespawnAction extends Action {

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";

        // remove actor
        map.removeActor(target);
        result = menuDescription(target);
        return result;
    }

    /**
     * Returns a string describing the action suitable for displaying in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player does nothing"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has despawned";
    }
}
