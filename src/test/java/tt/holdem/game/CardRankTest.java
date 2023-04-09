package tt.holdem.game.game;

import org.junit.jupiter.api.Test;
import tt.holdem.game.CardRank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CardRankTest {
    @Test
    void fromSymbol() {
        var symbols = new Character[]{'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
        for (int i = 0; i < symbols.length; ++i) {
            assertEquals(i, CardRank.fromSymbol(symbols[i]).ordinal());
        }

        assertThrows(IllegalArgumentException.class, () -> CardRank.fromSymbol('X'));
    }

    @Test
    void getSymbol() {
        var symbols = new Character[]{'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
        for (var cr : CardRank.values()) {
            assertEquals(symbols[cr.ordinal()], cr.getSymbol());
        }
    }

    @Test
    void testToString() {
        for (var cr : CardRank.values()) {
            assertEquals(cr.getSymbol().toString(), cr.toString());
        }
    }
}