package tt.holdem.game.combination;

import tt.holdem.game.Card;

import java.util.List;

public abstract class PokerCombination implements Comparable<PokerCombination> {
    protected final int combinationValue;

    protected PokerCombination(int combinationValue) {
        this.combinationValue = combinationValue;
    }

    /** Reverse comparing (descendant order) */
    @Override
    public int compareTo(PokerCombination other) {
        int valueCompareResult = Integer.compare(this.combinationValue, other.combinationValue);
        // if same combination
        if (valueCompareResult == 0) {
            return compareToSameTypeCombination(other) * -1;
        }
        return valueCompareResult * -1;
    }

    public int getCombinationValue() {
        return combinationValue;
    }

    protected abstract int compareToSameTypeCombination(PokerCombination other);

    protected static void assertCards(List<Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException(cards.toString());
        }
    }
}
