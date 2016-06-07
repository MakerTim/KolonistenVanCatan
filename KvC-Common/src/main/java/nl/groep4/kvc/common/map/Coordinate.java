package nl.groep4.kvc.common.map;

import java.io.Serializable;

public final class Coordinate implements Serializable {

    private static final long serialVersionUID = 12345678987654321L;

    private short x;
    private short y;

    public Coordinate(short x, short y) {
	this.x = x;
	this.y = y;
    }

    public String getKey() {
	return String.format("%d;%d", x, y);
    }

    @Override
    public String toString() {
	return getKey();
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Coordinate) {
	    Coordinate other = (Coordinate) obj;
	    return other.x == this.x && other.y == this.y;
	}
	return super.equals(obj);
    }

}
