package tt.holdem.game.combination;

import tt.holdem.game.Card;
import tt.holdem.game.CardRank;
import tt.holdem.game.CardSuit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

class StraightFlush extends PokerCombination {
    private static final Integer STRAIGHT_FLUSH_VALUE = 9;
    public static Optional<PokerCombination> create(Card[] cards) {
        assertCards(cards);
        if (Arrays.stream(cards).map(Card::getSuit).distinct().count() != 1L) {
            return Optional.empty();
        }

        var straightOpt = Straight.create(cards);
        if (straightOpt.isEmpty()) {
            return Optional.empty();
        }

        var straight = (Straight) straightOpt.get();
        var straightFlush = new StraightFlush();
        straightFlush.rank = straight.getRank();
        straightFlush.suit = cards[0].getSuit();

        return Optional.of(straightFlush);
    }
    static {
        PokerCombinationRegistry.registerCombinationFactoryMethod(STRAIGHT_FLUSH_VALUE, StraightFlush::create);
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
