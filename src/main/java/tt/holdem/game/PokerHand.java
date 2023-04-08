package tt.holdem.game;

import tt.holdem.game.combination.HighCard;
import tt.holdem.game.combination.Pair;
import tt.holdem.game.combination.PokerCombination;
import tt.holdem.game.combination.TwoPairs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

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
    }

    public PokerCombination getCombination() {
        if (combination == null) {
            combination = TwoPairs.create(cards).orElseGet(
                    () -> Pair.create(cards).orElseGet(
                    () -> HighCard.create(cards)
                            .orElseThrow(UnknownError::new)));
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
        return Arrays.toString(cards);
    }
}
