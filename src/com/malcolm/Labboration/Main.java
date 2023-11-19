package com.malcolm.Labboration;

import java.util.Scanner;

import static com.malcolm.Labboration.Colors.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    // =====>> KLAR <<===== ??
    public static void main(String[] args) {

        // SKAPA OBJECT FÖR PLAYER OCH MONSTER
        Player player = new Player(
                8,
                5,
                8,
                50,
                1,
                5
        );
        Monster monster = new Monster(
                "The Valhalla Vi-King",
                6,
                70,
                20
        );

        // =====> VÄLKOMSTTEXT <=====
        System.out.println(RED + "Welcome dearest fellower");
        System.out.println("Please state you birth name? ");
        player.setName(sc.nextLine());
        System.out.println(player.getName() + "... I hope you're ready" + RESET);

        // =====>> KLAR <<=====
        /*
        SKAPA EN MENY DÄR MAN KAN VÄLJA MELLAN ATT SLÅS, SE SPELAR-STATUS ELLER ATT AVSLUTA SPELET
         */
        do {
            System.out.println(RED + """
                                        
                    -----> START MENU <-----
                    Pleas make a selection
                    1. Fight A Monster
                    2. See Your Status
                    3. Exit Game
                    """ + RESET);

            switch (sc.nextLine()) {
                case "1" -> fightMenu(player, monster);
                case "2" -> player.getStatus();
                case "3" -> System.exit(0);

                default -> System.out.println(RED + "Try again" + RESET);
            }

        } while (true);
    }

    // =====>> KLAR <<=====
    /*
    MED DENNA METOD SKA MAN KUNNA ATTACKERA, MAN KUNNA FLY TILLBAKA TILL START MENU
    OCH MAN SKA KUNNA SE STATUS PÅ MONSTRET.
     */
    public static void fightMenu(Player player, Monster monster) {
        boolean isPlaying = true;
        do {
            System.out.println(RED + """ 
                                        
                    -----> BATTLE MENU <-----
                    You have chosen to battle with a monster.
                    Now chose between:
                    1. Attack
                    2. Escape to Start Menu
                    3. Monster status
                    """ + RESET);
            switch (sc.nextLine()) {
                case "1" -> attack(player, monster);
                case "2" -> isPlaying = false; //player.flee();
                case "3" -> monster.getMonsterStatus();

                default -> System.out.println("Try again");
            }
        } while (isPlaying);
    }

    // =====>> KLAR <<===== ??
    /*
    - MED DENNA METOD SKA SPELAREN OCH MONSTRET MÖTAS I EN KAMP DÄR BÅDA
      SLÅR VARSIN GÅNG MOT VARANDRA OCH FÖRLORAR HP.
    - VINNER SPELAREN FÅR MAN ETT MEDDELANDE SOM BEKRÄFTAR DET.
    - VINNER MONSTRET FÅR MAN ETT MEDDELANDE SOM BEKRÄFTAR DET.
     */
    public static void attack(Player player, Monster monster) {
        System.out.println(YELLOW + player.getName() + " is attacking " + monster.getName());

        monster.attack(player.calculateDamage(player.getStrength()));
        player.attack(monster.getStrength());

        System.out.println("\n" + YELLOW + player.getName() + " has " + player.getHealth() + "HP left");
        System.out.println(monster.getName() + " has " + monster.getHealth() + "HP left" + RESET);

        if (player.getHealth() <= 0) {
            System.out.println(BLUE + "...");
            System.out.println("You just got killed by " + monster.getName() + ". Sorry to say, but you lost.. BYE!" + RESET);
            System.exit(0);
        }

        if (monster.getHealth() <= 0) {
            System.out.println(GREEN + "...");
            System.out.println("You just killed a monster, AWESOME!!");
            //System.out.println("This is your remaining health: --> " + player.getHealth() + "HP <--\n" + RESET);
            System.out.println(BLUE + "\nYour health has been restored");
            System.out.println("..." + RESET);

            monster.setHealth(50);
            player.setHealth(50);

            player.levelUp(85);
        }
    }




}
