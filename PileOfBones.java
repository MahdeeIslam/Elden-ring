package edu.monash.fit2099.game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actions.BlankAction;
import edu.monash.fit2099.game.utils.EnemyType;

import static edu.monash.fit2099.game.utils.RandomNumberGenerator.getRandomInt;

/**
 * A Pile of Bones class, the unique consequence of the Skeleton types death, extends the enemy class
 * Created by:
 * @author Joshua Leong
 * Modified by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 2.0.0
 * @see Enemy
 */

public class PileOfBones extends Enemy{

    /**
     * The age of the PileOfBones
     */
    private int age = 0;

    /**
     * The enemy that will be summoned from the PileOfBones after 3 turns
     */
    private final Actor willSummon;

    /**
     * Constructor for the PileOfBones class
     * @param actor the enemy that will be summoned from the PileOfBones after 3 turns
     */
    public PileOfBones(Actor actor){
        super("Pile of Bones", 'X',1, getRandomInt(35, 892), EnemyType.SKELETON);
        this.addWeaponToInventory(actor.getWeaponInventory().get(0));
        actor.heal(999999);
        this.willSummon = actor;
    }

    /**
     * Method to play the turn of the PileOfBones class, will summon the "willSummon" enemy after 3 turns
     * @param actions the list of actions that the enemy can perform
     * @param lastAction the last action that the enemy performed
     * @param map the current GameMap
     * @param display the Display that will display the game
     * @return the action that the PileOfBones will perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(age == 3){
            Location current = map.locationOf(this);
            map.removeActor(this);
            map.addActor(willSummon, current);
            return new BlankAction(" has been resurrected");
        }
        age += 1;
        return new BlankAction(" lays dormant");
    }
}
