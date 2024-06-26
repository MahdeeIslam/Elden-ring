package edu.monash.fit2099.game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.game.items.FlaskOfCrimsonTears;

/**
 * An action executed if an actor uses the FlaskOfCrimsonTears.
 * Created By:
 * @author Benjamin Saunders
 * Last Modified: 02/05/2023
 * @see Action
 * @see edu.monash.fit2099.game.items.FlaskOfCrimsonTears
 */
public class UseFlaskAction extends Action {

    private FlaskOfCrimsonTears flask;

    public UseFlaskAction(FlaskOfCrimsonTears flask) {
        this.flask = flask;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.heal(flask.getHealAmount());
        flask.removeCharge();
        return menuDescription(actor) + " and healed " + flask.getHealAmount() + " HP";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " uses the Flask Of Crimson Tears (" + this.flask.getCurrentCharge() + "/" + this.flask.getInitialCharges() + ")";
    }
}
