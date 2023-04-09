package tt.holdem.game;

import tt.holdem.game.combination.PokerCombination;
import tt.holdem.game.combination.PokerCombinationRegistry;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PokerHand implements Comparable<PokerHand> {
    private static final Pattern handPattern = Pattern.compile("^[2-9TJQKA][SCDH]( [2-9TJQKA][SCDH]){4}$");

    private Card[] cards;
    private PokerCombination combination;

    public PokerHand(String cardsString) {
        if (!handPattern.matcher(cardsString).matches()) {
            throw new IllegalArgumentException(cardsString);
        }

        cards = Arrays.stream(cardsString.split(" "))
                .map(rs -> new Card(CardRank.fromSymbol(rs.charAt(0)), CardSuit.fromSymbol(rs.charAt(1))))
                .sorted(Comparator.reverseOrder())
                .toArray(Card[]::new);

        if (Arrays.stream(cards).distinct().count() != 5L) {
            throw new IllegalArgumentException(cardsString);
        }
    }

    public PokerCombination getCombination() {
        if (combination == null) {
            combination = PokerCombinationRegistry.create(cards);
        }
        return combination;
    }

    public Card[] getCards() {
        return Arrays.copyOf(cards, cards.length);
    }

    @Override
    public int compareTo(PokerHand other) {
        return getCombination().compareTo(other.getCombination());
    }

    @Override
    public String toString() {
        return Arrays.stream(cards).map(Card::toString).collect(Collectors.joining(" ", "\"", "\""));
    }
}
