package com.malcolm.Labboration.Model;
import com.malcolm.Labboration.DBConnection;
import com.malcolm.Labboration.Main;
import com.malcolm.Labboration.Monster;
import com.malcolm.Labboration.Player;

import java.util.Scanner;
import static com.malcolm.Labboration.Colors.*;
import static com.malcolm.Labboration.Colors.RESET;

public class Menus {

    static Scanner sc = new Scanner(System.in);

    public static void startMenu (Player player, Monster monster, DBConnection db) {
        do {
            System.out.println(YELLOW + """
                                        
                    -----> START MENU <-----
                    Make a selection
                    1. New Game
                    2. Load Game
                    3. Run like a coward
                    """ + RESET);
            switch (sc.nextLine()) {
                case "1" -> newGame(player, monster, db);
                case "2" -> loadGame(db, player, monster);
                case "3" -> System.exit(0);

                default -> System.out.println(WHITE_BOLD + "Not a correct input.. Try again" + RESET);
            }
        } while (true);
    }


    public static void newGame(Player player, Monster monster, DBConnection db) {
        do {
            System.out.println(YELLOW + """
                                        
                    -----> GAME MENU <-----
                    Make a selection
                    1. Start Battle!
                    2. See Your Stats
                    3. Run like a coward
                    """ + RESET);
            switch (sc.nextLine()) {
                case "1" -> fightMenu(player, monster, db);
                case "2" -> player.getStatus();
                case "3" -> System.exit(0);

                default -> System.out.println(WHITE_BOLD + "Not a correct input.. Try again" + RESET);
            }
        } while (true);
    }


    public static void loadGame (DBConnection db, Player player, Monster monster){
        db.showSavedPlayers();
        int getID = sc.nextInt();
        db.selectPlayer(player, getID);
        newGame(player, monster, db);
    }


    public static void fightMenu(Player player, Monster monster, DBConnection db) {
        do {
            System.out.println(YELLOW + """ 
                                        
                    -----> FIGHT MENU <-----
                    You have chosen to battle with a monster.
                    Now chose between:
                    1. Attack
                    2. Escape to Game Menu
                    3. Monster status
                    """ + RESET);
            switch (sc.nextLine()) {
                case "1" -> Main.combat(player, monster, db);
                case "2" -> newGame(player, monster, db);
                case "3" -> monster.getMonsterStatus();

                default -> System.out.println(WHITE_BOLD + "Not a correct input.. Try again" + RESET);
            }
        } while (true);
    }
}
