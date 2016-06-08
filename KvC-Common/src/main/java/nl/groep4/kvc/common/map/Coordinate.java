package nl.groep4.kvc.common.map;

import java.io.Serializable;

public final class Coordinate implements Serializable, Cloneable {

    private static final long serialVersionUID = 12345678987654321L;

    private double x;
    private double y;

    public Coordinate(double x, double y) {
	this.x = x;
	this.y = y;
    }

    public String getKey() {
	return String.format("%d;%d", x, y);
    }

    public boolean isSameLocation(double x, double y) {
	return this.x == x && this.y == y;
    }

    public Coordinate add(Coordinate added) {
	return add(added.getX(), added.getY());
    }

    public Coordinate multiply(Coordinate multiplied) {
	return multiply(multiplied.getX(), multiplied.getY());
    }

    public Coordinate subtract(Coordinate subtracted) {
	return subtract(subtracted.getX(), subtracted.getY());
    }

    public Coordinate add(double added) {
	return add(added, added);
    }

    public Coordinate multiply(double multiplied) {
	return multiply(multiplied, multiplied);
    }

    public Coordinate subtract(double subtracted) {
	return subtract(subtracted, subtracted);
    }

    public Coordinate add(double x, double y) {
	return new Coordinate(this.x + x, this.y + y);
    }

    public Coordinate multiply(double x, double y) {
	return new Coordinate(this.x * x, this.y * y);
    }

    public Coordinate subtract(double x, double y) {
	return new Coordinate(this.x / x, this.y / y);
    }

    public double getX() {
	return this.x;
    }

    public double getY() {
	return this.y;
    }

    @Override
    public String toString() {
	return x + ";" + y;
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
