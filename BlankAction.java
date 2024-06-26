package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An action that does nothing with custom text
 * Created By:
 * @author Benjamin Saunders
 * Last Modified: 1/05/2023
 * @see Action
 * @version 1.0.0
 */
public class BlankAction extends Action {
    /**
     * The message to be displayed
     */
    private String message;

    /**
     * Constructor.
     *
     * @param message the message to be displayed
     */
    public BlankAction(String message){
        this.message = message;
    }

    /**
     * Executes the action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string of the action that was performed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    /**
     * Returns a string describing the action suitable for displaying in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player does nothing"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + message;
    }
}
