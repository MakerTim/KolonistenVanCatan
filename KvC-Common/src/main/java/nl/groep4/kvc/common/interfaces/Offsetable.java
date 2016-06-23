package nl.groep4.kvc.common.interfaces;

import nl.groep4.kvc.common.map.Coordinate;

/**
 * Indicates it's an location focused class.
 * 
 * @author Tim
 * @version 1.0
 */
public interface Offsetable {

    /**
     * Adds coordinate.
     * 
     * @param Original
     *            The original coordinate.
     * @return Coordinate.
     */
    public Coordinate addTo(Coordinate original);

    /**
     * Offset of coordinate.
     * 
     * @param original
     *            The original coordinate.
     * @return Coordinate.
     */
    public Coordinate offset(Coordinate original);

    /**
     * Offset of coordinate that can either be true or false.
     * 
     * @param isEvenRow
     *            Can either be true or false.
     * @return True or false depends on isEvenRow.
     */
    public Coordinate offset(boolean isEvenRow);

    /**
     * Gets connected.
     * 
     * @return Connection.
     */
    public Offsetable[] getConnected();

    /**
     * Gets attached.
     * 
     * @return Attached.
     */
    public Offsetable[] getAttached();

    /**
     * Offsetable of opposite.
     * 
     * @return The opposite.
     */
    public Offsetable opposite();

}
