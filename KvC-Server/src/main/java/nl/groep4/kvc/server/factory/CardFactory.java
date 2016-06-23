package nl.groep4.kvc.server.factory;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.CardType;

public class CardFactory {

    public static List<CardType> getAllCards() {
	List<CardType> ret = new ArrayList<>();
	for (CardType cardType : CardType.values()) {
	    ret.add(cardType);
	}
	return ret;
    }

}
