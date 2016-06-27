package nl.groep4.kvc.server.model;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.CardType;
import nl.groep4.kvc.common.enumeration.VictoryCardType;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.util.CollectionUtil;
import nl.groep4.kvc.server.factory.CardFactory;

public class ServerCardHolder {

    /**
     * The factory to keep all the cards.
     * 
     * version: 1.0
     * 
     * @author: Tim
     */

    private List<Card> cards;

    public ServerCardHolder() {
	cards = new ArrayList<>();
	List<CardType> types = CardFactory.getAllCards();
	for (int i = 0; i < types.size(); i++) {
	    CardType type = types.get(i);
	    for (int j = 0; j < type.getAmount(); j++) {
		switch (type) {
		case FREE_STREETS:
		case INVENTION:
		case KNIGHT:
		case MONOPOLY:
		    cards.add(new ServerCard(type));
		    break;
		case VICTORY:
		    cards.add(new ServerVictoryCard(type, VictoryCardType.values()[j]));
		    break;
		}
	    }
	}
    }

    /**
     * 
     * @return !cards.isEmpty() It will return the Cards that are empty.
     */

    public boolean hasCards() {
	return !cards.isEmpty();
    }

    /**
     * It will draw a random card.
     * 
     * @return ret A randomized collection of cards.
     */
    public Card drawCard() {
	Card ret = CollectionUtil.randomItem(cards);
	cards.remove(ret);
	return ret;
    }

}
