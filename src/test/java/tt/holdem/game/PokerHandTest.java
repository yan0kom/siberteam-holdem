package tt.holdem.game;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void shouldSortHandsByRank() {
        var hands = new ArrayList<PokerHand>();
        // royal flush
        hands.add(new PokerHand("KS AS TS JS QS"));
        // straight flush
        hands.add(new PokerHand("TH JH 9H 7H 8H"));
        hands.add(new PokerHand("TC 6C 9C 7C 8C"));
        hands.add(new PokerHand("5C 6C 9C 7C 8C"));
        // quads
        hands.add(new PokerHand("AS TC TS TD TH"));
        hands.add(new PokerHand("AS 9C 9S 9D 9H"));
        hands.add(new PokerHand("2S 9C 9S 9D 9H"));
        // full house
        hands.add(new PokerHand("TH 9C 9D TS 9H"));
        hands.add(new PokerHand("QH QC 7D 7S 7H"));
        hands.add(new PokerHand("TH 7C 7D TS 7H"));
        // flush
        hands.add(new PokerHand("QS 5S JS 9S TS"));
        hands.add(new PokerHand("QS 8S 2S 9S TS"));
        hands.add(new PokerHand("QS 8S 2S 7S TS"));
        hands.add(new PokerHand("QS 8S 3S 5S TS"));
        hands.add(new PokerHand("QS 8S 2S 5S TS"));
        hands.add(new PokerHand("TC 4C 9C 7C 8C"));
        // straight
        hands.add(new PokerHand("QC 8S JC 9C TH"));
        hands.add(new PokerHand("TC 6H 9C 7C 8H"));
        hands.add(new PokerHand("2C 3S 6C 4C 5H"));
        // triple
        hands.add(new PokerHand("AD TC TS 5C TH"));
        hands.add(new PokerHand("KD TC TS QC TH"));
        hands.add(new PokerHand("KD TC TS 9C TH"));
        hands.add(new PokerHand("AD 3C 5H 3S 3H"));
        // two pairs
        hands.add(new PokerHand("AS AC TS 5C TH"));
        hands.add(new PokerHand("JS JC TS 5C TH"));
        hands.add(new PokerHand("JS JC 8S KC 8H"));
        hands.add(new PokerHand("JS JC 8S 5C 8H"));
        // pairs
        hands.add(new PokerHand("5S JH KD JS TC"));
        hands.add(new PokerHand("4S JH KD JS TC"));
        hands.add(new PokerHand("5S JH KD JS 7C"));
        hands.add(new PokerHand("5S JH QD JS 7C"));
        hands.add(new PokerHand("5S 7H KD JS 7C"));
        hands.add(new PokerHand("5S 7H KD JS 5C"));
        // high card
        hands.add(new PokerHand("5S 9H AD JS 7C"));
        hands.add(new PokerHand("5S 7H KH JS 6C"));
        hands.add(new PokerHand("3D 7H KD JS 6C"));
        hands.add(new PokerHand("2D 7H KD JS 3C"));
        hands.add(new PokerHand("5D 3H KD JS 6C"));
        hands.add(new PokerHand("5S 6S KD TS 7D"));

        var shuffled = new ArrayList<PokerHand>(hands);
        Collections.shuffle(shuffled);
        Collections.sort(shuffled);
        assertIterableEquals(hands, shuffled);
    }
}
