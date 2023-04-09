package tt.holdem.game.combination;

import org.junit.jupiter.api.Test;
import tt.holdem.game.PokerHand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StraightTest {

    @Test
    void create() {
        var straight = Straight.create(new PokerHand("KD AH TS JC QH").getCards());
        var notStraight = Straight.create(new PokerHand("AD AH TS 5C 9H").getCards());

        assertTrue(straight.isPresent());
        assertTrue(notStraight.isEmpty());
    }

    @Test
    void compareToSameTypeCombination() {
        var _Td9s8h7c6h = Straight.create(new PokerHand("TD 6H 9S 7C 8H").getCards()).get();
        var _Tc9c8h7c6h = Straight.create(new PokerHand("TC 6H 9C 7C 8H").getCards()).get();
        var _QcJcTh9c8s = Straight.create(new PokerHand("QC 8S JC 9C TH").getCards()).get();
        var _6c5c4h3c2s = Straight.create(new PokerHand("2C 3S 6C 4C 5H").getCards()).get();

        assertTrue(_Td9s8h7c6h.compareToSameTypeCombination(_Tc9c8h7c6h) == 0); // all cards have same rank
        assertTrue(_QcJcTh9c8s.compareToSameTypeCombination(_Tc9c8h7c6h) > 0); // straight rank different
        assertTrue(_6c5c4h3c2s.compareToSameTypeCombination(_QcJcTh9c8s) < 0); // straight rank different
    }

    @Test
    void testToString() {
        var _QcJcTh9c8s = Straight.create(new PokerHand("QC 8S JC 9C TH").getCards()).get();
        assertEquals("Straight to Q", _QcJcTh9c8s.toString());
    }
}
