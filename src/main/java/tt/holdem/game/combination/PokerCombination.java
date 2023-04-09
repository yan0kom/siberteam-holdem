package tt.holdem.game.combination;

import tt.holdem.game.Card;

import java.util.Arrays;

public abstract class PokerCombination implements Comparable<PokerCombination> {
    protected final int combinationValue;

    protected PokerCombination(int combinationValue) {
        this.combinationValue = combinationValue;
    }

    @Override
    public int compareTo(PokerCombination other) {
        Integer valueCompareResult = Integer.compare(this.combinationValue, other.combinationValue);
        // if same combination
        if (valueCompareResult == 0) {
            return compareToSameTypeCombination(other);
        }
        return valueCompareResult;
    }

    public int getCombinationValue() {
        return combinationValue;
    }

    protected abstract int compareToSameTypeCombination(PokerCombination other);

    protected static void assertCards(Card[] cards) {
        if (cards.length != 5) {
            throw new IllegalArgumentException(Arrays.toString(cards));
        }
    }
}
