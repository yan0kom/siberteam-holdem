package tt.holdem.game.combination;

import org.junit.jupiter.api.Test;
import tt.holdem.game.PokerHand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FullHouseTest {

    @Test
    void create() {
        var fullHouse = FullHouse.create(new PokerHand("KD KH TD TH KS").getCards());
        var notFullHouse = FullHouse.create(new PokerHand("AD AH TS 5C 9H").getCards());

        assertTrue(fullHouse.isPresent());
        assertTrue(notFullHouse.isEmpty());
    }

    @Test
    void compareToSameTypeCombination() {
        var _TdTs9d9c9s = FullHouse.create(new PokerHand("TD 9S 9D TS 9C").getCards()).get();
        var _ThTs9h9d9c = FullHouse.create(new PokerHand("TH 9C 9D TS 9H").getCards()).get();
        var _ThTs7h7d7c = FullHouse.create(new PokerHand("TH 7C 7D TS 7H").getCards()).get();
        var _QhQs7h7d7s = FullHouse.create(new PokerHand("QH QC 7D 7S 7H").getCards()).get();

        assertTrue(_TdTs9d9c9s.compareTo(_ThTs9h9d9c) == 0); // all cards have same rank
        assertTrue(_ThTs9h9d9c.compareTo(_ThTs7h7d7c) > 0); // triple rank different
        assertTrue(_ThTs7h7d7c.compareTo(_QhQs7h7d7s) < 0); // pair rank different
    }

    @Test
    void testToString() {
        var _QhQs7h7d7c = FullHouse.create(new PokerHand("7C QH QC 7D 7H").getCards()).get();
        assertEquals("Full house of triple 7 and pair Q", _QhQs7h7d7c.toString());
    }
}