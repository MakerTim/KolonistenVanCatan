package nl.groep4.kvc.server.model.map;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.map.TileType;

/**
 * Instance of Tile.
 * 
 * @author Tim
 * @version 1.1
 */
public abstract class ServerTile implements Tile {

    private static final long serialVersionUID = 1096369L;

    private Coordinate position;
    private Street[] streets;
    private Building[] buildings = new Building[6];
    private TileType type;

    /**
     * Makes an empty tile.
     * 
     * @param type
     *            The type of tile in the game.
     * @param position
     *            The position of the tile.
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
    public Tile getRelative(Map map, Direction direction) {
	return map.getRelativeTile(this, direction);
    }

    @Override
    public Street getStreet(Direction direction) {
	return streets[direction.ordinal()];
    }

    @Override
    public Direction getDirection(Street street) {
	for (int i = 0; i < streets.length; i++) {
	    if (street.equals(streets[i])) {
		return Direction.values()[i];
	    }
	}
	return null;
    }

    @Override
    public Building getBuilding(Point point) {
	return buildings[point.ordinal()];
    }

    @Override
    public Point getPoint(Building building) {
	for (int i = 0; i < streets.length; i++) {
	    if (building.equals(buildings[i])) {
		return Point.values()[i];
	    }
	}
	return null;
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
	for (Point neighbour : point.getConnected()) {
	    Building neighbourBuilding = getBuilding(neighbour);
	    if (neighbourBuilding != null && neighbourBuilding.getOwner() != null) {
		validPlace = false;
	    }
	}
	return validPlace;
    }

    @Override
    public String toString() {
	return "Tile " + getClass().getSimpleName() + " @" + getPosition().toString();
    }

}
