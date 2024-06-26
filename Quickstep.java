package edu.monash.fit2099.game.actions.special_attacks;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import edu.monash.fit2099.game.actions.AttackAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Quickstep action that allows the actor to attack a target and move to a random available exit
 * Created By:
 * @author Benjamin Saunders
 * last edited: 30/04/2023
 * @see Action
 */
public class Quickstep extends Action {

    /**
     * The weapon used to attack the target
     */
    private WeaponItem weapon;
    /**
     * The target to attack
     */
    private Actor target;
    /**
     * The direction of the target
     */
    private String direction;

    /**
     * Constructor
     * @param weapon the weapon used to attack the target
     * @param target the target to attack
     * @param direction the direction of the target
     */
    public Quickstep(WeaponItem weapon, Actor target, String direction){
        this.weapon = weapon;
        this.target = target;
        this.direction = direction;
    }

    /**
     * Executes the Quickstep action, first attacks the target enemy, then moves to a random available location
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // attack the target
        String message = new AttackAction(this.target, this.direction, this.weapon).execute(actor, map);

        // blank list of exits to add available exits to if they dont have an actor on them
        List<Exit> availableExits = new ArrayList<>();

        // add all exits that dont have an actor on them to availableExits
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (!exit.getDestination().containsAnActor() && exit.getDestination().canActorEnter(actor)) {
                availableExits.add(exit);
            }
        }

        // randomly choose an exit from availableExits
        Exit chosenExit;
        String suffix;
        if (availableExits.size() > 0) {
            chosenExit = availableExits.get((int) (Math.random() * availableExits.size())); // randomly choose an exit from availableExits
            map.moveActor(actor, chosenExit.getDestination()); // move the actor to the chosen exit
            suffix = actor + " has quick-stepped " + chosenExit.getName();
        } else {
            suffix = actor + " has no available exits to quick-step to";
        }



        return menuDescription(actor) + System.lineSeparator() + message + System.lineSeparator() + suffix;
    }

    /**
     * Returns a string describing the action
     * @param actor The actor performing the action.
     * @return a string describing the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s uses quickstep with their %s on %s at position %s", actor, this.weapon, this.target, this.direction);
    }
}
