import java.util.Random;
import java.util.Scanner;

class Hero {
    String name;
    String heroClass;
    int hp;
    int attack;
    Random rand = new Random();

    Hero(String name, String heroClass, int hp, int attack) {
        this.name = name;
        this.heroClass = heroClass;
        this.hp = hp;
        this.attack = attack;
    }

    int attack() {
        return this.attack + rand.nextInt(5);  // Attack has some random variation
    }

    void defend() {
        this.hp += 5;  // Gain some HP when defending
        System.out.println(name + " is defending and regains some HP.");
    }

    void specialSkill() {
        this.attack += 10;  // Temporary increase in attack power
        System.out.println(name + " uses special skill, increasing attack temporarily!");
    }

    boolean isAlive() {
        return this.hp > 0;
    }

    void displayStatus() {
        System.out.println(heroClass + " " + name + " | HP: " + hp + " | Attack: " + attack);
    }
}

class Warrior extends Hero {
    Warrior(String name) {
        super(name, "Warrior", 100, 20);
    }
}

class Mage extends Hero {
    Mage(String name) {
        super(name, "Mage", 80, 15);
    }
}

class Healer extends Hero {
    Healer(String name) {
        super(name, "Healer", 70, 10);
    }

    @Override
    void specialSkill() {
        this.hp += 20;  // Healer restores HP with special skill
        System.out.println(name + " uses Heal, regaining a large amount of HP.");
    }
}

class Assassin extends Hero {
    Assassin(String name) {
        super(name, "Assassin", 90, 18);
    }
}

class Tank extends Hero {
    Tank(String name) {
        super(name, "Tank", 150, 10);
    }

    @Override
    void specialSkill() {
        this.hp += 30;  // Tank restores more HP with special skill
        System.out.println(name + " uses Shield Bash, restoring a large amount of HP.");
    }
}

class AI {
    String name;
    int hp;
    int attack;
    Random rand = new Random();

    AI(String name) {
        this.name = name;
        this.hp = 100;
        this.attack = 15;
    }

    void takeTurn(Hero hero) {
        int action = rand.nextInt(3);  // 0 = Attack, 1 = Defend, 2 = Special Skill

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

    boolean isAlive() {
        return this.hp > 0;
    }
}

public class GamePlay {

    // Sample user database
    static String correctUsername = "player1";
    static String correctPassword = "password123";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        // Login system
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (username.equals(correctUsername) && password.equals(correctPassword)) {
                loggedIn = true;
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid credentials. Try again.");
            }
        }

        // Game starts after successful login
        System.out.println("\nChoose your character:");
        System.out.print("Enter your hero name: ");
        String heroName = scanner.nextLine();

        System.out.println("Choose your class: ");
        System.out.println("1. Warrior\n2. Mage\n3. Healer\n4. Assassin\n5. Tank");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        Hero playerHero = switch (choice) {
            case 1 -> new Warrior(heroName);
            case 2 -> new Mage(heroName);
            case 3 -> new Healer(heroName);
            case 4 -> new Assassin(heroName);
            case 5 -> new Tank(heroName);
            default -> new Warrior(heroName);
        };

        AI ai = new AI("AI Opponent");

        System.out.println("\nThe battle begins!");
        boolean gameRunning = true;

        while (gameRunning) {
            // Player's turn
            System.out.println("\nYour turn:");
            System.out.println("1. Attack\n2. Defend\n3. Special Skill");
            System.out.print("Choose your action: ");
            int playerAction = scanner.nextInt();

            switch (playerAction) {
                case 1 -> {
                    int damage = playerHero.attack();
                    ai.hp -= damage;
                    System.out.println(playerHero.name + " attacks for " + damage + " damage.");
                }
                case 2 -> playerHero.defend();
                case 3 -> playerHero.specialSkill();
                default -> System.out.println("Invalid choice.");
            }

            // AI's turn
            if (ai.isAlive()) {
                ai.takeTurn(playerHero);
            }

            // Display statuses
            System.out.println("\nCurrent Status:");
            playerHero.displayStatus();
            System.out.println("AI Status: HP " + ai.hp);

            // Check for game over
            if (!playerHero.isAlive()) {
                System.out.println("\nYou are dead! AI wins!");
                gameRunning = false;
            } else if (!ai.isAlive()) {
                System.out.println("\nThe AI is defeated! You win!");
                gameRunning = false;
            }
        }

        scanner.close();
    }
}
