package edu.monash.fit2099.game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Hashtable;

/**
 * A class that keeps track of the number of runes that each actor has
 * @author Benjamin Saunders {bsau0007@student.monash.edu}
 */
public class RuneManager {

    /**
     * The singleton instance of the RuneManager
     */
    private static RuneManager instance;
    /**
     * A Hashtable that maps an actor to the number of runes they have
     */
    private Hashtable<Actor, Integer> runeCount = new Hashtable<Actor, Integer>();

    /**
     * The constructor for the RuneManager
     */
    private RuneManager() {
    }

    /**
     * A method to get the number of runes that an actor has
     * @param actor, the actor to get the number of runes for
     * @return the number of runes that the actor has
     */
    public int getRunes(Actor actor) {
        return runeCount.get(actor);
    }

    /**
     * A method to add runes to an actor
     * @param actor, the actor to add the rune to
     * @param amount, the amount of runes to add
     */
    public void addRunes(Actor actor, int amount) {
        runeCount.put(actor, runeCount.get(actor) + amount);
    }

    /**
     * A method to remove runes from an actor
     * @param actor, the actor to remove the rune from
     * @param amount, the amount of runes to remove
     */
    public void removeRunes(Actor actor, int amount) {
        runeCount.put(actor, runeCount.get(actor) - amount);
    }

    /**
     * A method to set the number of runes that an actor has
     * @param actor, the actor to set the number of runes for
     * @param amount, the amount of runes to set
     */
    public void setRunes(Actor actor, int amount) {
        runeCount.put(actor, amount);
    }


    /**
     * A method to get the singleton instance of the RuneManager
     * @return the singleton instance of the RuneManager
     */
    public static RuneManager getInstance() {
        if (instance == null) {
            instance = new RuneManager();
        }
        return instance;
    }
}
