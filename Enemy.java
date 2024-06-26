package edu.monash.fit2099.game.enemies;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import edu.monash.fit2099.game.RuneManager;
import edu.monash.fit2099.game.actions.AttackAction;
import edu.monash.fit2099.game.actions.CanDie;
import edu.monash.fit2099.game.actions.DeathAction;
import edu.monash.fit2099.game.actions.DespawnAction;
import edu.monash.fit2099.game.behaviours.AttackBehaviour;
import edu.monash.fit2099.game.behaviours.FollowBehaviour;
import edu.monash.fit2099.game.behaviours.IBehaviour;
import edu.monash.fit2099.game.behaviours.WanderBehaviour;
import edu.monash.fit2099.game.reset.ResetManager;
import edu.monash.fit2099.game.reset.Resettable;
import edu.monash.fit2099.game.utils.EnemyType;
import edu.monash.fit2099.game.utils.State;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * An abstract class for all enemies
 * Created by:
 * @author Joshua Leong
 * Edited by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.2.0
 * @see Actor
 */
public abstract class Enemy extends Actor implements CanDie, Resettable {

    /** The Priority in which the enemy will execute its behaviours */
    private static final int ATTACK_PRIORITY = 0;
    private static final int FOLLOW_PRIORITY = 1;
    private static final int WANDER_PRIORITY = 2;

    /** The chance that the enemy will despawn */
    private static final float DESPAWN_CHANCE = 0.1F;

    /** The chance that the enemy will spawn */
    private int spawnChance;

    /** The HASHMAP of enemy behaviours */
    private final Map<Integer, IBehaviour> behaviour = new HashMap<>(); // this is for hte priority set for behaviour by using a hash map



    /**
     * Constructor for the Enemy class
     * @param name the name of the enemy
     * @param displayChar the character that will represent the enemy in the display
     * @param hitPoints the hitpoints of the enemy
     * @param runeDropCount the number of runes the enemy will drop
     * @param enemyType the type of enemy
     */
    public Enemy(String name, char displayChar, int hitPoints, int runeDropCount, EnemyType enemyType) {
        super(name, displayChar, hitPoints);

        // The enemy will be hostile to the player and other enemies
        this.addCapability(State.HOSTILE_TO_ENEMY);
        this.addCapability(State.HOSTILE_TO_PLAYER);

        /// The enemy is an enemy
        this.addCapability(State.IS_ENEMY);
        this.addCapability(enemyType);

        getBehaviours().put(ATTACK_PRIORITY, new AttackBehaviour());
        getBehaviours().put(WANDER_PRIORITY, new WanderBehaviour());

        RuneManager.getInstance().setRunes(this, runeDropCount);

        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * This method is used to determine the action that the enemy will perform
     * @param actions the list of actions that the enemy can perform
     * @param lastAction the last action that the enemy performed
     * @param map the current GameMap
     * @param display the Display that will display the game
     * @return the Action that the enemy will perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display){
        if(!super.isConscious()){
            return die(map, display);
        } else if (this.hasCapability(State.CAN_DESPAWN) && Math.random() < DESPAWN_CHANCE){ // if the enemy can despawn and the random number is less than the despawn chance
            return new DespawnAction();
        } else {
            for (IBehaviour behaviour : getBehaviours().values() ){
                Action action = behaviour.getAction(this, map);
                if (action != null){
                    return action;
                }
            }
        }
        // it will do nothing if it has no actions
        return new DoNothingAction();
    }


    /**
     * This method is used to export all actions that can be done to the enemy
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map){
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(State.IS_PLAYER)){
            getBehaviours().put(FOLLOW_PRIORITY, new FollowBehaviour(otherActor));
            if (this.hasCapability(State.CAN_DESPAWN)) {
               this.removeCapability(State.CAN_DESPAWN);
            }
        }

        if(otherActor.hasCapability(State.HOSTILE_TO_ENEMY) && super.isConscious()){
            for (WeaponItem weapon : new ArrayList<>(otherActor.getWeaponInventory())) {
                actions.add(new AttackAction(this, direction, weapon));
                actions.add(new AttackAction(this, direction, otherActor.getIntrinsicWeapon()));
                if (weapon.hasCapability(State.HAS_SKILL)) {
                    actions.add(weapon.getSkill(this, direction));
                }
            }
        }


        return actions;
    }


    //Mahdee this is where the reset would go. implement it here and make sure to write implements at the top.

    /**
     * This method is used to determine what will happen when the enemy dies.
     * @param map the current GameMap
     * @param display the Display that will display the game
     * @return the Action that the enemy will perform when it dies
     */
    public Action die(GameMap map, Display display) {return new DeathAction();}

    /**
     * This method is used to set the spawn chance of the enemy
     * @param spawnChance the spawn chance of the enemy
     */
    public void setSpawnChance(int spawnChance) {
        this.spawnChance = spawnChance;
    }

    /**
     * This method is used to get the spawn chance of the enemy
     * @return the spawn chance of the enemy
     */
    public int getSpawnChance() {
        return spawnChance;
    }

    /** Returns the Map of enemy behaviours */
    public Map<Integer, IBehaviour> getBehaviours() {return behaviour;} // getter for behaviour

    /**
     * This method is used to reset the enemy
     * @param map the current GameMap
     * @see ResetManager
     */
    public void reset(GameMap map) {
        new DespawnAction().execute(this, map);
    }

}
