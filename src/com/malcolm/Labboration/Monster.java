package com.malcolm.Labboration;

import java.util.Random;
import static com.malcolm.Labboration.Colors.*;

public class Monster implements ICombat {

    // ----> MONSTER VARIABLES <----
    private String name;
    private int strength;
    private int health;
    private int agility;

    // =====>> KLAR <<=====
    /*
    DETTA ÄR EN KONSTRUKTOR FÖR MONSTRETS ATTRIBUTER
     */
    public Monster(String name, int strength, int health, int agility) {
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.agility = agility;
    }

    //=====>> KLAR <<=====
    /*
    =====>> GETTERS AND SETTERS FOR MONSTER <<=====
     */
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getAgility() {
        return agility;
    }
    public void setAgility(int agility) {
        this.agility = agility;
    }

    // =====>> KLAR <<=====
    /*
    DENNA METOD VISAR MONSTRETS STATUS/EGENSKAPER
     */
    public void getMonsterStatus() {
        System.out.printf("Name: %s %n", name);
        System.out.printf("Strength: %d %n", strength);
        System.out.printf("Health: %d %n", health);
        System.out.printf("Agility: %d %n", agility);
    }

    @Override
    public void attack(int damage) {
        Random random = new Random();

        int testYourLuck = random.nextInt(100) + 1;

        if (testYourLuck < agility) {
            System.out.println(PURPLE + "===> MONSTER DODGED!! <===" + RESET);
        } else {
            setHealth(getHealth() - damage);
        }
    }

    @Override
    public void flee() {

    }
}
