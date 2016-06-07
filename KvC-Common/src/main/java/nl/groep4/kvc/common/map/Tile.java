package nl.groep4.kvc.common.map;

import java.io.Serializable;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;

/**
 * Define positions of tiles, streets and buildings
 * 
 * @version 1.0
 * @author Tim
 */

public interface Tile extends Serializable {

    /**
     * 
     * @return gets position of tile
     */
    public Coordinate getPosition();

    /**
     * 
     * @param direction
     * @return gets tile direction
     */
    public Tile getRelativeTile(Direction direction);

    /**
     * 
     * @param direction
     * @return gets direction of street
     */
    public Street getStreet(Direction direction);

    /**
     * 
     * @param point
     * @return gets building point
     */
    public Building getBuilding(Point point);

}
