package tt.holdem.game;

import java.util.Comparator;

public class Card implements Comparable<Card> {
    private final CardRank rank;
    private final CardSuit suit;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public CardRank getRank() {
        return rank;
    }
    public CardSuit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card other) {
        return Comparator.<Card, CardRank> comparing(Card::getRank)
                .thenComparing(Card::getSuit)
                .compare(this, other);
    }

    @Override
    public String toString() {
        return "" + getRank() + getSuit();
    }
}
