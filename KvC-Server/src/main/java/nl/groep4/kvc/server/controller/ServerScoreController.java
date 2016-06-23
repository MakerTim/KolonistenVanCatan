package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.CardType;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.Player;

public class ServerScoreController {

    private ServerKolonistenVanCatan controller;

    public ServerScoreController(ServerKolonistenVanCatan controller) {
	this.controller = controller;
    }

    public void calculateScores() {
	checkLongestRoute();
	checkLargestArmy();
    }

    private void checkLongestRoute() {
	// TODO: longest route
    }

    private void checkLargestArmy() {
	List<Player> armySorted = new ArrayList<>(controller.getPlayers());
	armySorted.sort((pl1, pl2) -> {
	    return Integer.compare(getKnightAmmount(pl1), getKnightAmmount(pl2));
	});
    }

    private int getKnightAmmount(Player pl) {
	try {
	    List<Card> cards = pl.getCards();
	    int ret = 0;
	    for (Card card : cards) {
		if (card.getType() == CardType.KNIGHT) {
		    ++ret;
		}
	    }
	    return ret;
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	    return 0;
	}
    }

}
