package edu.monash.fit2099.game.enemies;

import edu.monash.fit2099.game.utils.EnemyType;
import edu.monash.fit2099.game.utils.State;
import edu.monash.fit2099.game.weapons.fake_intrinsics.GiantCrabClaws;

import static edu.monash.fit2099.game.utils.RandomNumberGenerator.getRandomInt;

/**
 * A Giant Crayfish class, extends the enemy class
 * Created by:
 * @author Joshua Leong
 * Modified By:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 * @see Enemy
 */
public class GiantCrayfish extends Enemy {
    /**
     * Constructor for the GiantCrayfish class
     */
    public GiantCrayfish() {
        super("Giant Crayfish", 'R', 4803, getRandomInt(500, 2374), EnemyType.AQUATIC);
        this.addWeaponToInventory(new GiantCrabClaws());
        this.addCapability(State.CAN_DESPAWN);
        this.setSpawnChance(1);
    }
}
