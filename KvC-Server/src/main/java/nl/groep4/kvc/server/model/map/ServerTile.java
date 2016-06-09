package nl.groep4.kvc.server.model.map;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.map.TileType;

public abstract class ServerTile implements Tile {

    private static final long serialVersionUID = 1096369L;

    private Coordinate position;
    private Street[] streets;
    private Building[] buildings;
    private TileType type;

    public ServerTile(TileType type, Coordinate position) {
	this.type = type;
	this.position = position;
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
	// TODO Tile#validplace
	return false;
    }

}
