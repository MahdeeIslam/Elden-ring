package edu.monash.fit2099.game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.game.utils.EnemyType;
import edu.monash.fit2099.game.utils.State;

import static edu.monash.fit2099.game.utils.RandomNumberGenerator.getRandomInt;

/**
 * A Lone Wolf class, extends the enemy class
 * Created by:
 * @author Joshua Leong
 * Modified By:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 * @see Enemy
 */
public class LoneWolf extends Enemy {

    /**
     * Constructor for the LoneWolf class
     */
    public LoneWolf(){
        super ("Lone Wolf", 'h', 102, getRandomInt(55, 1740), EnemyType.CANINE);
        this.addCapability(State.CAN_DESPAWN);
        this.setSpawnChance(33);
    }

    /**
     * Returns the intrinsic weapon that the LoneWolf has
     * @return the intrinsic weapon that the LoneWolf has
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon(){ return new IntrinsicWeapon(97, "bites",  95);}

}
