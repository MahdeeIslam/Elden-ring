package edu.monash.fit2099.game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import edu.monash.fit2099.game.actions.special_attacks.Unsheathe;
import edu.monash.fit2099.game.utils.State;

/**
 * A class that represents the Uchigatana.
 * Created by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 */
public class Uchigatana extends WeaponItem {
    /**
     * The constructor for the Uchigatana, parses all data into the superclass's constructor.
     */
    public Uchigatana() {
        super("Uchigatana", ')', 115, "slices", 80);
        this.addCapability(State.HAS_SKILL);
    }

    /**
     * Returns the Unsheathe action, which is the only action the Uchigatana can perform.
     * @param target The target of the attack.
     * @param direction The direction of the attack.
     * @return The Unsheathe action.
     */
    @Override
    public Action getSkill(Actor target, String direction) {
//        return new AttackAction()
        return new Unsheathe(this, target, direction);
    }
}
