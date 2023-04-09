package tt.holdem.game.combination;

import tt.holdem.game.Card;
import tt.holdem.game.CardRank;

import java.util.*;
import java.util.stream.Collectors;

class TwoPairs extends PokerCombination {
    public static final Integer TWO_PAIRS_VALUE = 3;
    public static Optional<PokerCombination> create(List<Card> cards) {
        assertCards(cards);
        if (cards.stream().map(Card::getRank).distinct().count() != 3L) {
            return Optional.empty();
        }

        var twoPairs = new TwoPairs();
        var pairsRank = cards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .sorted(Comparator.reverseOrder())
                .toArray(CardRank[]::new);
        if (pairsRank.length != 2) {
            throw new UnknownError(Arrays.toString(pairsRank));
        }
        twoPairs.highPairRank = pairsRank[0];
        twoPairs.lowPairRank = pairsRank[1];
        twoPairs.kickerRank = cards.stream()
                .map(Card::getRank)
                .filter(rank -> twoPairs.highPairRank != rank && twoPairs.lowPairRank != rank)
                .findFirst()
                .orElseThrow(() -> new UnknownError(cards.toString()));

        return Optional.of(twoPairs);
    }

    private CardRank highPairRank;
    private CardRank lowPairRank;
    private CardRank kickerRank;

    private TwoPairs() {
        super(TWO_PAIRS_VALUE);
    }

    protected int compareToSameTypeCombination(TwoPairs other) {
        return Comparator.<TwoPairs, CardRank> comparing((pair) -> pair.highPairRank)
                .thenComparing((pair) -> pair.lowPairRank)
                .thenComparing((pair) -> pair.kickerRank)
                .compare(this, other);
    }

    @Override
    protected int compareToSameTypeCombination(PokerCombination other) {
        return compareToSameTypeCombination((TwoPairs) other);
    }

    @Override
    public String toString() {
        return String.format("Two pairs of %s and %s, kicker %s", highPairRank, lowPairRank, kickerRank);
    }
}
