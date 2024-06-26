package edu.monash.fit2099.game.weapons.fake_intrinsics;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import edu.monash.fit2099.game.actions.special_attacks.Slam;
import edu.monash.fit2099.game.utils.State;

/**
 * A class that represents the last ditch effort to comply with the requirements whilst not editing the engine.
 * Why on earth does the engine not have a way to get the skill of an intrinsic weapon?
 * Crab Claws Btw
 * Created by:
 * @author Benjamin Saunders
 * Last Modified: 01/05/2023
 * @version 1.0.0
 */
public class CrabClaws extends WeaponItem {
    /**
     * The constructor for the crab claws, parses all data into the superclass's constructor.
     */
    public CrabClaws() {
        super("Crab Claws", 'C', 97, "slams", 90);
        this.addCapability(State.HAS_SKILL);
    }

    /**
     * The claws cannot be dropped (they are attached) so this method returns null.
     * @param actor The actor that is dropping the crab claws.
     * @return null
     */
    @Override
    public DropAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Returns the Slam action, which is the only action the crab claws can perform.
     * @param target The target of the attack.
     * @param direction The direction of the attack.
     * @return The Slam action.
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new Slam(this);
    }
}
