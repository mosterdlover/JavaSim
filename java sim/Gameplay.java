import java.util.Scanner;
import java.util.Random;

public class GamePlay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (!username.equals("player1") || !password.equals("password123")) {
            System.out.println("Invalid credentials.");
            return;
        }

        System.out.println("Login successful!");
        System.out.print("\nEnter your hero name: ");
        String heroName = scanner.nextLine();

        System.out.println("Choose your class:\n1. Warrior\n2. Mage\n3. Healer\n4. Assassin\n5. Tank");
        int choice = scanner.nextInt();
        scanner.nextLine();

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
        while (playerHero.isAlive() && ai.isAlive()) {
            System.out.println("\nYour turn:\n1. Attack\n2. Defend\n3. Special Skill");
            int action = scanner.nextInt();
            switch (action) {
                case 1 -> {
                    int damage = playerHero.attack();
                    ai.hp -= damage;
                    System.out.println(playerHero.name + " attacks for " + damage + " damage.");
                }
                case 2 -> playerHero.defend();
                case 3 -> playerHero.specialSkill();
                default -> System.out.println("Invalid choice.");
            }

            if (ai.isAlive()) ai.takeTurn(playerHero);

            System.out.println("\nCurrent Status:");
            playerHero.displayStatus();
            System.out.println("AI Status: HP " + ai.hp);
        }

        System.out.println(playerHero.isAlive() ? "You win!" : "AI wins!");
        scanner.close();
    }
}
