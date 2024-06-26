package edu.monash.fit2099.game;

import edu.monash.fit2099.game.enemies.*;
import edu.monash.fit2099.game.utils.EnemyType;
import edu.monash.fit2099.game.utils.MapSide;

/**
 * A factory class for creating enemies of all different types
 * Created by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 */
public class EnemyFactory {
    /**
     * The singleton instance of the EnemyFactory
     */
    private static EnemyFactory instance = null;
    /**
     * The constructor for the EnemyFactory, private to prevent instantiation
     */
    private EnemyFactory() {}

    /**
     * Creates a new enemy based on the enemy type and map side
     * @param enemyType The type of enemy to create
     * @param mapSide The side of the map the enemy is on
     * @return The new enemy
     */
    public Enemy newEnemy (EnemyType enemyType, MapSide mapSide) {
        switch (enemyType) {
            case SKELETON -> {
                if (mapSide == MapSide.EAST) {
                    return new SkeletalBandit();
                } else {
                    return new HeavySkeletalSwordsmen();
                }
            }
            case CANINE -> {
                if (mapSide == MapSide.EAST) {
                    return new GiantDog();
                } else {
                    return new LoneWolf();
                }
            }
            case AQUATIC -> {
                if (mapSide == MapSide.EAST) {
                    return new GiantCrayfish();
                } else {
                    return new GiantCrab();
                }
            }
        }
        return null;
    }


    /**
     * Gets the singleton instance of the EnemyFactory
     * @return The singleton instance of the EnemyFactory
     */
    public static EnemyFactory getInstance() {
        if (instance == null) {
            instance = new EnemyFactory();
        }
        return instance;
    }
}
