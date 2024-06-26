package edu.monash.fit2099.game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;


import edu.monash.fit2099.game.actions.ChooseClassAction;
import edu.monash.fit2099.game.grounds.Dirt;
import edu.monash.fit2099.game.grounds.Floor;
import edu.monash.fit2099.game.grounds.Wall;
import edu.monash.fit2099.game.grounds.environments.Graveyard;
import edu.monash.fit2099.game.grounds.environments.GustOfWind;
import edu.monash.fit2099.game.grounds.environments.PuddleOfWater;
import edu.monash.fit2099.game.grounds.environments.SiteOfLostGrace;
import edu.monash.fit2099.game.items.FlaskOfCrimsonTears;
import edu.monash.fit2099.game.items.Runes;
import edu.monash.fit2099.game.npcs.MerchantKale;
import edu.monash.fit2099.game.npcs.Trader;
import edu.monash.fit2099.game.players.Bandit;
import edu.monash.fit2099.game.players.Player;
import edu.monash.fit2099.game.players.Samurai;
import edu.monash.fit2099.game.players.Wretch;
import edu.monash.fit2099.game.reset.ResetManager;
import edu.monash.fit2099.game.utils.FancyMessage;


import java.util.Arrays;
import java.util.List;

/**
 * The main application for the game.
 * Created by:
 * @author Benjamin Saunders
 * Last Modified: 02/05/2023
 */
public class Application {
    public static void main(String[] args) {
        /*
        * **DISCLAIMER**
        * Full disclaimer, Benjamin Saunders used the foundation model "Github - Copilot" to aid in the development of this game.
        * URL: https://github.com/features/copilot
        * Uses: Copilot did not have access to public github code (was turned off when created) and was mainly used for the
        * generation of javadoc comments and some code snippets.
        */

        World world = new World(new Display());
        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new PuddleOfWater(), new Graveyard(), new GustOfWind(), new Floor());
        GameMap gameMap;


        List<String> map = Arrays.asList(
            "..nnnn................................................~~~~~~~~~~~~~~~~~~~~~",
            "......................#####....######..................~~~~~~~~~~~~~~~~~~~~",
            "..nnnn................#..___....____#...................~~~~~~~~~~~~~~~~~~~",
            "..................................__#....................~~~~~~~~~~~~~~~~~~",
            "......................._____........#.....................~~~~~~~~~~~~~~~~~",
            "......................#............_#......................~~~~~~~~~~~~~~~~",
            "......................#...........###......................................",
            "...........................................................................",
            "...........................................................................",
            "~~~~~~~~~~~.......................###___###................................",
            "~~~~~~~~~~~~......................________#....nnnn........................",
            "~~~~~~~~~~~~~.....................#________................................",
            "~~~~~~~~~~~~......................#_______#....nnnn........................",
            "~~~~~~~~~~~.......................###___###................................",
            "~~~~~~~~~~..........................#___#..................................",
            "...........................................................................",
            "...........................................................................",
            "...........................................................................",
            "..####__##...........................................&&&......######..##...",
            "..#.....__...........................................&&&......#....____....",
            "..#___..............&&&..............................&&&........__.....#...",
            "..####__###.........&&&......................................._.....__.#...",
            "....................&&&.......................................###..__###...",
            "..........................................................................."
            );


        // Temp game map (Feel free to edit)
        /* List<String> map = Arrays.asList(
                "..................................................",
                "...##.............................................",
                "...##.............................................",
                "...##.............................................",
                "...##...................................&&&.......",
                "...##...................................&&&.......",
                "...##.............................................",
                ".................................................."
        );
        */

        /* List<String> map = Arrays.asList(
                ".........",
                ".........",
                ".........",
                ".........",
                "........."
        ); */

