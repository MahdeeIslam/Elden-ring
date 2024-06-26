package edu.monash.fit2099.game.npcs;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import edu.monash.fit2099.game.weapons.*;

import java.util.HashMap;

/**
 * A class that represents a merchant named Kale
 * Created by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 * @see Trader
 */
public class MerchantKale extends Trader {


    /**
     * Constructor for the MerchantKale class
     */
    public MerchantKale() {
        super("Merchant Kale", 'K', 999);
        HashMap<WeaponItem, Integer> buyInventory = new HashMap<>();
        HashMap<WeaponItem, Integer> sellInventory = new HashMap<>();

        buyInventory.put(new Uchigatana(), 5000);
        buyInventory.put(new GreatKnife(), 3500);
        buyInventory.put(new Club(), 600);
        buyInventory.put(new Scimitar(), 600);

        sellInventory.put(new Uchigatana(), 500);
        sellInventory.put(new GreatKnife(), 350);
        sellInventory.put(new Club(), 100);
        sellInventory.put(new Scimitar(), 100);
        sellInventory.put(new Grossmesser(), 100);

        this.setBuyInventory(buyInventory);
        this.setSellInventory(sellInventory);
    }


}
