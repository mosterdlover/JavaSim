package com.unasat.logics;

import java.util.Random;

import com.unasat.classes.Hero;

public class AI {
    String name;
    public int hp;
    int attack;                                                                                                                                                                                                                                                        
    Random rand = new Random();

    public AI(String name) {
        this.name = name;
        this.hp = 100;
        this.attack = 15;
    }

    public void takeTurn(Hero hero) {
        int action = rand.nextInt(3);
        switch (action) {
            case 0 -> {
                int damage = this.attack + rand.nextInt(5);
                hero.hp -= damage;
                System.out.println(name + " attacks for " + damage + " damage.");
            }
            case 1 -> {
                this.hp += 5;
                System.out.println(name + " defends and regains some HP.");
            }
            case 2 -> {
                this.attack += 10;
                System.out.println(name + " uses special skill to increase attack temporarily.");
            }
        }
    }

    public boolean isAlive() {
        return this.hp > 0;
    }
}
