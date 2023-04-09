package tt.holdem.game.combination;

import tt.holdem.game.Card;
import tt.holdem.game.CardRank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

class HighCard extends PokerCombination {
    public static final Integer HIGH_CARD_VALUE = 1;
    public static Optional<PokerCombination> create(Card[] cards) {
        assertCards(cards);
        if (Arrays.stream(cards).map(Card::getRank).distinct().count() != 5L) {
            return Optional.empty();
        }

        var pair = new HighCard();
        pair.cardsRank = Arrays.stream(cards)
                .map(Card::getRank)
                .sorted(Comparator.reverseOrder())
                .toArray(CardRank[]::new);
        if (pair.cardsRank.length != 5) {
            throw new UnknownError(Arrays.toString(pair.cardsRank));
        }

        return Optional.of(pair);
    }

    private CardRank[] cardsRank;

    private HighCard() {
        super(HIGH_CARD_VALUE);
    }

    protected int compareToSameTypeCombination(HighCard other) {
        return Comparator.<HighCard, CardRank> comparing((pair) -> pair.cardsRank[0])
                .thenComparing((pair) -> pair.cardsRank[1])
                .thenComparing((pair) -> pair.cardsRank[2])
                .thenComparing((pair) -> pair.cardsRank[3])
                .thenComparing((pair) -> pair.cardsRank[4])
                .compare(this, other);
    }

    @Override
    protected int compareToSameTypeCombination(PokerCombination other) {
        return compareToSameTypeCombination((HighCard) other);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("High card");
        Arrays.stream(cardsRank).forEach((rank) -> {
            sb.append(" ");
            sb.append(rank);
        });
        return sb.toString();
    }
}
