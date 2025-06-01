package com.unasat.logics;

import java.util.Scanner;

public class User {
    String username;
    String password;
    int gamesPlayed;

    public void Main(String username, String password) {
        this.username = username;
        this.password = password;
        this.gamesPlayed = 0;
    }

    public void editProfile(Scanner sc) {
        System.out.print("Nieuwe gebruikersnaam: ");
        this.username = sc.nextLine();
        System.out.print("Nieuw wachtwoord: ");
        this.password = sc.nextLine();
        System.out.println("Profiel bijgewerkt!");
    }

    public void display() {
        System.out.println("Gebruiker: " + username);
        System.out.println("Gespeelde games: " + gamesPlayed);
    }
}
