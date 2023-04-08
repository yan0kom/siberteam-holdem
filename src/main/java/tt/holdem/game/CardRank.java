package tt.holdem.game;

import java.util.Arrays;
import java.util.Comparator;

public enum CardRank {
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JACK('J'),
    QUEEN('Q'),
    KING('K'),
    ACE('A');

    private final Character symbol;

    CardRank(char symbol) {
        this.symbol = symbol;
    }

    private static CardRank[] valuesSortedBySymbol;
    private static Character[] sortedSymbols;
    static {
        valuesSortedBySymbol = values();
        Arrays.sort(valuesSortedBySymbol, Comparator.comparing((other) -> other.symbol));
        sortedSymbols = Arrays.stream(valuesSortedBySymbol).map(CardRank::getSymbol).toArray(Character[]::new);
    }

    public static CardRank fromSymbol(Character symbol) {
        int valueIdx = Arrays.binarySearch(sortedSymbols, symbol);
        if (valueIdx < 0) {
            throw new IllegalArgumentException(String.valueOf(symbol));
        }
        return valuesSortedBySymbol[valueIdx];
    }

    public Character getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol.toString();
    }
}
