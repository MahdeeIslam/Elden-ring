package edu.monash.fit2099.game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A class that represents a club.
 * Created by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 */
public class Club extends WeaponItem {
    /**
     * The constructor for the club, parses all data into the superclass's constructor.
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
    }
}
