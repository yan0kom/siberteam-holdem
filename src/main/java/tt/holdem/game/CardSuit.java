package tt.holdem.game;

public enum CardSuit {
    SPADES('S'),
    CLUBS('C'),
    DIAMONDS('D'),
    HEARTS('H');

    private final Character symbol;

    CardSuit(char symbol) {
        this.symbol = symbol;
    }

    public static CardSuit fromSymbol(Character symbol) {
        switch (symbol) {
            case 'S': return SPADES;
            case 'C': return CLUBS;
            case 'D': return DIAMONDS;
            case 'H': return HEARTS;
            default: throw new IllegalArgumentException(symbol.toString());
        }
    }

    @Override
    public String toString() {
        return symbol.toString();
    }
}
