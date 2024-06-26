package edu.monash.fit2099.game.utils;

/**
 * An enum class that represents the state of an enemy
 * Created by:
 * @author Benjamin Saunders
 * Modified by:
 * @author Joshua Leong
 * Last Modified: 01/05/2023
 * @version 9.9.9
 */
public enum State {
    HOSTILE_TO_ENEMY,
    HOSTILE_TO_PLAYER,
    IS_ENEMY,
    IS_PLAYER,
    ATTACKING,
    SPAWNS_PILE_OF_BONES,
    CAN_DESPAWN,
    HAS_SKILL
}
