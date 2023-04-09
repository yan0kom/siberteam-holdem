package tt.holdem.game;

import tt.holdem.game.combination.PokerCombination;
import tt.holdem.game.combination.PokerCombinationRegistry;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PokerHand implements Comparable<PokerHand> {
    private static final Pattern handPattern = Pattern.compile("^[2-9TJQKA][SCDH]( [2-9TJQKA][SCDH]){4}$");

    private final List<Card> cards;
    private PokerCombination combination;

    /** Creates PokerHand with random cards */
    public PokerHand() {
        var random = new Random();
        var cardRanks = CardRank.values();
        var cardSuits = CardSuit.values();

        List<Card> randomCards = null;
        var repeat = true;
        while (repeat) {
            randomCards = random.ints(5, 0, 13)
                    .mapToObj(rankIdx -> new Card(cardRanks[rankIdx], cardSuits[random.nextInt(4)]))
                    .collect(Collectors.toList());
            if (randomCards.stream().distinct().count() == 5L) {
                repeat = false;
            }
        }
        this.cards = randomCards;
        checkAndSortCards();
    }

    /** Creates PokerHand from list of cards */
    public PokerHand(List<Card> cards) {
        this.cards = cards;
        checkAndSortCards();
    }

    /**
     * Creates PokerHand from string with space-delimited cards.
     * Each of five card presented as symbol of rank and symbol of suit (uppercase).
     * Example: 5S AC JS 2H 7D */
    public PokerHand(String cardsString) {
        if (!handPattern.matcher(cardsString).matches()) {
            throw new IllegalArgumentException(cardsString);
        }

        this.cards = Arrays.stream(cardsString.split(" "))
                .map(rs -> new Card(CardRank.fromSymbol(rs.charAt(0)), CardSuit.fromSymbol(rs.charAt(1))))
                .collect(Collectors.toList());
        checkAndSortCards();
    }

    private void checkAndSortCards() {
        if (cards.stream().distinct().count() != 5L) {
            throw new IllegalArgumentException(cards.toString());
        }
        cards.sort(Comparator.reverseOrder());
    }

    public PokerCombination getCombination() {
        if (combination == null) {
            combination = PokerCombinationRegistry.create(cards);
        }
        return combination;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public int compareTo(PokerHand other) {
        return getCombination().compareTo(other.getCombination());
    }

    @Override
    public String toString() {
        return cards.stream().map(Card::toString).collect(Collectors.joining(" ", "\"", "\""));
    }
}
