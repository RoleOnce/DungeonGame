package com.malcolm.Labboration;

import java.util.Random;
import static com.malcolm.Labboration.Colors.*;

public class Player implements ICombat{

    // VARIABLER
    private String name;
    private int strength;
    private int intelligence;
    private int agility;
    private int health;
    private int experience;
    private int level;
    private int baseDamage;

    // KONSTRUKTOR
    public Player(int strength, int intelligence, int agility, int health, int level, int baseDamage) {
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.health = health;
        this.level = level;
        this.baseDamage = baseDamage;
    }

    // GETTERS OCH SETTERS
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

    public int getIntelligence() {
        return intelligence;
    }
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getAgility() {
        return agility;
    }
    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    // SPELAREN STATUS/EGENSKAPER
    public void getStatus() {
        System.out.printf("Name: %s %n", name);
        System.out.printf("Strength: %d %n", strength);
        System.out.printf("Intelligence: %d %n", intelligence);
        System.out.printf("Agility: %d %n", agility);
        System.out.printf("Health: %d %n", health);
        System.out.printf("Experience: %d %n", experience);
        System.out.printf("Level: %d %n", level);
        System.out.printf("Base Damage: %d %n", baseDamage);
    }

    // MED DENNA METOD KAN SPELAREN GÅ UPP I LVL MED HJÄLP AV EXP
    public void levelUp(int amountOfExp) {
        for (int i = amountOfExp; i > 0; i--) {
            setExperience(getExperience() + 1);

            if (getExperience() == 100) {
                setLevel(getLevel() + 1);
                setExperience(0);
                setStrength(getStrength() + 2);
                setIntelligence(getIntelligence() + 2);
                setAgility(getAgility() + 2);
                setHealth(getHealth() + 2);

                System.out.println(BLUE + "YOU REACHED NEW LEVEL.. CONGRATS!!\n" + RESET);
            }
        }

        System.out.println(BLUE + "==============");
        System.out.println("CURRENT HEALTH");
        System.out.println(getHealth());

        System.out.println("CURRENT EXP");
        System.out.println(getExperience());

        System.out.println("PLAYER LEVEL:");
        System.out.println(getLevel());
        System.out.println("==============" + RESET);
    }

    // KALKYLERA SKADA
    public int calculateDamage(int strength) {
        Random random = new Random();

        int randomNr = random.nextInt(100);

        if (randomNr < intelligence) {
            strength = baseDamage + (strength * 2 / 2 + 1);
            System.out.println(BLUE + "===> CRITICAL HIT ON MONSTER! <===");
            return strength;
        } else {
            strength = baseDamage + (strength * 2 / 4 + 1);
            return strength;
        }
    }

    // MED DENNA METOD TAR SPELAREN SKADA AV MONSTRET "ATTACKERAR"
    @Override
    public void attack(int damage) {
        Random random = new Random();

        int testYourLuck = random.nextInt(100) + 1;

        if (testYourLuck < agility) {
            System.out.println(BLUE + "===> YOU DODGED!! <===" + RESET);
        } else {
            setHealth(getHealth() - damage);
        }
    }

    // FRÅN ICOMBAT
    @Override
    public int getDamage() {
        return baseDamage + strength;
    }

}
