package tt.holdem.game.combination;

import tt.holdem.game.Card;
import tt.holdem.game.CardRank;
import tt.holdem.game.CardSuit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

class Flush extends PokerCombination {
    private static final Integer FLUSH_VALUE = 6;
    public static Optional<PokerCombination> create(Card[] cards) {
        assertCards(cards);
        if (Arrays.stream(cards).map(Card::getSuit).distinct().count() != 1L) {
            return Optional.empty();
        }

        var flush = new Flush();
        flush.suit = cards[0].getSuit();
        flush.cardsRank = Arrays.stream(cards)
                .map(Card::getRank)
                .sorted(Comparator.reverseOrder())
                .toArray(CardRank[]::new);
        if (flush.cardsRank.length != 5) {
            throw new UnknownError(Arrays.toString(flush.cardsRank));
        }

        return Optional.of(flush);
    }
    static {
        PokerCombinationRegistry.registerCombinationFactoryMethod(FLUSH_VALUE, Flush::create);
    }

    private CardSuit suit;
    private CardRank[] cardsRank;

    private Flush() {
        super(FLUSH_VALUE);
    }

    protected int compareToSameTypeCombination(Flush other) {
        return Comparator.<Flush, CardRank> comparing((flush) -> flush.cardsRank[0])
                .thenComparing((flush) -> flush.cardsRank[1])
                .thenComparing((flush) -> flush.cardsRank[2])
                .thenComparing((flush) -> flush.cardsRank[3])
                .thenComparing((flush) -> flush.cardsRank[4])
                .compare(this, other);
    }

    @Override
    protected int compareToSameTypeCombination(PokerCombination other) {
        return compareToSameTypeCombination((Flush) other);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("Flush of ");
        sb.append(suit.toString());
        sb.append(",");
        Arrays.stream(cardsRank).forEach((card) -> {
            sb.append(" ");
            sb.append(card);
        });
        return sb.toString();
    }
}
