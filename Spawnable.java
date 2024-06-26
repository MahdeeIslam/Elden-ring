package edu.monash.fit2099.game.grounds.environments;

import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.enemies.Enemy;

/**
 * An interface for spawning enemies
 * Created by:
 * @author Joshua Leong
 * Modified By:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.2.0
 * @see Graveyard
 * @see PuddleOfWater
 * @see GustOfWind
 */
public interface Spawnable {

    /**
     * Spawns an enemy at the given location
     * @param location the location to spawn the enemy at
     * @param enemy the enemy to spawn
     */
    void spawn(Location location, Enemy enemy);

}
