package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import edu.monash.fit2099.game.RuneManager;
import edu.monash.fit2099.game.npcs.Trader;

/**
 * The action to facilitate the selling of Weapons to a trader
 * Created By:
 * @auther Benjamin Saunders
 * Last Modified: 01/05/2023
 * @see Action
 * @version 1.0.0
 */
public class SellAction extends Action {
    /**
     * The weapon to be sold
     */
    private WeaponItem weapon;

    /**
     * The price of the weapon
     */
    private int price;

    /**
     * The trader buying the weapon
     */
    private Trader trader;

    /**
     * Constructor.
     *
     * @param weapon the weapon to be sold
     * @param price the price of the weapon
     * @param trader the trader buying the weapon
     */
    public SellAction(WeaponItem weapon, int price, Trader trader) {
        this.weapon = weapon;
        this.price = price;
        this.trader = trader;
    }

    /**
     * Executes the sell action
     *
     * @param actor The actor selling the item
     * @param map The map the actor is on.
     * @return a string that describes the sale
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager runeManager = RuneManager.getInstance();
        actor.removeWeaponFromInventory(this.weapon);
        runeManager.addRunes(actor, this.price);
        return this.menuDescription(actor);
    }

    /**
     * Returns a string describing the sale
     *
     * @param actor The actor selling the item
     * @return a string that describes the sale
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s sells a %s to %s for %d runes", actor, this.weapon, this.trader, this.price);
    }

}
