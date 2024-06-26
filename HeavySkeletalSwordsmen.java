package edu.monash.fit2099.game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.utils.EnemyType;
import edu.monash.fit2099.game.utils.State;
import edu.monash.fit2099.game.weapons.Grossmesser;

/**
 * A Heavy Skeletal Swordsman class, extends the enemy class
 * Created by:
 * @author Joshua Leong
 * Modified by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 2.0.0
 * @see Enemy
 */
public class HeavySkeletalSwordsmen extends Enemy{

    /**
     * Constructor for the HeavySkeletalSwordsmen class
     */
    public HeavySkeletalSwordsmen(){
        super("Heavy Skeletal Swordsman", 'q', 153, 0, EnemyType.SKELETON);
        this.addWeaponToInventory(new Grossmesser());
        this.addCapability(State.SPAWNS_PILE_OF_BONES);
        this.addCapability(State.CAN_DESPAWN);
        this.setSpawnChance(27);
    }

    /**
     * Method to deal with the HeavySkeletalSwordsmen's death
     * @param map the map the HeavySkeletalSwordsmen is on
     * @param display the display of the HeavySkeletalSwordsmen
     * @return the death action of the HeavySkeletalSwordsmen
     */
}
