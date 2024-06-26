package edu.monash.fit2099.game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import edu.monash.fit2099.game.actions.special_attacks.Quickstep;
import edu.monash.fit2099.game.utils.State;

/**
 * A class that represents the Great Knife.
 * Created by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 */
public class GreatKnife extends WeaponItem {
    /**
     * The constructor for the Great Knife, parses all data into the superclass's constructor.
     */
    public GreatKnife() {
        super("Great Knife", '/', 75, "stabs", 70);
        this.addCapability(State.HAS_SKILL);
    }


    /**
     * Returns the Quickstep action, which is the only action the Great Knife can perform.
     * @param target The target of the attack.
     * @param direction The direction of the attack.
     * @return The Quickstep action.
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new Quickstep(this, target, direction);
    }
}
