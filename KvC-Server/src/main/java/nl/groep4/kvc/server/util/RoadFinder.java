package nl.groep4.kvc.server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;

/**
 * Class to find all the roads in a Map
 * 
 * @author MakerTim
 *
 */
public class RoadFinder {

    private Set<Street> streetsToCheck;
    private Set<Road> roads;

    /**
     * Sets up the class and find all the roads
     * 
     * @param map
     *            the map where the streets are in
     */
    public RoadFinder(Map map) {
	streetsToCheck = new HashSet<>();
	for (Street street : map.getAllStreets()) {
	    if (street.getOwner() != null) {
		streetsToCheck.add(street);
	    }
	}
	findRoads();
    }

    private void findRoads() {
	roads = new HashSet<>();
	for (Street street : streetsToCheck) {
	    boolean skip = false;
	    for (Road road : roads) {
		if (road.hasStreet(street)) {
		    skip = true;
		    break;
		}
	    }
	    if (skip) {
		continue;
	    }
	    Set<Street> road = new HashSet<>();
	    getAllConnected(street, road);
	    roads.add(new Road(road));
	}
    }

    private void getAllConnected(Street street, Set<Street> road) {
	List<Street> connected = connectedStreets(street, road);
	for (Street connectedStreet : connected) {
	    getAllConnected(connectedStreet, road);
	}
    }

    private List<Street> connectedStreets(Street street, Set<Street> road) {
	List<Street> ret = new ArrayList<>();
	for (Street connectedStreet : street.getConnectedStreets()) {
	    if (!road.contains(connectedStreet) && connectedStreet.getOwner() == street.getOwner()) {
		ret.add(connectedStreet);
		road.add(connectedStreet);
	    }
	}
	road.add(street);
	return ret;
    }

    /**
     * Looks through every road and finds the longest for each player
     * 
     * @return a Map where very player with a road is found in with there
     *         longest road count
     */
    public java.util.Map<Player, Integer> getLongestRoadByPlayer() {
	java.util.Map<Player, Integer> ret = new HashMap<>();
	for (Road road : roads) {
	    if (!ret.containsKey(road.getOwner())) {
		ret.put(road.getOwner(), 0);
	    }
	    ret.put(road.getOwner(), Math.max(road.getLongestPath(), ret.get(road.getOwner())));
	}
	return ret;
    }
}
