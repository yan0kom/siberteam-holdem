package tt.holdem.game.game.combination;

import org.junit.jupiter.api.Test;
import tt.holdem.game.PokerHand;
import tt.holdem.game.combination.Pair;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PairTest {
    @Test
    void create() {
        var pair = Pair.create(new PokerHand("5S 7H KD JS 5C").getCards());
        var notPair = Pair.create(new PokerHand("5S 7H KD JS 6C").getCards());

        assertTrue(pair.isPresent());
        assertTrue(notPair.isEmpty());
    }

    @Test
    void compareToSameTypeCombination() {
        var _KdJs7h5c5h = Pair.create(new PokerHand("5S 7H KD JS 5C").getCards()).get();
        var _KdJs7c7h5s = Pair.create(new PokerHand("5S 7H KD JS 7C").getCards()).get();
        var _KdJs7d7s5s = Pair.create(new PokerHand("5S 7S KD JS 7D").getCards()).get();
        var _QdJsJh7c5s = Pair.create(new PokerHand("5S JH QD JS 7C").getCards()).get();
        var _KdJsJh7c5s = Pair.create(new PokerHand("5S JH KD JS 7C").getCards()).get();
        var _KdJsJhTc5s = Pair.create(new PokerHand("5S JH KD JS TC").getCards()).get();
        var _KdJsJhTc4s = Pair.create(new PokerHand("4S JH KD JS TC").getCards()).get();

        assertTrue(_KdJs7h5c5h.compareTo(_KdJs7c7h5s) < 0); // pair of 5 < pair of 7
        assertTrue(_KdJs7d7s5s.compareTo(_KdJs7h5c5h) > 0); // pair of 7 > pair of 5
        assertTrue(_KdJs7c7h5s.compareTo(_KdJs7d7s5s) == 0); // all cards have same rank
        assertTrue(_QdJsJh7c5s.compareTo(_KdJsJh7c5s) < 0); // both pair of J but first kicker different
        assertTrue(_KdJsJhTc5s.compareTo(_KdJsJh7c5s) > 0); // both pair of J but second kicker different
        assertTrue(_KdJsJhTc5s.compareTo(_KdJsJhTc4s) > 0); // both pair of J but third kicker different
    }

    @Test
    void testToString() {
        var _KdJsJhTc5s = Pair.create(new PokerHand("5S JH KD JS TC").getCards()).get();
        assertEquals("Pair of J, kickers K T 5", _KdJsJhTc5s.toString());
    }
}