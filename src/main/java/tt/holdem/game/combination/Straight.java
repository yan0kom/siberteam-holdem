package tt.holdem.game.combination;

import tt.holdem.game.Card;
import tt.holdem.game.CardRank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

class Straight extends PokerCombination {
    private static final Integer STRAIGHT_VALUE = 5;
    public static Optional<PokerCombination> create(Card[] cards) {
        assertCards(cards);
        if (Arrays.stream(cards).map(Card::getRank).distinct().count() != 5L) {
            return Optional.empty();
        }

        var straight = new Straight();
        Arrays.stream(cards)
                .map(Card::getRank)
                .sorted(Comparator.reverseOrder())
                .toArray(CardRank[]::new);

        return Optional.of(straight);
    }
    static {
        PokerCombinationRegistry.registerCombinationFactoryMethod(STRAIGHT_VALUE, Straight::create);
    }

    private CardRank rank;

    private Straight() {
        super(STRAIGHT_VALUE);
    }

    protected int compareToSameTypeCombination(Straight other) {
        return Comparator.<Straight, CardRank> comparing((straight) -> straight.rank).compare(this, other);
    }

    @Override
    protected int compareToSameTypeCombination(PokerCombination other) {
        return compareToSameTypeCombination((Straight) other);
    }

    @Override
    public String toString() {
        return String.format("Straight to %s", rank);
    }
}
