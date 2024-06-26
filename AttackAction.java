package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.game.utils.State;
import edu.monash.fit2099.game.utils.Utils;

/**
 * The action to control all attacks that are made.
 * Created By:
 * @author Joshua Leong
 * Modified By:
 * @author Benjamin Saunders
 * Last Modified: 1/05/2023
 * @version 1.3.0
 * @see Action
 */
public class AttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     */
    //Actor to be attacked
    public AttackAction(Actor target, String direction){
        setTarget(target);
        setDirection(direction);
    }

    /**
     * Constructor with weapon
     *
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     * @param weapon the weapon used for the attack
     */
    public AttackAction(Actor target, String direction, Weapon weapon){
        setTarget(target);
        setDirection(direction);
        setWeapon(weapon);
    }

    /**
     * Executes the attack on the target.
     * When the target is attacked, it will calculate the weapons hit chance,
     * and then process the actors death if necessary
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing the attack
     */
    public String execute(Actor actor, GameMap map){
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }
        // if it is not attacking it now is
        if(Utils.canAttack(target, actor) && !actor.hasCapability(State.ATTACKING)){
            actor.addCapability(State.ATTACKING);
        }

        //attacking sequence
        if(Math.random() * 100 < weapon.chanceToHit()){
            String attackOutcome = actor + " " + weapon.verb() + " " + getTarget() + " for " + weapon.damage() + " damage";
            getTarget().hurt(weapon.damage());

            if (!getTarget().isConscious()){
                return attackOutcome + System.lineSeparator() + new DeathAction(actor).execute(target, map);
            }

            return attackOutcome;
        } else if (getTarget().isConscious()){
            return actor + " attack has missed " + getTarget();
        }
        return null;
    }

    /**
     * Returns a string describing the attack to be shown in the menu
     * @param actor The actor performing the action.
     * @return a string describing the attack
     */
    public String menuDescription(Actor actor) {
        return String.format("%s attacks %s at %s with %s", actor, getTarget(), direction, weapon);
    }

    /**
     * Returns the target of the attack
     * @return the target of the attack
     */
    public Actor getTarget() {
        return target;
    }

    /**
     * Sets the target of the attack
     * @param target the target of the attack
     */
    public void setTarget(Actor target) {
        this.target = target;
    }

    /**
     * Returns the direction of the attack
     * @return the direction of the attack
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the attack
     * @param direction the direction of the attack
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Sets the weapon used for the attack
     * @param weapon the weapon used for the attack
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Returns the weapon used for the attack
     * @return the weapon used for the attack
     */
    public Weapon getWeapon() {
        return weapon;
    }
}
