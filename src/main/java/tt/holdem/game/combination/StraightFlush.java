package tt.holdem.game.combination;

import tt.holdem.game.Card;
import tt.holdem.game.CardRank;
import tt.holdem.game.CardSuit;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class StraightFlush extends PokerCombination {
    public static final Integer STRAIGHT_FLUSH_VALUE = 9;
    public static Optional<PokerCombination> create(List<Card> cards) {
        assertCards(cards);
        if (cards.stream().map(Card::getSuit).distinct().count() != 1L) {
            return Optional.empty();
        }

        var straightOpt = Straight.create(cards);
        if (straightOpt.isEmpty()) {
            return Optional.empty();
        }

        var straight = (Straight) straightOpt.get();
        var straightFlush = new StraightFlush();
        straightFlush.rank = straight.getRank();
        straightFlush.suit = cards.get(0).getSuit();

        return Optional.of(straightFlush);
    }

    private CardRank rank;
    private CardSuit suit;

    private StraightFlush() {
        super(STRAIGHT_FLUSH_VALUE);
    }

    protected int compareToSameTypeCombination(StraightFlush other) {
        return Comparator.<StraightFlush, CardRank> comparing((straightFlush) -> straightFlush.rank).compare(this, other);
    }

    @Override
    protected int compareToSameTypeCombination(PokerCombination other) {
        return compareToSameTypeCombination((StraightFlush) other);
    }

    @Override
    public String toString() {
        return String.format("Straight flush of %s to %s", suit, rank);
    }

    public CardRank getRank() {
        return rank;
    }

    public CardSuit getSuit() {
        return suit;
    }
}
