package edu.monash.fit2099.game.players;

import edu.monash.fit2099.game.weapons.Uchigatana;

/**
 * The Samurai load-out. One of the (currently) 3 possible load-outs for the player to choose from. Subclass of the player superclass.
 * Created by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 * @see Player
 */
public class Samurai extends Player {
    /**
     * The constructor for the Samurai load-out, parses all data into the superclass's constructor, sets the displayed class name.
     * @param name Name of the Character
     * @param displayChar Character that will be displayed on the map to represent the player
     * @param hitPoints The max hitpoints of the player.
     */
    public Samurai (String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.className = "Samurai"; // Displayed in the title bar of the action's menu.
        this.addWeaponToInventory(new Uchigatana()); // REQ4 game rule.
    }
}
