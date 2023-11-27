package com.malcolm.Labboration;

import java.util.Random;
import static com.malcolm.Labboration.Colors.*;

public class Player implements ICombat{

    private String name;
    private int strength; // DETTA SKA PÅVERKA HUR MYCKET SKADA MAN UTFÖR MOT MONSTRET
    private int intelligence; // JU MER MAN HAR DESTO STÖRRE CHANS FÖR ATT DUBBLA SIN SKADA
    private int agility; // DETTA ÖKAR CHANSEN ATT MOTSTÅNDAREN MISSAR SPELAREN
    private int health; // TODO - WUT??? "UTÖKAS MAXIMALA ANTALET LIV SPELAREN HAR"
    private int experience; // VARJE 100:E POÄNG ÄR 1 LVL UPP (LVL +1)
    private int level; // VID VARJE LVL ÖKAR STYRKA, INTELLIGENCE & AGILITY MED 2.
    private int baseDamage; // TODO - WUUUT?? KALKYLERA HUR MYCKET SKADA SPELAREN TILLFÖR VID VARJE ATTACK.

    // =====>> KLAR <<=====
    /*
    DETTA ÄR EN KONSTRUKTOR FÖR SPELAREN OCH DESS ATTRIBUTER
     */
    public Player(int strength, int intelligence, int agility, int health, int level, int baseDamage) {
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.health = health;
        this.level = level;
        this.baseDamage = baseDamage;
    }

    // =====>> KLAR <<=====
    /*
    =====>> GETTERS AND SETTERS <<=====
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

    public int getBaseDamage() {
        return baseDamage;
    }
    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    // =====>> KLAR <<=====
    /*
    MED DENNA METOD KAN MAN FÅ FRAM STATUS/EGENSKAPER PÅ SPELAREN
     */
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

    // =====>> KLAR <<=====
    /*
    MED DENNA METOD KAN SPELAREN GÅ UPP I LVL MED HJÄLP AV EXP.
     */
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

                System.out.println(PURPLE + "YOU REACHED NEW LEVEL.. CONGRATS!!" + RESET);
            }
        }

        System.out.println(Colors.BLUE + "CURRENT EXP");
        System.out.println(getExperience());

        System.out.println("PLAYER LEVEL:");
        System.out.println(getLevel() + Colors.RESET);
    }

    // =====>> KLAR <<=====
    public int calculateDamage(int strength) {
        Random random = new Random();

        int randomNr = random.nextInt(100);

        if (randomNr < intelligence) {
            strength = baseDamage + (strength * 2 / 2 + 1);
            System.out.println(PURPLE + "===> CRITICAL HIT ON MONSTER! <===");
            return strength;
        } else {
            strength = baseDamage + (strength * 2 / 4 + 1);
            return strength;
        }

    }

    // =====>> KLAR <<=====
    /*
    MED DENNA METOD TAR SPELAREN SKADA AV MONSTRET "ATTACKERAR"
     */
    @Override
    public void attack(int damage) {
        Random random = new Random();

        int testYourLuck = random.nextInt(100) + 1;

        if (testYourLuck < agility) {
            System.out.println(PURPLE + "===> YOU DODGED!! <===" + RESET);
        } else {
            setHealth(getHealth() - damage);
        }
    }

    @Override
    public int getDamage() {
        return baseDamage + strength;
    }

}
