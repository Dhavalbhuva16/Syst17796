package ca.sheridancollege.project;

// Represents a playing card with a rank and suit
public class Card {
    private String rank; // The rank of the card (e.g., "2", "King", "Ace")
    private String suit; // The suit of the card (e.g., "Hearts", "Clubs", "Spades", "Diamonds")

    // Constructor to create a card with the given rank and suit
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Get the rank of the card
    public String getRank() {
        return rank;
    }

    // Get the suit of the card
    public String getSuit() {
        return suit;
    }

    // Generate a human-readable representation of the card
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
