package tt.holdem.game.game;

import org.junit.jupiter.api.Test;
import tt.holdem.game.CardSuit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CardSuitTest {
    @Test
    void fromSymbol() {
        assertEquals(CardSuit.SPADES, CardSuit.fromSymbol('S'));
        assertEquals(CardSuit.CLUBS, CardSuit.fromSymbol('C'));
        assertEquals(CardSuit.DIAMONDS, CardSuit.fromSymbol('D'));
        assertEquals(CardSuit.HEARTS, CardSuit.fromSymbol('H'));

        assertThrows(IllegalArgumentException.class, () -> CardSuit.fromSymbol('X'));
    }

    @Test
    void testToString() {
        assertEquals(CardSuit.SPADES.toString(), "S");
        assertEquals(CardSuit.CLUBS.toString(), "C");
        assertEquals(CardSuit.DIAMONDS.toString(), "D");
        assertEquals(CardSuit.HEARTS.toString(), "H");
    }
}