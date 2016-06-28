package nl.groep4.kvc.common.map;

import java.io.Serializable;

/**
 * Its the reference to the location.
 * 
 * @author Tim
 * @version 1.1
 */
public final class Coordinate implements Serializable, Cloneable {

    private static final long serialVersionUID = 12345678987654321L;

    private double x;
    private double y;

    /**
     * Sets up the coordinates.
     * 
     * @param x
     *            The xCoordinate.
     * @param y
     *            The yCoordinate.
     */
    public Coordinate(double x, double y) {
	this.x = x;
	this.y = y;
    }

    /**
     * Gets the key of the item you want the coordinate from.
     * 
     * @return The key.
     */
    public String getKey() {
	return String.format("%d;%d", x, y);
    }

    /**
     * Returns true or false on if item is in the same location.
     * 
     * @param x
     *            The xCoordinate
     * @param y
     *            The yCoordinate
     * @return True or false about whether item is in the same location.
     */
    public boolean isSameLocation(double x, double y) {
	return this.x == x && this.y == y;
    }

    /**
     * Adds coordinates to an item.
     * 
     * @param added
     *            The added coordinates.
     * @return The item with the added coordinates.
     */
    public Coordinate add(Coordinate added) {
	return add(added.getX(), added.getY());
    }

    /**
     * Multiplies the coordinates.
     * 
     * @param multiplied
     *            The coordinate that needs to be multiplied.
     * @return The multiplied coordinate
     */
    public Coordinate multiply(Coordinate multiplied) {
	return multiply(multiplied.getX(), multiplied.getY());
    }

    /**
     * Subtracts the coordinates.
     * 
     * @param subtracted
     *            The coordinates.
     * @return The subtracted coordinates.
     */
    public Coordinate subtract(Coordinate subtracted) {
	return subtract(subtracted.getX(), subtracted.getY());
    }

    /**
     * Adds numbers to the coordinates. In this method the numbers will be added
     * to the coordinates at once.
     * 
     * @param added
     *            The added numbers.
     * @return New added numbers.
     */
    public Coordinate add(double added) {
	return add(added, added);
    }

    /**
     * Multiplies the coordinates. In this method the coordinates will be
     * multiplied at once.
     * 
     * @param multiplied
     *            The given number to multiply the coordinates with.
     * @return The new coordinates.
     */
    public Coordinate multiply(double multiplied) {
	return multiply(multiplied, multiplied);
    }

    /**
     * subtracts the numbers of the coordinates. In this method the coordinates
     * will be subtracted at once.
     * 
     * @param subtracted
     *            The given number to subtract the coordinates with.
     * @return The new coordinates.
     */
    public Coordinate subtract(double subtracted) {
	return subtract(subtracted, subtracted);
    }

    /**
     * Adds given numbers to the X and the Y coordinates.
     * 
     * @param x
     *            The number to add to the X coordinate.
     * @param y
     *            The number to add to the X coordinate.
     * @return The new coordinates.
     */
    public Coordinate add(double x, double y) {
	return new Coordinate(this.x + x, this.y + y);
    }

    /**
     * Multiplies the given numbers to the X and the Y coordinates.
     * 
     * @param x
     *            The number to multiply the X coordinate with.
     * @param y
     *            The number to multiply the Y coordinate with.
     * @return The new coordinates.
     */
    public Coordinate multiply(double x, double y) {
	return new Coordinate(this.x * x, this.y * y);
    }

    /**
     * Subtracts the given number with the X and the Y coordinates.
     * 
     * @param x
     *            The number to subtract the X coordinate with.
     * @param y
     *            The number to subtract the Y coordinate with.
     * @return The new coordinates.
     */
    public Coordinate subtract(double x, double y) {
	return new Coordinate(this.x / x, this.y / y);
    }

    /**
     * Gets the X coordinate.
     * 
     * @return The X coordinate.
     */
    public double getX() {
	return this.x;
    }

    /**
     * Gets the Y coordinate.
     * 
     * @return The Y coordinate.
     */
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
