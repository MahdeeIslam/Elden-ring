package edu.monash.fit2099.game.grounds.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.game.actions.RestAction;
import edu.monash.fit2099.game.reset.ResetManager;
import edu.monash.fit2099.game.utils.State;

/**
 * A class that represents the Site of Lost Grace.
 * Technically Created by:
 * @author Mahdee Islam
 * But implemented and rewritten by:
 * @author Benjamin Saunders
 * @see Ground
 */
public class SiteOfLostGrace extends Ground{

    private String name;

    /**
     * Constructor.
     *
     * @param name the name of the site of lost grace
     */
    public SiteOfLostGrace(String name) {
        super('U');
        this.name = name;
    }

    /**
     * Only the actor can enter the site of lost grace
     * @param actor the Actor to check
     * @return true if the actor has the IS_PLAYER capability
     */
    public boolean canActorEnter(Actor actor){ return actor.hasCapability(State.IS_PLAYER); }

    /**
     * Returns the allowable actions for the actor in range of the site of lost grace
     * @param actor the Actor to check
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an ActionList containing the RestAction
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        actions.add(new RestAction(location, this.name));
        return actions;
    }

    /**
     * Returns the name of the site of lost grace
     * @return the name of the site of lost grace
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the site of lost grace
     * @param name the name of the site of lost grace
     */
    public void setName(String name) {
        this.name = name;
    }
}