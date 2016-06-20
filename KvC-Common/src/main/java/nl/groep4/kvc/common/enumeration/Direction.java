package nl.groep4.kvc.common.enumeration;

import nl.groep4.kvc.common.interfaces.Offsetable;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.util.CollectionUtil;

/**
 * Directions to assign for relative tiles / buildings
 * 
 * @version 1.0
 * @author Tim
 */
public enum Direction implements Offsetable {

    NORTH, NORTH_EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, NORTH_WEST;

    @Override
    public Direction opposite() {
	return CollectionUtil.getInRange(Direction.values(), Direction.values().length / 2);
    }

    @Override
    public Direction[] getConnected() {
	return new Direction[] { CollectionUtil.getInRange(Direction.values(), ordinal() - 1),
		CollectionUtil.getInRange(Direction.values(), ordinal() + 1) };
    }

    @Override
    public Point[] getAttached() {
	return new Point[] { CollectionUtil.getInRange(Point.values(), ordinal() - 1),
		CollectionUtil.getInRange(Point.values(), ordinal()) };
    }

    @Override
    public Coordinate addTo(Coordinate original) {
	return original.add(offset(original));
    }

    @Override
    public Coordinate offset(Coordinate original) {
	return offset(original.getX() % 2 == 0);
    }

    @Override
    public Coordinate offset(boolean isEvenRow) {
	switch (this) {
	default:
	case NORTH:
	    return new Coordinate(0, -1);
	case NORTH_EAST:
	    if (isEvenRow) {
		return new Coordinate(1, 0);
	    } else {
		return new Coordinate(1, -1);
	    }
	case NORTH_WEST:
	    if (isEvenRow) {
		return new Coordinate(-1, 0);
	    } else {
		return new Coordinate(-1, -1);
	    }
	case SOUTH:
	    return new Coordinate(0, 1);
	case SOUTH_EAST:
	    if (!isEvenRow) {
		return new Coordinate(1, 0);
	    } else {
		return new Coordinate(1, 1);
	    }
	case SOUTH_WEST:
	    if (!isEvenRow) {
		return new Coordinate(-1, 0);
	    } else {
		return new Coordinate(-1, 1);
	    }
	}
    }
}
