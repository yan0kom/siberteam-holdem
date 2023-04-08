package tt.holdem.test.game;

import org.junit.jupiter.api.Test;
import tt.holdem.game.PokerHand;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PokerHandTest {
    @Test
    public void whenInvalidHand_thenThrowException() {
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
    }
}
