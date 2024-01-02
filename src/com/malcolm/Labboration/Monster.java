package com.malcolm.Labboration;
import java.util.Random;
import static com.malcolm.Labboration.Colors.*;

public class Monster implements ICombat {

    // VARIABLER
    private String name = "Valhalla Worrior";
    private int strength;
    private int health;
    private int agility;
    private int baseDamage;
    private int monsterID;

    // KONSTRUKTOR
    public Monster(int strength, int health, int agility, int baseDamage) {
        this.strength = strength;
        this.health = health;
        this.agility = agility;
        this.baseDamage = baseDamage;
    }

    // GETTERS OCH SETTERS
    public String getName() {
        return name;
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

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getMonsterID() {
        return monsterID;
    }
    public void setMonsterID(int monsterID) {
        this.monsterID = monsterID;
    }

    // MONSTRETS STATUS/EGENSKAPER
    public void getMonsterStatus() {
        System.out.printf("Name: %s %n", name);
        System.out.printf("Strength: %d %n", strength);
        System.out.printf("Health: %d %n", health);
        System.out.printf("Agility: %d %n", agility);
    }

    // FRÅN ICOMBAT
    @Override
    public void attack(int damage) {
        DBConnection db = new DBConnection();
        Random random = new Random();

        int testYourLuck = random.nextInt(100) + 1;

        if (testYourLuck < agility) {
            System.out.println(BLUE + "===> MONSTER DODGED!! <===" + RESET);
        } else {
            setHealth(getHealth() - damage);
        }
    }

    @Override
    public int getDamage() {
        return baseDamage + strength;
    }
}
