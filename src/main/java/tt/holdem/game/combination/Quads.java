package tt.holdem.game.combination;

import tt.holdem.game.Card;
import tt.holdem.game.CardRank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class Quads extends PokerCombination {
    private static final Integer QUADS_VALUE = 8;
    public static Optional<PokerCombination> create(Card[] cards) {
        assertCards(cards);
        if (Arrays.stream(cards).map(Card::getRank).distinct().count() != 2L) {
            return Optional.empty();
        }

        // full house or quads
        var quadsRankOpt = Arrays.stream(cards)
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 4)
                .map(Map.Entry::getKey)
                .findFirst();
        if (quadsRankOpt.isEmpty()) { // full house
            return Optional.empty();
        }

        var quads = new Quads();
        quads.quadsRank = quadsRankOpt.get();
        var kickersRank = Arrays.stream(cards)
                .map(Card::getRank)
                .filter(rank -> quads.quadsRank != rank)
                .toArray(CardRank[]::new);
        if (kickersRank.length != 1) {
            throw new UnknownError(Arrays.toString(kickersRank));
        }
        quads.kickerRank = kickersRank[0];

        return Optional.of(quads);
    }
    static {
        PokerCombinationRegistry.registerCombinationFactoryMethod(QUADS_VALUE, Quads::create);
    }

    private CardRank quadsRank;
    private CardRank kickerRank;

    private Quads() {
        super(QUADS_VALUE);
    }

    protected int compareToSameTypeCombination(Quads other) {
        return Comparator.<Quads, CardRank> comparing((quads) -> quads.quadsRank)
                .thenComparing((quads) -> quads.kickerRank)
                .compare(this, other);
    }

    @Override
    protected int compareToSameTypeCombination(PokerCombination other) {
        return compareToSameTypeCombination((Quads) other);
    }

    @Override
    public String toString() {
        return String.format("Quads of %s, kicker %s", quadsRank, kickerRank);
    }
}
