package tt.holdem.game.combination;

import org.junit.jupiter.api.Test;
import tt.holdem.game.PokerHand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TwoPairsTest {
    @Test
    void create() {
        var twoPairs = TwoPairs.create(new PokerHand("AD AH TS 5C TH").getCards());
        var notTwoPairs = TwoPairs.create(new PokerHand("AD AH TS 5C 9H").getCards());

        assertTrue(twoPairs.isPresent());
        assertTrue(notTwoPairs.isEmpty());
    }

    @Test
    void compareToSameTypeCombination() {
        var _AdAhTsTh5c = TwoPairs.create(new PokerHand("AD AH TS 5C TH").getCards()).get();
        var _AsAcTsTh5c = TwoPairs.create(new PokerHand("AS AC TS 5C TH").getCards()).get();
        var _JsJcTsTh5c = TwoPairs.create(new PokerHand("JS JC TS 5C TH").getCards()).get();
        var _JsJc8s8h5c = TwoPairs.create(new PokerHand("JS JC 8S 5C 8H").getCards()).get();
        var _JsJc8s8hKc = TwoPairs.create(new PokerHand("JS JC 8S KC 8H").getCards()).get();

        assertTrue(_AdAhTsTh5c.compareToSameTypeCombination(_AsAcTsTh5c) == 0); // all cards have same rank
        assertTrue(_AdAhTsTh5c.compareToSameTypeCombination(_JsJcTsTh5c) > 0); // high pairs different
        assertTrue(_JsJc8s8h5c.compareToSameTypeCombination(_JsJcTsTh5c) < 0); // low pairs different
        assertTrue(_JsJc8s8hKc.compareToSameTypeCombination(_JsJc8s8h5c) > 0); // kicker different
    }

    @Test
    void testToString() {
        var _JsJcTsTh5c = TwoPairs.create(new PokerHand("JS JC TS 5C TH").getCards()).get();
        assertEquals("Two pairs of J and T, kicker 5", _JsJcTsTh5c.toString());
    }
}