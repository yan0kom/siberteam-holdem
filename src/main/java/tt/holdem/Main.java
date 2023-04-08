package tt.holdem;

import tt.holdem.game.PokerHand;

public class Main {
    public static void main(String[] args) {
        System.out.println("Holdem");

        var hand1 = new PokerHand("5S JH KD JS TC");
        System.out.println("" + hand1 + " " + hand1.getCombination());

        var hand2 = new PokerHand("5S JH KD 3S TC");
        System.out.println("" + hand2 + " " + hand2.getCombination());
    }
}