package nl.groep4.kvc.server.model;

import nl.groep4.kvc.common.enumeration.CardType;
import nl.groep4.kvc.common.interfaces.Card;

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

}
