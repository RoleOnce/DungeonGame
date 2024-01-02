package com.malcolm.Labboration;
import com.malcolm.Labboration.Model.Menus;

import java.util.Scanner;
import static com.malcolm.Labboration.Colors.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        DBConnection db = new DBConnection();
        Player player = new Player(10, 5, 8, 50, 1, 5);
        Monster monster = new Monster(5, 60, 10, 3);

        // =====> SKAPAR TABELLER I DATABASEN <=====
        //db.createTablePlayer();
        //db.createTableMonster();

        // =====> VÄLKOMSTTEXT <=====
        System.out.println(GREEN_BOLD + "You are entering a dangerous game..");
        System.out.println("State you birth name");
        player.setName(sc.nextLine());
        System.out.println("I hope you're ready " + player.getName() + RESET);

        // =====> SKAPAR SPELARE/MONSTER MED UNIKT ID <=====
        db.createPlayer(player);
        db.createMonster(monster);

        // STARTMENY
        Menus.startMenu(player, monster, db);

    }

    // MED DENNA METOD MÖTS SPELAREN OCH MONSTRET I EN KAMP
    public static void combat(Player player, Monster monster, DBConnection db) {
        System.out.println(PURPLE + player.getName() + " is attacking " + monster.getName());

        monster.attack(player.calculateDamage(player.getStrength()));
        player.attack(monster.getStrength());

        System.out.println("\n" + PURPLE + player.getName() + " has " + GREEN + player.getHealth() + "HP" + RESET + PURPLE + " left");
        System.out.println(monster.getName() + " has " + GREEN + monster.getHealth() + "HP" + RESET + PURPLE + " left");

        if (player.getHealth() <= 0) {
            System.out.println(BLUE + "...");
            System.out.println("You just got killed by " + monster.getName() + ". Sorry to say, but you lost.. BYE!" + RESET);
            db.levelUP(player);
            System.exit(0);
        } else if (monster.getHealth() <= 0) {
            System.out.println("\n...YOU KILLED THE MONSTER, AWESOME!!");
            System.out.println(BLUE + "\n===> Your health has been restored <===\n" + RESET);

            monster.setHealth(50);
            monster.setAgility(monster.getAgility() + 2);
            monster.setStrength(monster.getStrength() + 2);
            player.setHealth(50);

            player.levelUp(85);
            db.levelUP(player);
        }
    }
}
