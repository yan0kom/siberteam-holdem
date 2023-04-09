package tt.holdem.game;

import java.util.Comparator;
import java.util.Objects;

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
        return Comparator.comparing(Card::getRank)
                .thenComparing(Card::getSuit)
                .compare(this, other);
    }

    @Override
    public String toString() {
        return "" + getRank() + getSuit();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}
