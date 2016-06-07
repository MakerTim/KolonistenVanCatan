package nl.groep4.kvc.common.enumeration;

import nl.groep4.kvc.common.map.Coordinate;

/**
 * Directions to assign for relative tiles / buildings
 * 
 * @version 1.0
 * @author Tim
 */
public enum Direction {

    NORTH, NORTH_EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, NORTH_WEST;

    public Coordinate addTo(Coordinate original) {
	return original.add(offset(original.getX() % 2 == 0));
    }

    public Coordinate offset(Coordinate original) {
	return offset(original.getX() % 2 == 0);
    }

    public Coordinate offset(boolean isEvenRow) {
	Coordinate ret;
	switch (this) {
	default:
	case NORTH:
	    ret = new Coordinate(0, -1);
	    break;
	case NORTH_EAST:
	    if (isEvenRow) {
		ret = new Coordinate(1, 0);
	    } else {
		ret = new Coordinate(1, -1);
	    }
	    break;
	case NORTH_WEST:
	    if (isEvenRow) {
		ret = new Coordinate(-1, 0);
	    } else {
		ret = new Coordinate(-1, -1);
	    }
	    break;
	case SOUTH:
	    ret = new Coordinate(0, 1);
	    break;
	case SOUTH_EAST:
	    if (!isEvenRow) {
		ret = new Coordinate(1, 0);
	    } else {
		ret = new Coordinate(1, 1);
	    }
	    break;
	case SOUTH_WEST:
	    if (!isEvenRow) {
		ret = new Coordinate(1, 0);
	    } else {
		ret = new Coordinate(-1, 1);
	    }
	    break;
	}
	return ret;
    }
}
