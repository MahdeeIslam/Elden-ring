package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An interface for all classes that can die.
 * Created By:
 * @author Benjamin Saunders
 */
public interface CanDie {
    /**
     * A method that is called when an actor dies.
     * @param map The map the actor is on.
     * @param display The games current display.
     * @return an action that will be performed when the actor dies.
     */
    public Action die(GameMap map, Display display);
}
