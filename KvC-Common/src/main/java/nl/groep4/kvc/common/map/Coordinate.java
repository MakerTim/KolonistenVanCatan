package nl.groep4.kvc.common.map;

import java.io.Serializable;

public final class Coordinate implements Serializable, Cloneable {

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

    public boolean isSameLocation(short x, short y) {
	return this.x == x && this.y == y;
    }

    public Coordinate add(short x, short y) {
	return new Coordinate((short) (this.x + x), (short) (this.y + y));
    }

    public Coordinate multiply(short x, short y) {
	return new Coordinate((short) (this.x * x), (short) (this.y * y));
    }

    public Coordinate subtract(short x, short y) {
	return new Coordinate((short) (this.x / x), (short) (this.y / y));
    }

    public short getX() {
	return this.x;
    }

    public short getY() {
	return this.y;
    }

    @Override
    public String toString() {
	return getKey();
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Coordinate) {
	    Coordinate other = (Coordinate) obj;
	    return isSameLocation(other.x, other.y);
	}
	return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
	return new Coordinate(x, y);
    }
}
