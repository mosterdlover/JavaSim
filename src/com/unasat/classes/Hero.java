package com.unasat.classes;

import java.util.Random;

public class Hero {
    public String name;
    String heroClass;
    public int hp;
    int attack;
    Random rand = new Random();

    public Hero(String name, String heroClass, int hp, int attack) {
        this.name = name;
        this.heroClass = heroClass;
        this.hp = hp;
        this.attack = attack;
    }

    public int attack() {
        return this.attack + rand.nextInt(5);
    }

    public void defend() {
        this.hp += 5;
        System.out.println(name + " is defending and regains some HP.");
    }

    public void specialSkill() {
        this.attack += 10;
        System.out.println(name + " uses special skill, increasing attack temporarily!");
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public void displayStatus() {
        System.out.println(heroClass + " " + name + " | HP: " + hp + " | Attack: " + attack);
    }
}
