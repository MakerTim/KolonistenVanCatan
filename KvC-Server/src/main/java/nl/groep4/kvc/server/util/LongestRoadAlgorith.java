package nl.groep4.kvc.server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;

public class LongestRoadAlgorith {

    private Set<Street> streetsToCheck;
    Player longestRoadPlayer;

    java.util.Map<Player, Integer> longestCheck = new HashMap<>();

    public LongestRoadAlgorith(Map map) {
	streetsToCheck = new HashSet<>();

	for (Street street : map.getAllStreets()) {
	    if (street.getOwner() != null) {
		streetsToCheck.add(street);
	    }
	}
	startCounting();
    }

    private void startCounting() {
	for (Street street : streetsToCheck) {
	    int lenght = countRoute(street, 0, true);

	    Player owner = street.getOwner();
	    if (!longestCheck.containsKey(owner)) {
		longestCheck.put(owner, 1);
	    }
	    longestCheck.put(owner, Math.max(lenght, longestCheck.get(owner)));
	}
    }

    private int countRoute(Street street, int count, boolean init) {
	List<Street> connected = connectedStreets(street);
	int routeLenght = count;
	List<Integer> ints = new ArrayList<>();

	for (int i = 0; i < connected.size(); i++) {
	    ints.add(countRoute(connected.get(i), count - 1, false));
	}
	if (init) {
	    for (int i : ints) {
		routeLenght += i;
	    }
	} else {
	    if (ints.size() == 1) {
		routeLenght += ints.get(0);
	    } else if (ints.size() > 1) {
		ints.sort((i1, i2) -> Integer.compare(i1, i2));
		routeLenght += ints.get(0);
	    }
	}
	return routeLenght;
    }

    private List<Street> connectedStreets(Street street) {
	List<Street> ret = new ArrayList<>();

	Tile[] connectedTiles = street.getConnectedTiles();
	Direction[] relativeDirections = new Direction[connectedTiles.length];
	for (Direction direction : Direction.values()) {
	    if (street.equals(connectedTiles[0].getStreet(direction))) {
		relativeDirections[0] = direction;
		relativeDirections[1] = direction.opposite();
		break;
	    }
	}
	for (int i = 0; i < relativeDirections.length; i++) {
	    Direction[] connected = relativeDirections[i].getConnected();
	    for (int j = 0; j < connected.length; j++) {
		Street connectedStreet = connectedTiles[i].getStreet(connected[j]);
		if (connectedStreet != null && connectedStreet.getOwner() == street.getOwner()) {
		    ret.add(connectedStreet);
		}
	    }
	}
	return ret;
    }

    public void setPlayerWithLongestRoad(Player pl) {

	Player playerL;

    }

    public java.util.Map<Player, Integer> getLongestRoad() {
	return longestCheck;
    }
}
