package nl.groep4.kvc.common.enumeration;

import nl.groep4.kvc.common.interfaces.Offsetable;
import nl.groep4.kvc.common.map.Coordinate;

/**
 * Points available for interaction
 * 
 * @version 1.0
 * @author Tim
 */
public enum Point implements Offsetable {

    NORTH_EAST, EAST, SOUTH_EAST, SOUTH_WEST, WEST, NORTH_WEST;

    @Override
    public Coordinate addTo(Coordinate original) {
	return original.add(offset(original.getX() % 2 == 0));
    }

    @Override
    public Coordinate offset(Coordinate original) {
	return offset(original.getX() % 2 == 0);
    }

    @Override
    public Coordinate offset(boolean isEvenRow) {
	switch (this) {
	default:
	case EAST:
	    if (isEvenRow) {
		return new Coordinate(0.5, 0);
	    } else {
		return new Coordinate(0.5, -0.5);
	    }
	case NORTH_EAST:
	    if (isEvenRow) {
		return new Coordinate(0.5, -0.5);
	    } else {
		return new Coordinate(0.5, -1);
	    }
	case NORTH_WEST:
	    if (isEvenRow) {
		return new Coordinate(-0.5, -0.5);
	    } else {
		return new Coordinate(-0.5, -1);
	    }
	case SOUTH_EAST:
	    if (isEvenRow) {
		return new Coordinate(-0.5, 0.5);
	    } else {
		return new Coordinate(-0.5, 0);
	    }
	case SOUTH_WEST:
	    if (isEvenRow) {
		return new Coordinate(-0.5, 0.5);
	    } else {
		return new Coordinate(-0.5, 0);
	    }
	case WEST:
	    if (isEvenRow) {
		return new Coordinate(-0.5, 0);
	    } else {
		return new Coordinate(-0.5, -0.5);
	    }
	}
    }

    public Coordinate realOffset(Coordinate original) {
	return realOffset(original.getX() % 2 == 0);
    }

    public Coordinate realOffset(boolean isEvenRow) {
	switch (this) {
	default:
	case EAST:
	    return new Coordinate(0.5, 0);
	case NORTH_EAST:
	    return new Coordinate(0.25, -0.433015);
	case NORTH_WEST:
	    return new Coordinate(-0.25, -0.433015);
	case SOUTH_EAST:
	    return new Coordinate(0.25, 0.433015);
	case SOUTH_WEST:
	    return new Coordinate(-0.25, 0.433015);
	case WEST:
	    return new Coordinate(-0.5, 0);
	}
    }

}
