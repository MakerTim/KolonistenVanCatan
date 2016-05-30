package nl.groep4.kvc.common.enumeration;

public enum CardType {

    KNIGHT(20), VICTORY(5), MONOPOLY(3), FREE_STREETS(2), INVENTION(3);

    int amount;

    private CardType(int amount) {
	this.amount = amount;
    }
}
