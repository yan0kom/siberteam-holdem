package tt.holdem.game.combination;

import org.junit.jupiter.api.Test;
import tt.holdem.game.PokerHand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FlushTest {

    @Test
    void create() {
        var flush = Flush.create(new PokerHand("KD AD TD JD QD").getCards());
        var notFlush = Flush.create(new PokerHand("AD AH TS 5C 9H").getCards());

        assertTrue(flush.isPresent());
        assertTrue(notFlush.isEmpty());
    }

    @Test
    void compareToSameTypeCombination() {
        var _Td9d8d7d6d = Flush.create(new PokerHand("TD 6D 9D 7D 8D").getCards()).get();
        var _Tc9c8c7c6c = Flush.create(new PokerHand("TC 6C 9C 7C 8C").getCards()).get();
        var _QsJsTs9s8s = Flush.create(new PokerHand("QS 8S JS 9S TS").getCards()).get();
        var _QsTs9s8s2s = Flush.create(new PokerHand("QS 8S 2S 9S TS").getCards()).get();
        var _QsTs8s7s2s = Flush.create(new PokerHand("QS 8S 2S 7S TS").getCards()).get();
        var _QsTs8s5s2s = Flush.create(new PokerHand("QS 8S 2S 5S TS").getCards()).get();
        var _QsTs8s5s3s = Flush.create(new PokerHand("QS 8S 3S 5S TS").getCards()).get();

        assertTrue(_Td9d8d7d6d.compareTo(_Tc9c8c7c6c) == 0); // all cards have same rank
        assertTrue(_QsJsTs9s8s.compareTo(_Tc9c8c7c6c) > 0); // first card rank different
        assertTrue(_QsTs9s8s2s.compareTo(_QsJsTs9s8s) < 0); // second card rank different
        assertTrue(_QsTs9s8s2s.compareTo(_QsTs8s7s2s) > 0); // third card rank different
        assertTrue(_QsTs8s5s2s.compareTo(_QsTs8s7s2s) < 0); // fourth card rank different
        assertTrue(_QsTs8s5s3s.compareTo(_QsTs8s5s2s) > 0); // fifth card rank different
    }

    @Test
    void testToString() {
        var _Tc9c8c7c6c = Flush.create(new PokerHand("TC 6C 9C 7C 8C").getCards()).get();
        assertEquals("Flush of C, T 9 8 7 6", _Tc9c8c7c6c.toString());
    }
}