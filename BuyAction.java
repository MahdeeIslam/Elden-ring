package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import edu.monash.fit2099.game.RuneManager;
import edu.monash.fit2099.game.npcs.Trader;

/**
 * The action to facilitate the purchasing of Weapons from a trader
 * Created By:
 * @auther Benjamin Saunders
 * Last Modified: 01/05/2023
 * @see Action
 * @version 1.0.0
 */
public class BuyAction extends Action {
    /**
     * The weapon to be bought
     */
    private WeaponItem weapon;

    /**
     * The price of the weapon
     */
    private int price;

    /**
     * The trader selling the weapon
     */
    private Trader trader;

    /**
     * Constructor.
     *
     * @param weapon the weapon to be bought
     * @param price the price of the weapon
     * @param trader the trader selling the weapon
     */
    public BuyAction(WeaponItem weapon, int price, Trader trader) {
        this.weapon = weapon;
        this.price = price;
        this.trader = trader;
    }

    /**
     * Executes the buy action
     *
     * @param actor The actor purchasing the item
     * @param map The map the actor is on.
     * @return a string that describes the purchase
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager runeManager = RuneManager.getInstance();
        if (runeManager.getRunes(actor) >= this.price) {
            runeManager.removeRunes(actor, this.price);
            actor.addWeaponToInventory(this.weapon);
            return this.menuDescription(actor);
        } else {
            return String.format("%s does not have enough runes to buy the %s from %s", actor, this.weapon, this.trader);
        }
    }

    /**
     * Returns a string describing the action suitable for displaying in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player buys a sword from the trader for 10 runes"
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s buys a %s from %s for %d runes", actor, this.weapon, this.trader, this.price);
    }
}
