package nl.groep4.kvc.server.model;

import java.util.List;

import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.util.CollectionUtil;
import nl.groep4.kvc.server.factory.CardFactory;

public class ServerCardHolder {

    private List<Card> remainingCards = CardFactory.getAllCards();

    public boolean hasCards() {
	return !remainingCards.isEmpty();
    }

    public Card drawCard() {
	Card ret = CollectionUtil.randomItem(remainingCards);
	remainingCards.remove(ret);
	return ret;
    }

}
