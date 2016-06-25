package nl.groep4.kvc.server.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.util.CollectionUtil;

/**
 * A road object, used only for {@link RoadFinder}
 * 
 * @author MakerTim
 * @version 1.0
 *
 */
class Road {

    private Set<Street> streets;

    /**
     * Constructs a road
     * 
     * @param streets
     *            a set of streets
     */
    public Road(Set<Street> streets) {
	this.streets = streets;
    }

    /**
     * Gets the owner of this road
     * 
     * @return the Player from who the road is
     */
    public Player getOwner() {
	return CollectionUtil.first(streets).getOwner();
    }

    /**
     * Check if the given street is inside this road
     * 
     * @param street
     *            the street to check
     * @return returns true if the street is resent
     */
    public boolean hasStreet(Street street) {
	return streets.contains(street);
    }

    /**
     * Gets all roads on the outside of a path<br>
     * <b>note that branches of size 1 are not counted as corner</b>
     * 
     * @return a set of streets that are at the end of a path inside the road
     */
    public Set<Street> getCorners() {
	Set<Street> corners = new HashSet<>();
	for (Street street : streets) {
	    if (neighbours(street).size() < 2) {
		corners.add(street);
	    }
	}
	if (corners.isEmpty()) {
	    corners = new HashSet<>(streets);
	}
	return corners;
    }

    /**
     * Calculates the longest path inside the road
     * 
     * @return the longest road
     */
    public int getLongestPath() {
	int lenght = 0;
	for (Street corner : getCorners()) {
	    lenght = Math.max(roadsFind(corner, new HashSet<>(), 0), lenght);
	}
	return lenght;
    }

    private int roadsFind(Street toFind, Set<Street> visited, int length) {
	visited.add(toFind);
	for (Street neighbour : neighbours(toFind)) {
	    if (!visited.contains(neighbour)) {
		length = Math.max(roadsFind(neighbour, /* new HashSet<> */(visited), length + 1), length);
	    }
	}
	return length;
    }

    private List<Street> neighbours(Street street) {
	List<Street> neighbours = new ArrayList<>();
	for (Street connected : street.getConnectedStreets()) {
	    if (street.getOwner().equals(connected.getOwner())) {
		neighbours.add(connected);
	    }
	}
	return neighbours;
    }

}
