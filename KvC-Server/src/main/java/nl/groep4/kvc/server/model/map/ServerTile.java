package nl.groep4.kvc.server.model.map;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.map.TileLand;
import nl.groep4.kvc.common.map.TileType;

/**
 * Instance of Tile
 * 
 * @author Tim
 * @version 1.0
 */
public abstract class ServerTile implements Tile {

    private static final long serialVersionUID = 1096369L;

    private Coordinate position;
    private Street[] streets;
    private Building[] buildings;
    private TileType type;

    /**
     * Makes an empty tile
     * 
     * @param type
     * @param position
     */
    public ServerTile(TileType type, Coordinate position) {
	this.type = type;
	this.position = position;
    }

    @Override
    public Building[] getBuildings() {
	return buildings;
    }

    @Override
    public Street[] getStreets() {
	return streets;
    }

    @Override
    public Coordinate getPosition() {
	return position;
    }

    @Override
    public TileType getType() {
	return type;
    }

    @Override
    public Street getStreet(Direction direction) {
	return streets[direction.ordinal()];
    }

    @Override
    public Building getBuilding(Point point) {
	return buildings[point.ordinal()];
    }

    @Override
    public void setupStreets(Street[] streets) {
	this.streets = streets;
    }

    @Override
    public void setupBuilding(Building[] buildings) {
	this.buildings = buildings;
    }

    @Override
    public boolean isValidPlace(Map map, Point point) {
	if (getBuilding(point) == null) {
	    return false;
	}
	if (getBuilding(point).getOwner() != null) {
	    return false;
	}
	boolean validPlace = true;
	boolean hasLandTile = false;
	for (Tile tile : getBuilding(point).getConnectedTiles()) {
	    if (tile instanceof TileLand) {
		hasLandTile = true;
	    }
	    for (Point neighbour : point.getConnected()) {
		Building neighbourBuilding = tile.getBuilding(neighbour);
		if (neighbourBuilding != null && neighbourBuilding.getOwner() != null) {
		    validPlace = false;
		}
	    }
	}
	return validPlace && hasLandTile;
    }

    @Override
    public String toString() {
	return "Tile " + getClass().getSimpleName() + " @" + getPosition().toString();
    }

}
