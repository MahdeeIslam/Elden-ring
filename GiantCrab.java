package edu.monash.fit2099.game.enemies;

import edu.monash.fit2099.game.utils.EnemyType;
import edu.monash.fit2099.game.utils.State;
import edu.monash.fit2099.game.weapons.fake_intrinsics.CrabClaws;

import static edu.monash.fit2099.game.utils.RandomNumberGenerator.getRandomInt;

/**
 * A Giant Crab class, extends the enemy class
 * Created by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 * @see Enemy
 */
public class GiantCrab extends Enemy {
    /**
     * Constructor for the GiantCrab class
     */
    public GiantCrab() {
        super("Giant Crab", 'C', 407, getRandomInt(318, 4961), EnemyType.AQUATIC);
        this.addWeaponToInventory(new CrabClaws());
        this.addCapability(State.CAN_DESPAWN);
        this.setSpawnChance(2);
    }
}
