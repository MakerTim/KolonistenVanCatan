package nl.groep4.kvc.server.model.map;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.TileSea;
import nl.groep4.kvc.common.map.TileType;

public class ServerTileSea implements TileSea {

    private static final long serialVersionUID = 19071996112112112L;

    private Coordinate position;
    private Street[] streets;
    private Building[] buildings;

    public ServerTileSea(Coordinate position) {
	this.position = position;
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
	// TODO SeaTile#ValidPlace
	return false;
    }

    @Override
    public boolean isValidPlace(Map map, Direction direction) {
	// TODO SeaTile#ValidPlace
	return false;
    }

    @Override
    public TileType getType() {
	// TODO Auto-generated method stub
	return null;
    }
}
