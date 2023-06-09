package tt.holdem.game.combination;

import tt.holdem.game.Card;
import tt.holdem.game.CardRank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class Straight extends PokerCombination {
    public static final Integer STRAIGHT_VALUE = 5;
    public static Optional<PokerCombination> create(List<Card> cards) {
        assertCards(cards);
        if (cards.stream().map(Card::getRank).distinct().count() != 5L) {
            return Optional.empty();
        }

        var straight = new Straight();
        var cardRanks = cards.stream()
                .map(Card::getRank)
                .sorted(Comparator.reverseOrder())
                .toArray(CardRank[]::new);
        if (cardRanks.length != 5) {
            throw new UnknownError(Arrays.toString(cardRanks));
        }
        for (int i = 0; i < 4; ++i) {
            if (cardRanks[i].ordinal() - cardRanks[i + 1].ordinal() !=  1) { // not straight
                return Optional.empty();
            }
        }
        straight.rank = cardRanks[0];

        return Optional.of(straight);
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

    public CardRank getRank() {
        return rank;
    }
}
