package edu.monash.fit2099.game.actions.special_attacks;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import edu.monash.fit2099.game.actions.AttackAction;
import edu.monash.fit2099.game.utils.Utils;

/**
 * The action to control the Spin special attack.
 * Created By:
 * @author Benjamin Saunders
 * Modified By:
 * @version 1.0.0
 * @see Action
 */
public class SpinningAttack extends Action{
    /**
     * The weapon used for the attack
     */
    private WeaponItem weapon;

    /**
     * Constructor.
     *
     * @param weapon the weapon used for the attack
     */
    public SpinningAttack(WeaponItem weapon){
        this.weapon = weapon;
    }

    /**
     * Executes the spinning attack
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string displaying the spinning attack + everyone it hit
     */
    public String execute(Actor actor, GameMap map){
        String message = "";
        // for each exit
        for (Exit exit : map.locationOf(actor).getExits()){
            // if there is an actor in the exit
            if (exit.getDestination().containsAnActor()){
                // get the actor
                Actor target = exit.getDestination().getActor();
                if (Utils.canAttack(target, actor)) {
                    message += new AttackAction(target, exit.getName(), this.weapon).execute(actor, map) + System.lineSeparator();
                }
            }
        }

        return menuDescription(actor) + System.lineSeparator() + message;
    }

    /**
     * Returns a string describing the action suitable for displaying in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string describing the action
     */
    public String menuDescription(Actor actor){
        return String.format("%s uses %s's Spinning Attack", actor, this.weapon);
    }
}
