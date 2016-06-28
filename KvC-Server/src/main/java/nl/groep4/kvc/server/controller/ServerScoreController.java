package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map.Entry;

import nl.groep4.kvc.common.enumeration.CardType;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.VictoryCard;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.util.Scheduler;
import nl.groep4.kvc.server.util.RoadFinder;

/**
 * Checks longest route, most knights and updates score.
 * 
 * @author Tim
 * @version 1.0
 */
public class ServerScoreController {

    private List<Player> players;
    private Map map;

    /**
     * Lists players.
     * 
     * @param players
     *            All the players.
     * @param map
     *            Current map.
     */
    public ServerScoreController(List<Player> players, Map map) {
	this.players = players;
	this.map = map;
    }

    /**
     * Updates score.
     */
    public void updateScores() {
	Scheduler.runAsyncLater(() -> {
	    checkMostKnights();
	    checkLongestRoute();
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
		    if (player.hasLongestRoad()) {
			score += 2;
		    }
		    if (player.hasMostKnights()) {
			score += 2;
		    }
		    player.setPoints(score);
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		}
	    }
	} , 10L);
    }

    private void checkLongestRoute() {
	Player longest = null;
	RoadFinder roads = new RoadFinder(map);
	int length = 3; // Should be 4, but works on 3?
	try {
	    for (Entry<Player, Integer> roadLenght : roads.getLongestRoadByPlayer().entrySet()) {
		if (roadLenght.getValue() == length) {
		    longest = null;
		    roadLenght.getKey().setHasLongestRoad(false);
		} else if (length < roadLenght.getValue()) {
		    longest = roadLenght.getKey();
		    length = roadLenght.getValue();
		} else {
		    roadLenght.getKey().setHasLongestRoad(false);
		}
	    }
	    if (longest != null) {
		longest.setHasLongestRoad(true);
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    private void checkMostKnights() {
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
		    pl.setMostRidder(false);
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
    }
}
