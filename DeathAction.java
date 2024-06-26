package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import edu.monash.fit2099.game.RuneManager;
import edu.monash.fit2099.game.enemies.HeavySkeletalSwordsmen;
import edu.monash.fit2099.game.enemies.PileOfBones;
import edu.monash.fit2099.game.items.Runes;
import edu.monash.fit2099.game.reset.ResetManager;
import edu.monash.fit2099.game.utils.FancyMessage;
import edu.monash.fit2099.game.utils.State;

/**
 * An action executed if an actor is killed.
 * Created By:
 * @author Adrian Kristanto
 * Edited By:
 * @author Benjamin Saunders
 * @author Joshua Leong
 * Last Modified: 01/05/2023
 * @see Action
 */
public class DeathAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    private Actor attacker;

    /**
     * the empty constructor when attacker is unknown.
     */
    public DeathAction() {}

    /**
     * Constructor when attacker is known.
     *
     * @param attacker the Actor that killed the target
     */
    public DeathAction(Actor attacker) {
       this.attacker = attacker;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String message = "";
        if (target.hasCapability(State.SPAWNS_PILE_OF_BONES)) {
            Location current = map.locationOf(target);
            map.removeActor(target);
            map.addActor(new PileOfBones(target), current);
            return target + " has been struck down and became a Pile Of Bones.";
        }

        ActionList dropActions = new ActionList();
        // drop all items
        if (attacker.hasCapability(State.IS_PLAYER)) {

            for (Item item : target.getItemInventory())
                dropActions.add(item.getDropAction(target));
            for (WeaponItem weapon : target.getWeaponInventory())
                dropActions.add(weapon.getDropAction(target));
            for (Action drop : dropActions)
                drop.execute(target, map);

            RuneManager runeManager = RuneManager.getInstance();
            runeManager.addRunes(attacker, runeManager.getRunes(target));
            message += String.format("%s dropped %d runes\n", target, RuneManager.getInstance().getRunes(target));
        }

        // PLAYER DEATH
        if (target.hasCapability(State.IS_PLAYER)) {
            (new Display()).println(FancyMessage.YOU_DIED);

            RuneManager runeManager = RuneManager.getInstance();
            int value = runeManager.getRunes(target);
            runeManager.setRunes(target, 0);

            ResetManager resetManager = ResetManager.getInstance();
            if (value != 0) {
                Runes runes = new Runes(value);
                runes.drop(resetManager.getLastLocation()).execute(target, map);
            }


            resetManager.run(map, true);
            map.moveActor(target, resetManager.getLastSite());
            return String.format("%s has died and dropped %d runes", target, value);
        }
        // remove actor
        map.removeActor(target);
        return message + menuDescription(target);
    }

    /**
     * Returns a string describing the action suitable for displaying in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player was killed by Skellie"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " was killed by " + attacker;
    }
}
