package tt.holdem.test.game.combination;

import org.junit.jupiter.api.Test;
import tt.holdem.game.PokerHand;
import tt.holdem.game.combination.Triple;

import static org.junit.jupiter.api.Assertions.*;

class TripleTest {
    @Test
    void create() {
        var triple = Triple.create(new PokerHand("TD AH TS 5C TH").getCards());
        var notTriple = Triple.create(new PokerHand("AD AH TS 5C 9H").getCards());

        assertTrue(triple.isPresent());
        assertTrue(notTriple.isEmpty());
    }

    @Test
    void compareToSameTypeCombination() {
        var _AdThTdTs5c = Triple.create(new PokerHand("AD TD TS 5C TH").getCards()).get();
        var _AdThTcTs5c = Triple.create(new PokerHand("AD TC TS 5C TH").getCards()).get();
        var _Ad3h3c3s5h = Triple.create(new PokerHand("AD 3C 5H 3S 3H").getCards()).get();
        var _KdThTcTs9c = Triple.create(new PokerHand("KD TC TS 9C TH").getCards()).get();
        var _KdThTcTsQc = Triple.create(new PokerHand("KD TC TS QC TH").getCards()).get();

        assertTrue(_AdThTdTs5c.compareTo(_AdThTcTs5c) == 0); // all cards have same rank
        assertTrue(_AdThTcTs5c.compareTo(_Ad3h3c3s5h) > 0); // triple rank different
        assertTrue(_KdThTcTs9c.compareTo(_AdThTcTs5c) < 0); // first kicker different
        assertTrue(_KdThTcTsQc.compareTo(_KdThTcTs9c) > 0); // second kicker different
    }

    @Test
    void testToString() {
        var _KdThTcTs9c = Triple.create(new PokerHand("KD TC TS 9C TH").getCards()).get();
        assertEquals("Triple of T, kickers K 9", _KdThTcTs9c.toString());
    }
}