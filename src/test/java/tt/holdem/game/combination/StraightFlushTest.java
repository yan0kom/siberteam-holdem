package tt.holdem.game.combination;

import org.junit.jupiter.api.Test;
import tt.holdem.game.PokerHand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StraightFlushTest {

    @Test
    void create() {
        var straightFlush = StraightFlush.create(new PokerHand("KD AD TD JD QD").getCards());
        var notStraighFlush = StraightFlush.create(new PokerHand("AD AH TS 5C 9H").getCards());

        assertTrue(straightFlush.isPresent());
        assertTrue(notStraighFlush.isEmpty());
    }

    @Test
    void compareToSameTypeCombination() {
        var _Th9h8h7h6h = StraightFlush.create(new PokerHand("TH 6H 9H 7H 8H").getCards()).get();
        var _Tc9c8c7c6c = StraightFlush.create(new PokerHand("TC 6C 9C 7C 8C").getCards()).get();
        var _JhTh9h8h7h = StraightFlush.create(new PokerHand("TH JH 9H 7H 8H").getCards()).get();
        var _9c8c7c6c5c = StraightFlush.create(new PokerHand("5C 6C 9C 7C 8C").getCards()).get();

        assertTrue(_Th9h8h7h6h.compareToSameTypeCombination(_Tc9c8c7c6c) == 0); // all cards have same rank
        assertTrue(_JhTh9h8h7h.compareToSameTypeCombination(_Tc9c8c7c6c) > 0); // Straight flush rank different
        assertTrue(_9c8c7c6c5c.compareToSameTypeCombination(_Tc9c8c7c6c) < 0); // Straight flush rank different
    }

    @Test
    void testToString() {
        var _JhTh9h8h7h = StraightFlush.create(new PokerHand("TH JH 9H 7H 8H").getCards()).get();
        assertEquals("Straight flush of H to J", _JhTh9h8h7h.toString());
    }
}
