package tt.holdem.game.combination;

import tt.holdem.game.Card;
import tt.holdem.game.CardRank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

class Pair extends PokerCombination {
    public static final Integer PAIR_VALUE = 2;
    public static Optional<PokerCombination> create(Card[] cards) {
        assertCards(cards);
        if (Arrays.stream(cards).map(Card::getRank).distinct().count() != 4L) {
            return Optional.empty();
        }

        var pair = new Pair();
        pair.pairRank = Arrays.stream(cards)
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .findFirst()
                .orElseThrow()
                .getKey();
        pair.kickersRank = Arrays.stream(cards)
                .filter(card -> pair.pairRank != card.getRank())
                .map(Card::getRank)
                .sorted(Comparator.reverseOrder())
                .toArray(CardRank[]::new);
        if (pair.kickersRank.length != 3) {
            throw new UnknownError(Arrays.toString(pair.kickersRank));
        }

        return Optional.of(pair);
    }

    private CardRank pairRank;
    private CardRank[] kickersRank;

    private Pair() {
        super(PAIR_VALUE);
    }

    protected int compareToSameTypeCombination(Pair other) {
        return Comparator.<Pair, CardRank> comparing((pair) -> pair.pairRank)
                .thenComparing((pair) -> pair.kickersRank[0])
                .thenComparing((pair) -> pair.kickersRank[1])
                .thenComparing((pair) -> pair.kickersRank[2])
                .compare(this, other);
    }

    @Override
    protected int compareToSameTypeCombination(PokerCombination other) {
        return compareToSameTypeCombination((Pair) other);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("Pair of ");
        sb.append(pairRank);
        sb.append(", kickers");
        Arrays.stream(kickersRank).forEach((rank) -> {
            sb.append(" ");
            sb.append(rank);
        });
        return sb.toString();
    }
}
