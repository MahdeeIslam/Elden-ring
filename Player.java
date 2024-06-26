/**
 * This package contains all classes related to the player, including the abstract base, as well as the (currently) 3 main load-outs.
 * @author Benjamin Saunders {bsau0007@student.monash.edu}
 * @version 1.0
 */
package edu.monash.fit2099.game.players;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;


import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.game.RuneManager;
import edu.monash.fit2099.game.actions.DeathAction;
import edu.monash.fit2099.game.actions.UseFlaskAction;
import edu.monash.fit2099.game.items.FlaskOfCrimsonTears;
import edu.monash.fit2099.game.reset.ResetManager;
import edu.monash.fit2099.game.reset.Resettable;
import edu.monash.fit2099.game.utils.State;



/**
 * The Player Abstract class to ensure all methods and attributes are uniform between different player load-outs
 * Created by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 * @see Actor
 */
public abstract class Player extends Actor implements Resettable {

    private final Menu menu = new Menu();
    private FlaskOfCrimsonTears flask;
    /**
     * The String that will be displayed next to the players name in the action menu.
     */
    protected String className;

    /**
     * the abstract constructor for the Player load-outs. It will initialize rune count
     * @param name the name of the player
     * @param displayChar the character that will represent the player on the map
     * @param hitPoints the maximum amount of hit points for the player
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);

        this.addCapability(State.HOSTILE_TO_ENEMY);
        this.addCapability(State.IS_PLAYER);
        RuneManager.getInstance().setRunes(this, 0);
        ResetManager.getInstance().registerResettable(this);
    }


    /**
     * Some of this code was provided by Adrian Kistanto in the game demo.
     * The set of actions that will happen when the player's turn occurs. includes the print statement for the player, class, current hp and rune count.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return The action object of the turn choice that the user made.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            return new DeathAction();
        }

        ResetManager resetManager = ResetManager.getInstance();
        if (resetManager.getLastLocation() != map.locationOf(this)) {
            resetManager.setLastLocation(map.locationOf(this));
        }


        // Prints the players stats
        display.print("Tarnished, " + this.className + " (" + this.hitPoints + "/" + this.maxHitPoints + ") | Runes: " + RuneManager.getInstance().getRunes(this) + "\n");

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();


        if (this.flask.hasCharges()) { actions.add(new UseFlaskAction(this.flask)); }
        // return/print the console menu
        return menu.showMenu(this, actions, display);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(11, "punches");
    }

    /**
     * Sets the flask of crimson tears for the player.
     * @param flask The flask of crimson tears.
     */
    public void setFlask(FlaskOfCrimsonTears flask) {
        this.flask = flask;
    }

    @Override
    public void reset(GameMap map) {
    	this.heal(999999);
    }
}
