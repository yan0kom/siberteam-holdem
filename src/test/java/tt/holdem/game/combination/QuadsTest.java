package tt.holdem.game.combination;

import org.junit.jupiter.api.Test;
import tt.holdem.game.PokerHand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuadsTest {

    @Test
    void create() {
        var quads = Quads.create(new PokerHand("TD TC TS 5C TH").getCards());
        var notQuads = Quads.create(new PokerHand("AD AH TS 5C 9H").getCards());

        assertTrue(quads.isPresent());
        assertTrue(notQuads.isEmpty());
    }

    @Test
    void compareToSameTypeCombination() {
        var _AdThTdTcTs = Quads.create(new PokerHand("AD TD TS TC TH").getCards()).get();
        var _AsThTdTc5s = Quads.create(new PokerHand("AS TC TS TC TH").getCards()).get();
        var _As9h9d9c9s = Quads.create(new PokerHand("AS 9C 9S 9C 9H").getCards()).get();
        var _2s9h9d9c9s = Quads.create(new PokerHand("2S 9C 9S 9C 9H").getCards()).get();

        assertTrue(_AdThTdTcTs.compareTo(_AsThTdTc5s) == 0); // all cards have same rank
        assertTrue(_AsThTdTc5s.compareTo(_As9h9d9c9s) > 0); // quads rank different
        assertTrue(_2s9h9d9c9s.compareTo(_As9h9d9c9s) < 0); // kicker different
    }

    @Test
    void testToString() {
        var _2s9h9d9c9s = Quads.create(new PokerHand("2S 9C 9S 9C 9H").getCards()).get();
        assertEquals("Quads of 9, kicker 2", _2s9h9d9c9s.toString());
    }
}
