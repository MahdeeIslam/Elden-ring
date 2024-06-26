package edu.monash.fit2099.game.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import edu.monash.fit2099.game.actions.BuyAction;
import edu.monash.fit2099.game.actions.SellAction;

import java.util.HashMap;

/**
 * A class that represents a trader.
 * Created by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 * @see Actor
 */
public abstract class Trader extends Actor {

    /**
     * The sellable item inventory of the trader.
     */
    private HashMap<WeaponItem, Integer> sellInventory;
    /**
     * The buyable item inventory of the trader.
     */
    private HashMap<WeaponItem, Integer> buyInventory;

    /**
     * Constructor.
     *
     * @param name         the name of the Actor
     * @param displayChar  the character that will represent the Actor in the display
     * @param hitPoints    the Actor's starting hit points
     */
    public Trader(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.buyInventory = new HashMap<>();
        this.sellInventory = new HashMap<>();
    }

    /**
     * Set the buyable inventory of the trader. The inventory is a HashMap of WeaponItems and their prices.
     * @param buyInventory the buyable inventory of the trader.
     */
    public void setBuyInventory(HashMap<WeaponItem, Integer> buyInventory) {
        this.buyInventory = buyInventory;
    }

    /**
     * Returns the sellable inventory of the trader. The inventory is a HashMap of WeaponItems and their prices.
     * @param sellInventory the sellable inventory of the trader.
     */
    public void setSellInventory(HashMap<WeaponItem, Integer> sellInventory) {
        this.sellInventory = sellInventory;
    }
    /**
     * The playTurn method for the Trader class, the trader never does anything, so will always return a DoNothingAction.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Returns a new collection of the Actions of purchases that can be made by or from the Trader.
     * @param otherActor the Actor that might be performing the purchase
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        for (WeaponItem weapon : this.buyInventory.keySet()) {
            actions.add(new BuyAction(weapon, this.buyInventory.get(weapon), this));
        }

        for (WeaponItem weapon : otherActor.getWeaponInventory()) {
            for (WeaponItem key : this.sellInventory.keySet()) {
                // pls dont judge me for this. THE ENGINE DOES THE EXACT SAME THING TWICE!!!!!!!!!!!!!!!!!!!!!
                if (weapon.getClass() == key.getClass()) {
                    actions.add(new SellAction(weapon, this.sellInventory.get(key), this));
                }
            }
        }

        return actions;
    }
}
