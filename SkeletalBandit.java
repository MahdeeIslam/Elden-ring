package edu.monash.fit2099.game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.utils.EnemyType;
import edu.monash.fit2099.game.utils.State;
import edu.monash.fit2099.game.weapons.Scimitar;

import static edu.monash.fit2099.game.utils.RandomNumberGenerator.getRandomInt;

/**
 * A Skeletal Bandit class, extends the enemy class
 * Created by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 * @see Enemy
 */
public class SkeletalBandit extends Enemy{
    /**
     * Constructor for the SkeletalBandit class
     */
    public SkeletalBandit() {
        super("Skeletal Bandit", 'b', 184, getRandomInt(35, 892), EnemyType.SKELETON);
        this.addWeaponToInventory(new Scimitar());
        this.addCapability(State.SPAWNS_PILE_OF_BONES);
        this.addCapability(State.CAN_DESPAWN);
        this.setSpawnChance(27);
    }
}
