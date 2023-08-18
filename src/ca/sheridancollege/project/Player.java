package ca.sheridancollege.project;

import ca.sheridancollege.project.Card;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;

    // Constructor to initialize the player's name and hand
    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    // Add a card to the player's hand
    public void addCard(Card card) {
        hand.add(card);
    }

    // Remove a card from the player's hand
    public void removeCard(Card card) {
        hand.remove(card);
    }

    // Check if the player has a card of a specific rank
    public boolean hasCard(String rank) {
        for (Card card : hand) {
            if (card.getRank().equalsIgnoreCase(rank)) {
                return true;
            }
        }
        return false;
    }

    // Get sets of four cards of the same rank (books) from the player's hand
    public List<String> getBooks() {
        List<String> books = new ArrayList<>();
        int[] rankCount = new int[13]; // There are 13 ranks (2 to Ace)

        // Count the occurrences of each rank in the hand
        for (Card card : hand) {
            String rank = card.getRank();
            int rankIndex = getRankIndex(rank);
            rankCount[rankIndex]++;
        }

        // If there are 4 cards of a rank, add it as a book
        for (int i = 0; i < rankCount.length; i++) {
            if (rankCount[i] == 4) {
                books.add(getRankByIndex(i));
            }
        }
        return books;
    }

    // Get the index of a rank in the ranks array
    private int getRankIndex(String rank) {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i].equalsIgnoreCase(rank)) {
                return i;
            }
        }
        return -1;
    }

    // Get the rank using an index
    private String getRankByIndex(int index) {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        if (index >= 0 && index < ranks.length) {
            return ranks[index];
        }
        return null;
    }

    // Get the player's name
    public String getName() {
        return name;
    }

    // Get the player's hand of cards
    public List<Card> getHand() {
        return hand;
    }
}
