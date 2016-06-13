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

public interface Tile extends Serializable, Locatable {

    public TileType getType();

    /**
     *
     * @param direction
     *            Refers to {@link Direction}
     * @return gets direction of street
     */
    public Street getStreet(Direction direction);

    /**
     *
     * @param point
     *            Refers to {@link Point}
     * @return gets interactable building points
     */
    public Building getBuilding(Point point);

    /**
     *
     * @param streets
     *            Refers to {@link ServerMap} for street location and direction
     */
    public void setupStreets(Street[] streets);

    /**
     *
     * @param buildings
     *            Refers to {@link ServerMap} for building location and
     *            direction
     */
    public void setupBuilding(Building[] buildings);

    /**
     *
     * @param point
     * @return
     */
    public boolean isValidPlace(Point point);

    /**
     *
     * @param map
     * @param direction
     * @return
     */
    public default boolean isValidPlace(Map map, Direction direction) {
	return this instanceof TileLand || map.getRelativeTile(this, direction) instanceof TileLand;
    }

}
