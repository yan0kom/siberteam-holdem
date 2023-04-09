package tt.holdem.game.combination;

import tt.holdem.game.Card;
import tt.holdem.game.CardRank;

import java.util.*;
import java.util.stream.Collectors;

class Triple extends PokerCombination {
    public static final Integer TRIPLE_VALUE = 4;
    public static Optional<PokerCombination> create(List<Card> cards) {
        assertCards(cards);
        if (cards.stream().map(Card::getRank).distinct().count() != 3L) {
            return Optional.empty();
        }

        // two pairs or triple
        var tripleRankOpt = cards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 3)
                .map(Map.Entry::getKey)
                .findFirst();
        if (tripleRankOpt.isEmpty()) { // two pairs
            return Optional.empty();
        }

        var triple = new Triple();
        triple.tripleRank = tripleRankOpt.get();
        triple.kickersRank = cards.stream()
                .map(Card::getRank)
                .filter(rank -> triple.tripleRank != rank)
                .sorted(Comparator.reverseOrder())
                .toArray(CardRank[]::new);
        if (triple.kickersRank.length != 2) {
            throw new UnknownError(Arrays.toString(triple.kickersRank));
        }

        return Optional.of(triple);
    }

    private CardRank tripleRank;
    private CardRank[] kickersRank;

    private Triple() {
        super(TRIPLE_VALUE);
    }

    protected int compareToSameTypeCombination(Triple other) {
        return Comparator.<Triple, CardRank> comparing((triple) -> triple.tripleRank)
                .thenComparing((triple) -> triple.kickersRank[0])
                .thenComparing((triple) -> triple.kickersRank[1])
                .compare(this, other);
    }

    @Override
    protected int compareToSameTypeCombination(PokerCombination other) {
        return compareToSameTypeCombination((Triple) other);
    }

    @Override
    public String toString() {
        return String.format("Triple of %s, kickers %s %s", tripleRank, kickersRank[0], kickersRank[1]);
    }
}
