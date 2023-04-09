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

    public static void registerCombinationFactoryMethod(Integer value, CombinationFactoryMethod method) {
        factoryMethods.add(new RegistryEntry(value, method));
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
