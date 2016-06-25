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
    private Set<Set<Street>> routes;
    java.util.Map<Player, Integer> longestCheck = new HashMap<>();

    public LongestRoadAlgorith(Map map) {
	streetsToCheck = new HashSet<>();
	for (Street street : map.getAllStreets()) {
	    if (street.getOwner() != null) {
		streetsToCheck.add(street);
	    }
	}
	findRouds();

    }

    private void findRouds() {
	routes = new HashSet<>();
	for (Street street : streetsToCheck) {
	    /* START om ConcurrentModificationException te voorkomen */
	    boolean skip = false;
	    for (Set<Street> route : routes) {
		if (route.contains(street)) {
		    skip = true;
		}
	    }
	    if (skip) {
		continue;
	    }
	    /* END om ConcurrentModificationException te voorkomen */
	    Set<Street> route = new HashSet<>();
	    getAllConnected(street, route);
	    routes.add(route);
	}
    }

    private void getAllConnected(Street street, Set<Street> route) {
	List<Street> connected = connectedStreets(street, route);
	for (Street connectedStreet : connected) {
	    getAllConnected(connectedStreet, route);
	}
    }

    private List<Street> connectedStreets(Street street, Set<Street> route) {
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
		    if (!route.contains(connectedStreet)) {
			ret.add(connectedStreet);
			route.add(connectedStreet);
		    }
		}
	    }
	}
	route.add(street);
	return ret;
    }

    public java.util.Map<Player, Integer> getLongestRoad() {
	return longestCheck;
    }
}
