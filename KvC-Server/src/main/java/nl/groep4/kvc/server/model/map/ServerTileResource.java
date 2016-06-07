package nl.groep4.kvc.server.model.map;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.TileResource;
import nl.groep4.kvc.common.map.TileType;

public class ServerTileResource implements TileResource {

    private static final long serialVersionUID = 19071996112112112L;

    private Coordinate position;
    private Street[] streets;
    private Building[] buildings;

    private TileType type;

    public ServerTileResource(Coordinate position, TileType type) {
	this.position = position;
	this.type = type;
    }

    @Override
    public Coordinate getPosition() {
	return position;
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
    public boolean isValidPlace(Map map, Point point) {
	// TODO TileLand#validplace
	return false;
    }

    @Override
    public boolean hasRover() {
	// TODO TileLand#hasrover
	return false;
    }

    @Override
    public Resource getResource() {
	return Resource.values()[type.ordinal()];
    }

    @Override
    public boolean isValidPlace(Map map, Direction direction) {
	// TODO TileLand#isvalidplace
	return false;
    }

    @Override
    public TileType getType() {
	return type;
    }
}
