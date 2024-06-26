package edu.monash.fit2099.game.enemies;

import edu.monash.fit2099.game.utils.EnemyType;
import edu.monash.fit2099.game.utils.State;
import edu.monash.fit2099.game.weapons.fake_intrinsics.DogHead;


import static edu.monash.fit2099.game.utils.RandomNumberGenerator.getRandomInt;

/**
 * A Giant Dog class, extends the enemy class
 * Created by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 * @see Enemy
 */
public class GiantDog extends Enemy {
    public GiantDog() {
        super("Giant Dog", 'G', 683, getRandomInt(313, 1808), EnemyType.CANINE);
        this.setSpawnChance(4);
        this.addCapability(State.CAN_DESPAWN);
        this.addWeaponToInventory(new DogHead());
    }
}
