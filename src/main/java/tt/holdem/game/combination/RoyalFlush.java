package tt.holdem.game.combination;

import tt.holdem.game.Card;
import tt.holdem.game.CardRank;
import tt.holdem.game.CardSuit;

import java.util.Arrays;
import java.util.Optional;

class RoyalFlush extends PokerCombination {
    public static final Integer ROYAL_FLUSH_VALUE = 10;
    public static Optional<PokerCombination> create(Card[] cards) {
        assertCards(cards);
        if (Arrays.stream(cards).map(Card::getSuit).distinct().count() != 1L) {
            return Optional.empty();
        }

        var straightFlushOpt = StraightFlush.create(cards);
        if (straightFlushOpt.isEmpty()) {
            return Optional.empty();
        }

        var straightFlush = (StraightFlush) straightFlushOpt.get();
        if (straightFlush.getRank() != CardRank.ACE) {
            return Optional.empty();
        }

        var royalFlush = new RoyalFlush();
        royalFlush.suit = straightFlush.getSuit();

        return Optional.of(royalFlush);
    }

    private CardSuit suit;

    private RoyalFlush() {
        super(ROYAL_FLUSH_VALUE);
    }

    protected int compareToSameTypeCombination(RoyalFlush other) {
        return 0;
    }

    @Override
    protected int compareToSameTypeCombination(PokerCombination other) {
        return compareToSameTypeCombination((RoyalFlush) other);
    }

    @Override
    public String toString() {
        return String.format("Royal flush of %s", suit);
    }
}
