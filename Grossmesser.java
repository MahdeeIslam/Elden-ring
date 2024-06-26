package edu.monash.fit2099.game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import edu.monash.fit2099.game.actions.special_attacks.SpinningAttack;
import edu.monash.fit2099.game.utils.State;

/**
 * A class that represents the Grossmesser.
 * Created by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 */
public class Grossmesser extends WeaponItem {

    /**
     * The constructor for the Grossmesser, parses all data into the superclass's constructor.
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "Hits", 85 );
        this.addCapability(State.HAS_SKILL);
    }

    /**
     * Returns the Spinning Attack action, which is the only action the Grossmesser can perform.
     * @param target The target of the attack.
     * @param direction The direction of the attack.
     * @return The Spinning Attack action.
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new SpinningAttack(this);
    }
}
