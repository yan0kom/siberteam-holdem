package tt.holdem.game.game.combination;

import org.junit.jupiter.api.Test;
import tt.holdem.game.PokerHand;
import tt.holdem.game.combination.HighCard;

import static org.junit.jupiter.api.Assertions.*;

class HighCardTest {
    @Test
    void create() {
        var highCard = HighCard.create(new PokerHand("5S 7H KD JS 6C").getCards());
        var notHighCard = HighCard.create(new PokerHand("5S 7H KD JS 5C").getCards());

        assertTrue(highCard.isPresent());
        assertTrue(notHighCard.isEmpty());
    }

    @Test
    void compareToSameTypeCombination() {
        var _KdJs7h6c5d = HighCard.create(new PokerHand("5D 7H KD JS 6C").getCards()).get();
        var _KhJs7h6c5s = HighCard.create(new PokerHand("5S 7H KH JS 6C").getCards()).get();
        var _AdJs9h7c5s = HighCard.create(new PokerHand("5S 9H AD JS 7C").getCards()).get();
        var _KdTs7d6c5s = HighCard.create(new PokerHand("5S 6S KD TS 7D").getCards()).get();
        var _KdJs6c5d3h = HighCard.create(new PokerHand("5D 3H KD JS 6C").getCards()).get();
        var _KdJs7h3c2d = HighCard.create(new PokerHand("2D 7H KD JS 3C").getCards()).get();
        var _KdJs7h6c3d = HighCard.create(new PokerHand("3D 7H KD JS 6C").getCards()).get();

        assertTrue(_KdJs7h6c5d.compareTo(_KhJs7h6c5s) == 0); // all cards have same rank
        assertTrue(_AdJs9h7c5s.compareTo(_KdJs7h6c5d) > 0); // first card different
        assertTrue(_KdTs7d6c5s.compareTo(_KdJs7h6c5d) < 0); // second card different
        assertTrue(_KdJs7h6c5d.compareTo(_KdJs6c5d3h) > 0); // third card different
        assertTrue(_KdJs7h3c2d.compareTo(_KdJs7h6c5d) < 0); // fourth card different
        assertTrue(_KdJs7h6c5d.compareTo(_KdJs7h6c3d) > 0); // fifth card different
    }

    @Test
    void testToString() {
        var _KdTs7d6c5s = HighCard.create(new PokerHand("5S 6S KD TS 7D").getCards()).get();
        assertEquals("High card K T 7 6 5", _KdTs7d6c5s.toString());
    }
}