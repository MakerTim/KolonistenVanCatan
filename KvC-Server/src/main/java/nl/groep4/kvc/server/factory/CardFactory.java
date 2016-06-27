package nl.groep4.kvc.server.factory;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.CardType;

/**
 * This class stores all the cards and its values in a List.
 * 
 * @author Tim
 * @version 1.0
 */
public class CardFactory {

    /**
     * Gets a List of all the cards in the game with its type.
     * 
     * @return A List of all the cards in the game.
     */
    public static List<CardType> getAllCards() {
	List<CardType> ret = new ArrayList<>();
	for (CardType cardType : CardType.values()) {
	    ret.add(cardType);
	}
	return ret;
    }

}
