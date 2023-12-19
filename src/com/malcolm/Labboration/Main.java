package com.malcolm.Labboration;

import java.util.Scanner;
import static com.malcolm.Labboration.Colors.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Player player = new Player(
                8,
                5,
                8,
                50,
                1,
                5
        );
        Monster monster = new Monster(
                6,
                65,
                18,
                3
        );

        // =====> VÄLKOMSTTEXT <=====
        System.out.println(GREEN_BOLD + "You are entering a dangerous game..");
        System.out.println("State you birth name");
        player.setName(sc.nextLine());
        System.out.println("I hope you're ready " + player.getName() + RESET);

        // STARTMENY
        do {
            System.out.println(YELLOW + """
                                        
                    -----> START MENU <-----
                    Make a selection
                    1. Fight A Monster
                    2. See Your Status
                    3. Exit Game
                    """ + RESET);

            switch (sc.nextLine()) {
                case "1" -> fightMenu(player, monster);
                case "2" -> player.getStatus();
                case "3" -> System.exit(0);

                default -> System.out.println(WHITE_BOLD + "Not a correct input.. Try again" + RESET);
            }

        } while (true);
    }

    // ATTACKMENYN
    public static void fightMenu(Player player, Monster monster) {
        boolean isPlaying = true;
        do {
            System.out.println(YELLOW + """ 
                                        
                    -----> BATTLE MENU <-----
                    You have chosen to battle with a monster.
                    Now chose between:
                    1. Attack
                    2. Escape to Start Menu
                    3. Monster status
                    """ + RESET);
            switch (sc.nextLine()) {
                case "1" -> attack(player, monster);
                case "2" -> isPlaying = false;
                case "3" -> monster.getMonsterStatus();

                default -> System.out.println(WHITE_BOLD + "Not a correct input.. Try again" + RESET);
            }
        } while (isPlaying);
    }

    // MED DENNA METOD MÖTS SPELAREN OCH MONSTRET I EN KAMP
    public static void attack(Player player, Monster monster) {
        System.out.println(PURPLE + player.getName() + " is attacking " + monster.getName());

        monster.attack(player.calculateDamage(player.getStrength()));
        player.attack(monster.getStrength());

        System.out.println("\n" + PURPLE + player.getName() + " has " + GREEN + player.getHealth() + "HP" + RESET + PURPLE + " left");
        System.out.println(monster.getName() + " has " + GREEN + monster.getHealth() + "HP" + RESET + PURPLE + " left");

        if (player.getHealth() <= 0) {
            System.out.println(BLUE + "...");
            System.out.println("You just got killed by " + monster.getName() + ". Sorry to say, but you lost.. BYE!" + RESET);
            System.exit(0);
        }

        if (monster.getHealth() <= 0) {
            System.out.println("\n...YOU KILLED THE MONSTER, AWESOME!!");
            System.out.println(BLUE + "\n===> Your health has been restored <===\n" + RESET);

            monster.setHealth(50);
            monster.setAgility(monster.getAgility() + 2);
            monster.setStrength(monster.getStrength() + 2);
            player.setHealth(50);

            player.levelUp(85);
        }
    }
}
