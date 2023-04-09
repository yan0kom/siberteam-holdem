package tt.holdem.game.combination;

import org.junit.jupiter.api.Test;
import tt.holdem.game.PokerHand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoyalFlushTest {

    @Test
    void create() {
        var royalFlush = RoyalFlush.create(new PokerHand("KD AD TD JD QD").getCards());
        var notRoyalFlush = RoyalFlush.create(new PokerHand("KD 9D TD JD QD").getCards());

        assertTrue(royalFlush.isPresent());
        assertTrue(notRoyalFlush.isEmpty());
    }

    @Test
    void compareToSameTypeCombination() {
        var _AdKdQdJdTd = RoyalFlush.create(new PokerHand("KD AD TD JD QD").getCards()).get();
        var _AsKsQsJsTs = RoyalFlush.create(new PokerHand("KS AS TS JS QS").getCards()).get();

        assertTrue(_AdKdQdJdTd.compareToSameTypeCombination(_AsKsQsJsTs) == 0); // royal flush have same rank
    }

    @Test
    void testToString() {
        var _AsKsQsJsTs = RoyalFlush.create(new PokerHand("KS AS TS JS QS").getCards()).get();
        assertEquals("Royal flush of S", _AsKsQsJsTs.toString());
    }
}