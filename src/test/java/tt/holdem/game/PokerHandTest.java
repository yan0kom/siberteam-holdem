package tt.holdem.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PokerHandTest {
    @Test
    void whenInvalidHand_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PokerHand("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new PokerHand("2D 3H TH QS");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new PokerHand("2D 3H TH QS GH");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new PokerHand(" 2D 3H TH QS 2C");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new PokerHand("2D3HTHQS2C");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new PokerHand("2D 3H TH QS 2D");
        });
    }

    @Test
    void shouldDeterminePokerCombination() {
        assertEquals(1, new PokerHand("KS TD 6S 5C AH").getCombination().getCombinationValue());
        assertEquals(2, new PokerHand("QH 7D 9S 7S 3C").getCombination().getCombinationValue());
        assertEquals(3, new PokerHand("7S KD KH 2C 7C").getCombination().getCombinationValue());
        assertEquals(4, new PokerHand("4D 8D 4S KS 4C").getCombination().getCombinationValue());
        assertEquals(5, new PokerHand("2S 6D 4H 5C 3C").getCombination().getCombinationValue());
        assertEquals(6, new PokerHand("QD 9D 5D 2D TD").getCombination().getCombinationValue());
        assertEquals(7, new PokerHand("JS 8S JH 8D 8C").getCombination().getCombinationValue());
        assertEquals(8, new PokerHand("3H 3D KS 3S 3C").getCombination().getCombinationValue());
        assertEquals(9, new PokerHand("7C TC 9C 8C 6C").getCombination().getCombinationValue());
        assertEquals(10, new PokerHand("KS TS QS JS AS").getCombination().getCombinationValue());
    }
}
