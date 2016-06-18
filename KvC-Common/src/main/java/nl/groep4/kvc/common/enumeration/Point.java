package nl.groep4.kvc.common.enumeration;

import nl.groep4.kvc.common.interfaces.Offsetable;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.util.CollectionUtil;

/**
 * Points available for interaction
 * 
 * @version 1.0
 * @author Tim
 */
public enum Point implements Offsetable {

    NORTH_EAST, EAST, SOUTH_EAST, SOUTH_WEST, WEST, NORTH_WEST;

    @Override
    public Point[] getConnected() {
	return new Point[] { CollectionUtil.getInRange(Point.values(), ordinal() - 1),
		CollectionUtil.getInRange(Point.values(), ordinal() + 1) };
    }

    @Override
    public Coordinate addTo(Coordinate original) {
	return original.add(offset(original));
    }

    @Override
    public Coordinate offset(Coordinate original) {
	return offset((int) original.getX() % 2 == 0);
    }

    @Override
    public Coordinate offset(boolean isEvenRow) {
	switch (this) {
	default:
	case EAST:
	    return new Coordinate(0.5, 0 + (isEvenRow ? 0 : -0.5));
	case NORTH_EAST:
	    return new Coordinate(0.5, -0.5 + (isEvenRow ? 0 : -0.5));
	case NORTH_WEST:
	    return new Coordinate(-0.5, -0.5 + (isEvenRow ? 0 : -0.5));
	case SOUTH_EAST:
	    return new Coordinate(0.5, 0.5 + (isEvenRow ? 0 : -0.5));
	case SOUTH_WEST:
	    return new Coordinate(-0.5, 0.5 + (isEvenRow ? 0 : -0.5));
	case WEST:
	    return new Coordinate(-0.5, 0 + (isEvenRow ? 0 : -0.5));
	}
    }

    public Coordinate realOffset() {
	switch (this) {
	default:
	case EAST:
	    return new Coordinate(0.5, 0);
	case NORTH_EAST:
	    return new Coordinate(0.25, 0.433015);
	case NORTH_WEST:
	    return new Coordinate(-0.25, 0.433015);
	case SOUTH_EAST:
	    return new Coordinate(0.25, -0.433015);
	case SOUTH_WEST:
	    return new Coordinate(-0.25, -0.433015);
	case WEST:
	    return new Coordinate(-0.5, 0);
	}
    }

}
