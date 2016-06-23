package nl.groep4.kvc.server.model;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.CardType;
import nl.groep4.kvc.common.enumeration.VictoryCardType;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.util.CollectionUtil;
import nl.groep4.kvc.server.factory.CardFactory;

public class ServerCardHolder {

    private List<Card> cards;

    public ServerCardHolder() {
	cards = new ArrayList<>();
	List<CardType> types = CardFactory.getAllCards();
	for (int i = 0; i < types.size(); i++) {
	    CardType type = types.get(i);
	    switch (type) {
	    case FREE_STREETS:
	    case INVENTION:
	    case KNIGHT:
	    case MONOPOLY:
		cards.add(new ServerCard(type));
		break;
	    case VICTORY:
		cards.add(new ServerVictoryCard(type, VictoryCardType.values()[i]));
		break;
	    }
	}
    }

    public boolean hasCards() {
	return !cards.isEmpty();
    }

    public Card drawCard() {
	Card ret = CollectionUtil.randomItem(cards);
	cards.remove(ret);
	return ret;
    }

}
