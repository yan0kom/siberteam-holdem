package tt.holdem.game.combination;

import tt.holdem.game.Card;

import java.util.*;

/**
 * Poker combination registry.
 * Registry contains PokerCombination implementations sorted by combination value in reverse order
 */
public class PokerCombinationRegistry {
    @FunctionalInterface
    interface CombinationFactoryMethod {
        Optional<PokerCombination> create(Card[] cards);
    }

    private static class RegistryEntry {
        private final Integer combinationValue;
        private final CombinationFactoryMethod combinationFactoryMethod;

        public RegistryEntry(Integer combinationValue, CombinationFactoryMethod combinationFactoryMethod) {
            this.combinationValue = combinationValue;
            this.combinationFactoryMethod = combinationFactoryMethod;
        }
    }

    private static final List<RegistryEntry> factoryMethods = new ArrayList<>();
    static {
        factoryMethods.add(new RegistryEntry(RoyalFlush.ROYAL_FLUSH_VALUE, RoyalFlush::create));
        factoryMethods.add(new RegistryEntry(StraightFlush.STRAIGHT_FLUSH_VALUE, StraightFlush::create));
        factoryMethods.add(new RegistryEntry(Quads.QUADS_VALUE, Quads::create));
        factoryMethods.add(new RegistryEntry(FullHouse.FULL_HOUSE_VALUE, FullHouse::create));
        factoryMethods.add(new RegistryEntry(Flush.FLUSH_VALUE, Flush::create));
        factoryMethods.add(new RegistryEntry(Straight.STRAIGHT_VALUE, Straight::create));
        factoryMethods.add(new RegistryEntry(Triple.TRIPLE_VALUE, Triple::create));
        factoryMethods.add(new RegistryEntry(TwoPairs.TWO_PAIRS_VALUE, TwoPairs::create));
        factoryMethods.add(new RegistryEntry(Pair.PAIR_VALUE, Pair::create));
        factoryMethods.add(new RegistryEntry(HighCard.HIGH_CARD_VALUE, HighCard::create));
        factoryMethods.sort(
                Comparator.<RegistryEntry, Integer> comparing(entry -> entry.combinationValue).reversed());
    }

    /**
     * Creates the highest value combination possible from given cards
     * @param cards Cards of poker hand
     * @return PokerCombination implementation
     */
    public static PokerCombination create(Card[] cards) {
        for (var entry : factoryMethods) {
            var pokerCombination = entry.combinationFactoryMethod.create(cards);
            if (pokerCombination.isPresent()) {
                return pokerCombination.get();
            }
        }
        throw new NoSuchElementException("Suitable combination not found");
    };
}