        /* List<String> map = Arrays.asList(
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.............._.#________",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~..........._....#________",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~...........__...#________",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~................#________",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~..........._.....#________",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.................#________",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~........._......##________",
                ".~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~........._.......#______###",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~........_........#___####..",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~........__........#___#.....",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.......___........#___#.....",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~......_._..._.._.............",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~........._.........._..__.__._.",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.........___..___._._.._._....._._",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~........._.........................",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~..........._.._.........................",
                ".~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~..............._.._............................",
                "~~.~~.~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~...................._............................",
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~................_.._...............................",
                "~~.~.~~~...~~~~~~~~~~~~~~~~~~~~~~~~~................_....................................",
                "~~.~........~~~~~~~~~~~~~~~~~~~~................._...._..................................",
                "~.............~~~~~~~~~~~~........................._.....................................",
                "##########................................._..__._.......................................",
                "#________#..............................._..__._.........................................",
                "#_______#.........................._...___._...___.......................................",
                "#________..._......_.._........_..___._.............__._.................................",
                "####_____..._..__.__._.._..__.____...........&.&.......__................................",
                "#________................__.__.......&.&&.&.&&&&&&&.....___..............................",
                "#_______#................._........&&&.&&&&&&&&..&...........__..........................",
                "#________#....&&..........._._.._....&..&&&&&&&&&&.&&&&....._.__._.......................",
                "##########...&.&&.&&.&&...___....__..&&&&.&&&&&.&.&.&&.&&&.......__......................",
                "............&.&&&.....&..........__....&&&.&&..&...&&&..&&........__...................._",
                ".........&.&.&&&...&.&...._.___..._._..&&&&....&&&&&.&&&&&&&&&....._..__..............__.",
                "........&.&...&&&&&&&&....._..._............&&&&&&...&.&&.&&&&&&....._..__............._.",
                ".&&.&&&&&&&..&.&.&&&&........_......._.....&&&&..&&..&..&&&&.&&&&&......._..........._...",
                ".&.&..&&&.&.&....&&&....._.._........_._...&&....................&&&&....__._..__......_.",
                "....&&.&&.&&&&&...&....__.._....&......_...........................&&.&&...._.....__..__.",
                ".&..&&&.&&&&.&&.&&...._........&&.....____....####.##.####..###.......&&&.....__.........",
                ".&&&&.&&.&.&..&....._._.._.....&&........_....#...............#..&&&...&&.&.&............",
                ".&&.&&&....&&&.&....._._.....&&.........___...#.n.n.n.n.n.n.n.#..&&&.&....&&&&&&&&.....&.",
                ".&.&..&..&.&&&...._.__......&.&&&&........__..#._._._._._._._.#...&.&..&&..&....&&&&&&&&.",
                ".&&.....&&&&&..._..........&&&..&&&&&...._.._..._._._._._._._......&&..&&&&&&&&&&&.&.&&&.",
                ".&&&&..&&&...._.._.........&.&&&.&..&........_.....................&&..&...&.&.&.&.&&.&&.",
                ".&&..&.......__._........&&&..&.&&.&&&............................&&&.&&&...&&&.&.&.&.&..",
                ".&......._...._.._.....&&&&&.&.&..&&&&&&&...__................#.....&.&&.&&..&.&.&&.&&&..",
                "...._..__.__...__.....&&.&...&&&&&&&&&...&....................#..&&&..&...&&&.&..&.&&....",
                "_......_......_.....&&&.&..&&....&...&.&.&....................#....&&.&.&&.&.&.&&.&&&&...",
                "..__._...._.......&&&...&.&&&&&&...&&...&&&...#._._._._._._._....&&.&.&&&&.&&.&.&&&&.&...",
                ".___._..........&&&&&&.&.&&&&&&&&&&&....&&&...#._._._._._._._.#..&&&&.&.&&..&&&&&&&&&&&..",
                "...............&.&&.....&..&&&&.&&.&.&&&&&&...#.n.n.n.n.n.n.n......&&&&&&&&.&.&&&&&&&&&&.",
                ".....&&&&.....&.&...&&&.&&.&&&.&......&&&&&&&.#...............#....&&&&&&&.&..&&.....&...",
                "...&&&&....&&.&&&&&.&.............&.&.&.......####.##.#########..........................",
                "........................................................................................."
        ); */
        gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        // Class Selection Menu
        Menu classSelect = new Menu();
        ActionList classes = new ActionList();

        Player placeholder = new Samurai("Placeholder", '-', 1);

        classes.add(new ChooseClassAction(placeholder, "s", "Samurai"));
        classes.add(new ChooseClassAction(placeholder, "b", "Bandit"));
        classes.add(new ChooseClassAction(placeholder, "w", "Wretch"));

        Player player = placeholder;
        Display currentDisplay = new Display();
        currentDisplay.println(FancyMessage.ELDEN_RING);
        currentDisplay.print("Select your role:\n");

        Action chosenClass = classSelect.showMenu(placeholder, classes, currentDisplay);
        switch (chosenClass.menuDescription(placeholder)) {
            case "Samurai" -> player = new Samurai("Tarnished", '@', 455);
            case "Bandit" -> player = new Bandit("Tarnished", '@', 415);
            case "Wretch" -> player = new Wretch("Tarnished", '@', 415);
        }

        // Reset Manager initialization
        ResetManager resetManager = ResetManager.getInstance();

        player.setFlask(new FlaskOfCrimsonTears());

        Location firstSite = gameMap.at(38, 11);
        firstSite.setGround(new SiteOfLostGrace("The First Step"));

        resetManager.setLastSite(firstSite);

        // Spawns the player @ 36, 10
        world.addPlayer(player, gameMap.at(36, 10));

        // Spawns Merchant Kale @ 38, 12
        Trader kale = new MerchantKale();
        gameMap.at(38, 12).addActor(kale);


        world.run();
    }
}
