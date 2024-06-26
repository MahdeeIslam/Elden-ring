package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.game.players.Player;

/**
 * An action that allows the player to choose this class
 * Created By:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @see Action
 * @version 1.0.0
 */
public class ChooseClassAction extends Action {
    /**
     * The player class to be chosen by this action
     */
    private Player playerClass;

    /**
     * The hotkey to be used for the action
     */
    private String hotkey;

    /**
     * The name of the class
     */
    private String name;

    /**
     * Constructor.
     *
     * @param playerClass the player class to be chosen by this action
     * @param hotkey the hotkey to be used for the action
     * @param name the name of the class
     */
    public ChooseClassAction(Player playerClass, String hotkey, String name) {
        this.playerClass = playerClass;
        this.hotkey = hotkey;
        this.name = name;

    }

    /**
     * Executes the action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string of the class the user chose
     */
    @Override
    public String execute(Actor actor, GameMap map) { return menuDescription(actor); }

    /**
     * Returns a string describing the class to be chosen
     *
     * @param actor The actor performing the action.
     * @return a string, e.g. "Bandit"
     */
    @Override
    public String menuDescription(Actor actor) { return this.name; }

    /**
     * Returns the hotkey to be used for the class's choice
     *
     * @return a string, e.g. "b"
     */
    @Override
    public String hotkey() { return this.hotkey; }

}
