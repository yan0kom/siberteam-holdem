package tt.holdem.game.combination;

import tt.holdem.game.Card;
import tt.holdem.game.CardRank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TwoPairs extends PokerCombination {
    private CardRank highPairRank;
    private CardRank lowPairRank;
    private CardRank kickerRank;

    private TwoPairs() {
        super(3);
    }

    public static Optional<PokerCombination> create(Card[] cards) {
        assertCards(cards);
        if (Arrays.stream(cards).map(Card::getRank).distinct().count() != 3L) {
            return Optional.empty();
        }

        var twoPairs = new TwoPairs();
        var pairsRank = Arrays.stream(cards)
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
        twoPairs.kickerRank = Arrays.stream(cards)
                .map(Card::getRank)
                .filter(rank -> twoPairs.highPairRank != rank && twoPairs.lowPairRank != rank)
                .findFirst()
                .orElseThrow(() -> new UnknownError(Arrays.toString(cards)));

        return Optional.of(twoPairs);
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
        var sb = new StringBuilder("Two pairs of ");
        sb.append(highPairRank);
        sb.append(" and ");
        sb.append(lowPairRank);
        sb.append(", kicker ");
        sb.append(kickerRank);
        return sb.toString();
    }
}
