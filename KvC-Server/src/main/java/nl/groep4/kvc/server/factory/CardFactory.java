package nl.groep4.kvc.server.factory;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.CardType;
import nl.groep4.kvc.common.enumeration.VictoryCardType;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.server.model.ServerCard;
import nl.groep4.kvc.server.model.ServerVictoryCard;

public class CardFactory {

    public static List<Card> getAllCards() {
	List<Card> ret = new ArrayList<>();
	for (CardType cardType : CardType.values()) {
	    for (int i = 0; i < cardType.getAmount(); i++) {
		switch (cardType) {
		case FREE_STREETS:
		case INVENTION:
		case KNIGHT:
		case MONOPOLY:
		    ret.add(new ServerCard(cardType));
		    break;
		case VICTORY:
		    ret.add(new ServerVictoryCard(cardType, VictoryCardType.values()[i]));
		    break;
		}
	    }
	}
	return ret;
    }

}
