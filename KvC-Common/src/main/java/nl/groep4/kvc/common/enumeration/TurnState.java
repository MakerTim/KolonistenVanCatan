package nl.groep4.kvc.common.enumeration;

/**
 * All activities that can be done.
 * 
 * @author Tim
 * @version 1.0
 */
public enum TurnState {

    THROWING_DICE, BUILDING_STREET, BUILDING_BUILDING, BUYING_CARD, WAITING, TRADING;

    /**
     * All translation of this method.
     * 
     * @return All translations.
     */
    public String translate() {
	switch (this) {
	case BUILDING_BUILDING:
	    return "turn.state.building";
	case BUILDING_STREET:
	    return "turn.state.street";
	case BUYING_CARD:
	    return "turn.state.card";
	case THROWING_DICE:
	    return "turn.state.dice";
	case TRADING:
	    return "turn.state.trade";
	case WAITING:
	    return "turn.state.wait";
	}
	return null;
    };
}
