package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a deck of playing cards
public class Deck {
    private List<Card> cards; // List to store the cards in the deck

    // Constructor to initialize the deck with a standard set of cards
    public Deck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        cards = new ArrayList<>();
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(rank, suit)); // Create and add cards to the deck
            }
        }

        shuffle(); // Shuffle the deck upon initialization
    }

    // Shuffle the cards in the deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Deal a card from the top of the deck
    public Card dealCard() {
        if (cards.isEmpty()) {
            return null; // Return null if the deck is empty
        }
        return cards.remove(0); // Remove and return the card from the top
    }
}
