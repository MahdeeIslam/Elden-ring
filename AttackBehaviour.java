package edu.monash.fit2099.game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import edu.monash.fit2099.game.actions.AttackAction;
import edu.monash.fit2099.game.utils.State;
import edu.monash.fit2099.game.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * AttackBehaviour that allows the enemy to make its own attacks
 * Created By:
 * @author Joshua Leong
 * Edited By:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @see IBehaviour
 */
public class AttackBehaviour implements IBehaviour {

    /**
     * Returns an AttackAction if the actor can attack a target, otherwise returns null
     * @param actor the actor performing the action
     * @param map the map the actor is on
     * @return an AttackAction if the actor can attack a target, otherwise returns null
     */
    public Action getAction(Actor actor, GameMap map){
        // get all exits that have an actor on them
        List<Exit> possibleTargets = new ArrayList<>();
        for(Exit exit : map.locationOf(actor).getExits()){
            Location location = exit.getDestination();
            if (location.containsAnActor() && Utils.canAttack(location.getActor(), actor)) {
                possibleTargets.add(exit);
            }
        }

        // get random target from possibleTargets and attack it
        if (!possibleTargets.isEmpty()) {
            Exit exit = possibleTargets.get((int) (Math.random() * possibleTargets.size()));
            Actor target = exit.getDestination().getActor();

            // if the actor has a weapon, use it, otherwise use the intrinsic weapon
            try {
                WeaponItem weapon = actor.getWeaponInventory().get(0);
                // if the weapon has a skill, give a 50% chance of using it
                if (weapon.hasCapability(State.HAS_SKILL)) {
                    if (Math.random() < 0.5) {
                        return weapon.getSkill(target, exit.getName());
                    }
                }

                return new AttackAction(target, exit.getName(), weapon);
            } catch (IndexOutOfBoundsException e) {
                Weapon weapon = actor.getIntrinsicWeapon();
                return new AttackAction(target, exit.getName(), weapon);
            }
        } else {
            return null;
        }
    }
}
