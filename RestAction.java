package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.reset.ResetManager;

/**
 * Action for resting at a site of lost grace
 * Created By:
 * @author Benjamin Saunders
 * Last Modified: 02/05/2023
 * @see Action
 * @see edu.monash.fit2099.game.grounds.environments.SiteOfLostGrace
 */
public class RestAction extends Action {


    private Location siteLocation;
    private String name;

    /**
     * Constructor.
     *
     * @param siteLocation the location of the site of lost grace
     * @param name the name of the site of lost grace
     */
    public RestAction(Location siteLocation, String name) {
        this.siteLocation = siteLocation;
        this.name = name;
    }

    /**
     * Rests at the site of lost grace
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.run(siteLocation.map(), false);
        resetManager.setLastSite(siteLocation);
        return menuDescription(actor);
    }

    /**
     * Returns a description of the action suitable for displaying in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player rests at Site of Lost Grace"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at " + this.name;
    }
}
