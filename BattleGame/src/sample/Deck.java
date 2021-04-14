package sample;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {
    LinkedList<Card> cards = new LinkedList<>();

    public Deck() {
        //Creates a deck, populates it with cards, and shuffles it
        for(int i = 1; i < 53; i++) {
            cards.add(new Card(i));
        }
        Collections.shuffle(cards);
    }
    public Card drawCard() {
        //takes one card from the top of the deck
        return cards.pop();
    }
}
