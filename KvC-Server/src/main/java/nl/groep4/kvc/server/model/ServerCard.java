package nl.groep4.kvc.server.model;

import nl.groep4.kvc.common.enumeration.CardType;
import nl.groep4.kvc.common.interfaces.Card;

/**
 * The serverside of Card.
 * 
 * @version: 1.0
 * 
 * @author Tim
 *
 */

public class ServerCard implements Card {

    private static final long serialVersionUID = 1337L;

    private CardType type;

    public ServerCard(CardType type) {
	this.type = type;
    }

    @Override
    public CardType getType() {
	return type;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Card) {
	    Card other = (Card) obj;
	    return other.getType() == getType();
	}
	return super.equals(obj);
    }

}
