package ca.sheridancollege.project;

import java.util.Scanner;

// Represents the main game logic and flow
public class Game {
    private final Deck deck; // The deck of cards used in the game
    private final Player[] players; // Array of players participating in the game

    // Constructor to initialize the game with player names
    public Game(String[] playerNames) {
        deck = new Deck(); // Initialize the deck of cards
        players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            players[i] = new Player(playerNames[i]); // Create and add players to the game
        }
    }

    // Distribute a specified number of cards to each player
    public void dealCards(int numCards) {
        for (int i = 0; i < numCards; i++) {
            for (Player player : players) {
                Card card = deck.dealCard();
                if (card != null) {
                    player.addCard(card); // Add dealt card to player's hand
                }
            }
        }
    }

    // Main game loop
    public void play() {
        dealCards(5); // Start by dealing 5 cards to each player
        try (Scanner scanner = new Scanner(System.in)) {
            while (!isGameOver()) {
                for (Player player : players) {
                    if (isGameOver()) {
                        break;
                    }

                    System.out.println("\n" + player.getName() + "'s Turn");
                    System.out.println("Your Hand: " + player.getHand());

                    System.out.print("Ask for a card rank: ");
                    String rank = scanner.nextLine().trim().toUpperCase();

                    if (!isValidRank(rank)) {
                        System.out.println("Invalid input. Please enter a valid card rank.");
                        continue;
                    }

                    Player opponent = getOpponent(player);

                    if (!opponent.hasCard(rank)) {
                        System.out.println("Go Fish!");
                        Card drawnCard = deck.dealCard();
                        if (drawnCard != null) {
                            player.addCard(drawnCard);
                            System.out.println("You drew: " + drawnCard);
                        }
                    } else {
                        transferCards(player, opponent, rank);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging purposes
        }

        displayResults(); // Display the final results of the game
    }

    // Check if the provided rank is valid
    private boolean isValidRank(String rank) {
        String[] validRanks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING", "ACE"};
        for (String validRank : validRanks) {
            if (validRank.equals(rank)) {
                return true;
            }
        }
        return false;
    }

    // Get the opponent player for a given player's turn
    private Player getOpponent(Player currentPlayer) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Ask which player: ");
                String playerName = scanner.nextLine().trim();
                for (Player player : players) {
                    if (player.getName().equalsIgnoreCase(playerName) && !player.equals(currentPlayer)) {
                        return player;
                    }
                }
                System.out.println("Invalid opponent. Try again.");
            }
        }
    }

    // Transfer cards from opponent to player based on requested rank
    private void transferCards(Player player, Player opponent, String rank) {
        int transferCount = 0;
        for (Card card : player.getHand()) {
            if (card.getRank().equalsIgnoreCase(rank)) {
                player.removeCard(card);
                opponent.addCard(card);
                transferCount++;
            }
        }
        System.out.println("You got " + transferCount + " card(s) from " + opponent.getName() + ".");
    }

    // Check if the game is over (all players have empty hands)
    private boolean isGameOver() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    // Display the final results of the game, including players' books and scores
    private void displayResults() {
        System.out.println("\n--- Game Over ---");
        for (Player player : players) {
            System.out.println(player.getName() + "'s Books: " + player.getBooks());
            System.out.println(player.getName() + "'s Score: " + player.getBooks().size());
        }
    }

    // Entry point of the program
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the number of players: ");
            int numPlayers = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            String[] playerNames = new String[numPlayers];
            for (int i = 0; i < numPlayers; i++) {
                System.out.print("Enter the name of Player " + (i + 1) + ": ");
                playerNames[i] = scanner.nextLine().trim();
            }

            Game game = new Game(playerNames);
            game.play(); // Start the game
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging purposes
        }
    }
}
