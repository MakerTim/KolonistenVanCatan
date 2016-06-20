package nl.groep4.kvc.common.enumeration;

/**
 * All card types and amounts per type
 * 
 * @version 1.0 30-5-2016
 * @author Tim
 */
public enum CardType {

    KNIGHT(20), VICTORY(VictoryCardType.values().length), MONOPOLY(3), FREE_STREETS(2), INVENTION(3);

    int amount;

    private CardType(int amount) {
	this.amount = amount;
    }

    public int getAmount() {
	return amount;
    }
}
