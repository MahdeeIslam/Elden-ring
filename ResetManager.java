package edu.monash.fit2099.game.reset;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;


import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Benjamin Saunders
 * @author Mahdee Islam
 * Last Modified: 02/05/2023
 * @see Resettable
 */
public class ResetManager {
    private Location lastSite;
    private Location buffer;
    private Location lastLocation;
    private List<Resettable> resetList;
    private List<Resettable> runeList;
    private static ResetManager instance;

    /**
     * The constructor for the reset manager
     */
    private ResetManager() {
        this.resetList = new ArrayList<>();
        this.runeList = new ArrayList<>();
        this.lastSite = null;
    }

    /**
     * Runs the reset method for all resettables in the reset list, and all runes in the rune list
     * @param map The map the actor is on.
     * @param death Whether the player has died or not
     */
    public void run(GameMap map, boolean death) {
        for (Resettable resettable : this.resetList ){
            resettable.reset(map);
        }

        if (death) {
            for (Resettable resettable : this.runeList ){
                resettable.reset(map);
            }
        }
    }

    /**
     * Registers a resettable to the reset list
     * @param resettable The resettable to register
     */
    public void registerResettable(Resettable resettable) {
        this.resetList.add(resettable);
    }

    /**
     * Registers a rune to the rune list
     * @param resettable The rune to register
     */
    public void registerRune(Resettable resettable) {
        this.runeList.add(resettable);
    }

    /**
     * Sets the last site visited by the player
     * @param lastSite The last site visited by the player
     */
    public void setLastSite(Location lastSite) {
        this.lastSite = lastSite;
    }

    /**
     * Returns the last site visited by the player
     * @return The last site visited by the player
     */
    public Location getLastSite() {
        return this.lastSite;
    }

    /**
     * Sets the last location visited by the player
     * @param location The last location visited by the player
     */
    public void setLastLocation(Location location) {
        this.buffer = location;
        this.lastLocation = this.buffer;
    }

    /**
     * Returns the last location visited by the player
     * @return The last location visited by the player
     */
    public Location getLastLocation() {
        return this.buffer;
    }

    /**
     * Returns the instance of the reset manager
     * @return The instance of the reset manager
     */
    public static ResetManager getInstance() {
        if (instance == null) {
            instance = new ResetManager();
        }
        return instance;
    }
}
