package tt.holdem.game.combination;

import tt.holdem.game.Card;
import tt.holdem.game.CardRank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class FullHouse extends PokerCombination {
    private static final Integer FULL_HOUSE_VALUE = 7;
    public static Optional<PokerCombination> create(Card[] cards) {
        assertCards(cards);
        if (Arrays.stream(cards).map(Card::getRank).distinct().count() != 2L) {
            return Optional.empty();
        }

        var rankMap = Arrays.stream(cards)
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        var tripleRankOpt = rankMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 3)
                .map(Map.Entry::getKey)
                .findFirst();
        if (tripleRankOpt.isEmpty()) {
            return Optional.empty();
        }
        var fullHouse = new FullHouse();
        fullHouse.tripleRank = tripleRankOpt.get();
        fullHouse.pairRank = rankMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .findFirst().orElseThrow(() -> new UnknownError(Arrays.toString(cards)));

        return Optional.of(fullHouse);
    }
    static {
        PokerCombinationRegistry.registerCombinationFactoryMethod(FULL_HOUSE_VALUE, FullHouse::create);
    }

    private CardRank tripleRank;
    private CardRank pairRank;

    private FullHouse() {
        super(FULL_HOUSE_VALUE);
    }

    protected int compareToSameTypeCombination(FullHouse other) {
        return Comparator.<FullHouse, CardRank> comparing((fullHouse) -> fullHouse.tripleRank)
                .thenComparing((fullHouse) -> fullHouse.pairRank)
                .compare(this, other);
    }

    @Override
    protected int compareToSameTypeCombination(PokerCombination other) {
        return compareToSameTypeCombination((FullHouse) other);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("Full house of triple ");
        sb.append(tripleRank);
        sb.append(" and pair ");
        sb.append(pairRank);
        return sb.toString();
    }
}
