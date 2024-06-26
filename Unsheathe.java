package edu.monash.fit2099.game.actions.special_attacks;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import edu.monash.fit2099.game.actions.DeathAction;
import edu.monash.fit2099.game.utils.Utils;

/**
 * An action that facillitates the Unsheathe special ability
 * Created by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 * @see Action
 */
public class Unsheathe extends Action {
    /**
     * The weapon to be used
     */
    private WeaponItem weapon;

    /**
     * The target to be attacked
     */
    private Actor target;

    /**
     * The direction of the target
     */
    private String direction;

    /**
     * The damage to be dealt
     */
    private int damage;

    /**
     * The chance of the attack hitting
     */
    private int attackChance;

    /**
     * Constructor.
     *
     * @param weapon the weapon to be used
     * @param target the target to be attacked
     * @param direction the direction of the target
     */
    public Unsheathe(WeaponItem weapon, Actor target, String direction) {
        this.weapon = weapon;
        this.target = target;
        this.direction = direction;

        this.damage = weapon.damage() * 2;
        this.attackChance = 60;
    }

    /**
     * Executes the Unsheathe action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string that describes the attack
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String message = "";
        // If the target is in range and the attack hits
        if(Utils.canAttack(target, actor) && Math.random() * 100 < this.attackChance){
            String attackOutcome = actor + " " + weapon.verb() + " " + this.target + " for " + this.damage + " damage";
            this.target.hurt(this.damage);

            String deathMessage = "";
            if (!this.target.isConscious()){
                deathMessage = new DeathAction(actor).execute(target, map);
            }

            if (deathMessage != "") {
                message = attackOutcome + System.lineSeparator() + deathMessage;
            } else {
                message = attackOutcome;
            }

        } else if (this.target.isConscious()){
            message = actor + " attack has missed " + this.target;
        }

        return menuDescription(actor) + System.lineSeparator() + message;
    }

    /**
     * Returns a string describing the action suitable for displaying in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s unsheathes their %s on %s at %s", actor, this.weapon, this.target, this.direction);
    }
}
