package edu.monash.fit2099.game.grounds.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.EnemyFactory;
import edu.monash.fit2099.game.enemies.Enemy;
import edu.monash.fit2099.game.utils.EnemyType;
import edu.monash.fit2099.game.utils.Utils;

/**
 * A gust of wind class, extends the ground class
 * Created by:
 * @author Joshua Leong
 * Modified By:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.2.0
 * @see Ground
 * @see Spawnable
 */
public class GustOfWind extends Ground implements Spawnable{
    /**
     * Constructor for the GustOfWind class
     */
    public GustOfWind(){
        super('&');
    }

    /**
     * Tick method for the GustOfWind class
     * @param location the location of the GustOfWind
     */
    @Override
    public void tick (Location location){
        EnemyFactory enemyFactory = EnemyFactory.getInstance();
        Enemy enemy = enemyFactory.newEnemy(EnemyType.CANINE, Utils.calculateMapSide(location));

        if(Math.random() * 100 < enemy.getSpawnChance() && !location.containsAnActor()){
            this.spawn(location, enemy);
        }
    }

    /**
     * Spawns an enemy at the given location, enforced by the Spawnable interface
     * @param location the location to spawn the enemy at
     * @param enemy the enemy to spawn
     */
    @Override
    public void spawn(Location location, Enemy enemy) {
        location.addActor(enemy);
    }
}
