package nl.groep4.kvc.server.model.map;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;

/**
 * Instance of street.
 * 
 * @author Tim
 * @version 1.1
 *
 */
public class ServerStreet implements Street {

    private static final long serialVersionUID = 7162302038488828886L;

    private Coordinate location;
    private Player owner;
    private Tile[] tiles = new Tile[2];

    /**
     * Makes empty streets at the points where the streets can be placed.
     * 
     * @param location
     *            The location of the streets.
     */
    public ServerStreet(Coordinate location) {
	this.location = location;
    }

    @Override
    public Player getOwner() {
	return owner;
    }

    @Override
    public void setOwner(Player player) {
	owner = player;
    }

    @Override
    public Coordinate getPosition() {
	return location;
    }

    @Override
    public String toString() {
	return "Street @" + getPosition().toString();
    }

    @Override
    public void registerTile(Tile tile) {
	for (int i = 0; i < tiles.length; i++) {
	    if (tiles[i] == null) {
		tiles[i] = tile;
		return;
	    }
	}
	throw new IllegalArgumentException("A street can only have 2 tiles.");
    }

    @Override
    public Tile[] getConnectedTiles() {
	return tiles;
    }

    @Override
    public Street[] getConnectedStreets() {
	List<Street> connectedStreets = new ArrayList<>();
	Tile[] connectedTiles = getConnectedTiles();
	Direction[] directions = new Direction[connectedTiles.length];
	for (int i = 0; i < connectedTiles.length; i++) {
	    Tile tile = connectedTiles[i];
	    directions[i] = tile.getDirection(this);
	}
	for (int i = 0; i < directions.length; i++) {
	    Direction[] connected = directions[i].getConnected();
	    for (Direction direction : connected) {
		Street connectedStreet = connectedTiles[i].getStreet(direction);
		if (connectedStreet != null) {
		    connectedStreets.add(connectedStreet);
		}
	    }
	}
	return connectedStreets.toArray(new Street[connectedStreets.size()]);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Street) {
	    Street other = (Street) obj;
	    return other.getPosition().equals(getPosition());
	}
	return super.equals(obj);
    }

}
