package edu.monash.fit2099.game.grounds.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.EnemyFactory;
import edu.monash.fit2099.game.enemies.Enemy;
import edu.monash.fit2099.game.utils.EnemyType;
import edu.monash.fit2099.game.utils.Utils;

/**
 * A graveyard class, extends the ground class
 * Created by:
 * @author Joshua Leong
 * Modified By:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.2.0
 * @see Ground
 * @see Spawnable
 */
public class Graveyard extends Ground implements Spawnable {
    /**
     * Constructor for the Graveyard class
     */
    public Graveyard(){
        super('n');
    }

    /**
     * Tick method for the Graveyard class
     * @param location the location of the Graveyard
     */
    @Override
    public void tick (Location location){
        EnemyFactory enemyFactory = EnemyFactory.getInstance();
        Enemy enemy = enemyFactory.newEnemy(EnemyType.SKELETON, Utils.calculateMapSide(location));

        if(Math.random() * 100 < enemy.getSpawnChance() && !location.containsAnActor()){
            this.spawn(location, enemy);
        }
    }

    /**
     * Spawns an enemy at the given location, is from the Spawnable interface
     * @param location the location to spawn the enemy at
     * @param enemy the enemy to spawn
     */
    @Override
    public void spawn(Location location, Enemy enemy) {
        location.addActor(enemy);
    }
}
