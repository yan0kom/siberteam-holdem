package tt.holdem;

import tt.holdem.game.PokerHand;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hold 'em");
        System.out.println();

        var list = Stream.generate(PokerHand::new).limit(100).collect(Collectors.toList());
        Collections.sort(list);
        list.stream().forEach(hand -> System.out.println("" + hand + " " + hand.getCombination()));
    }
}