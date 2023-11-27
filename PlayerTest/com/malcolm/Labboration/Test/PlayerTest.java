package com.malcolm.Labboration.Test;

import com.malcolm.Labboration.Monster;
import com.malcolm.Labboration.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    /*
    Player player = new Player(
            5,
            5,
            5,
            50,
            1,
            5
    );


    @Test
    public void reducePlayerHealth() {
        player.takeDamage(5);
        System.out.println(player.getHealth());

        assertEquals(45, player.getHealth());
    }

    @Test
    public void reducePlayerLevel() {
        player.setLevel(player.getLevel() - 1);

        assertEquals(1, player.getLevel());
    }

    @Test
    public void checkStartingHealth() {

    }

     */

    Player player = new Player(
            5,
            5,
            5,
            50,
            1,
            20
    );

    Monster monster = new Monster(1,1,1, 1);

    @Test
    void updateLvlTest() {
        player.setLevel(1);
        player.levelUp(100);
        assertEquals(2, player.getLevel());
    }

    @Test
    void takeDamageTest(){
        //99dmg + 1fromSTR.
        monster.setStrength(99);
        player.setHealth(100);
        player.attack(monster.getDamage());
        assertEquals(player.getHealth(),0);
    }

    @Test
    void getDamageTest(){
        //basedmg20+20fromSTR
        player.setStrength(20);
        assertEquals(player.getDamage(),40);
    }
}