package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;
import java.util.List;

import nl.groep4.kvc.common.enumeration.CardType;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.VictoryCard;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.util.Scheduler;

public class ServerScoreController {

    /**
     * elk 'laatste' straat (doodlopende straat) vinden <br>
     * vervolgens elk einde met elkaar kijken hoeveel stappen er tussen zitten
     * langste route = win
     */

    private List<Player> players;
    private Map map;

    public ServerScoreController(List<Player> players, Map map) {
	this.players = players;
	this.map = map;
	updateScores();
    }

    private void updateScores() {
	Scheduler.runAsync(() -> {
	    Player highestKnight = getMostKnights();
	    for (Player player : players) {
		try {
		    int score = 0;
		    for (Card cards : player.getCards()) {
			if (cards instanceof VictoryCard) {
			    ++score;
			}
		    }
		    for (Building building : map.getAllBuildings()) {
			if (player.equals(building.getOwner())) {
			    switch (building.getBuildingType()) {
			    case CITY:
				++score;
			    case VILLAGE:
				++score;
			    case EMPTY:
			    default:
				break;
			    }
			}
		    }
		    if (player.equals(highestKnight)) {
			++score;
		    }
		    player.setPoints(score);
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		}
	    }
	});
    }

    private Player getMostKnights() {
	Player most = null;
	int highest = 2;
	for (Player pl : players) {
	    try {
		int knights = 0;
		for (Card card : pl.getCards()) {
		    if (card.getType() == CardType.KNIGHT) {
			++knights;
		    }
		}
		if (highest == knights) {
		    most = null;
		} else if (highest < knights) {
		    most = pl;
		    highest = knights;
		} else {
		    pl.setMostRidder(false);
		}
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	}
	if (most != null) {
	    try {
		most.setMostRidder(true);
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	}
	return most;
    }
}
