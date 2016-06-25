package nl.groep4.kvc.common.map;

import java.io.Serializable;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;

/**
 * Define positions of tiles, streets and buildings.
 *
 * @version 1.0
 * @author Tim
 */
public interface Tile extends Serializable, Locatable {

    public TileType getType();

    /**
     * Gets street.
     *
     * @param direction
     *            Direction of street.
     * @return Gets direction of street.
     */
    public Street getStreet(Direction direction);

    /**
     * Gets the direction of the given street, will be null if not found.
     * 
     * @param street
     *            the street attached to the tile
     * @return the direction of the street, null if not found
     */
    public Direction getDirection(Street street);

    /**
     * Gets building.
     *
     * @param point
     *            Available points.
     * @return Gets Interactable building points.
     */
    public Building getBuilding(Point point);

    /**
     * Gets the point of the given building, will be null if not found.
     * 
     * @param building
     *            the building attached to the tile
     * @return the point of the building, null if not found
     */
    public Point getPoint(Building building);

    /**
     * Gets streets.
     * 
     * @return Streets.
     */
    public Street[] getStreets();

    /**
     * Gets buildings.
     * 
     * @return Buildings.
     */
    public Building[] getBuildings();

    /**
     * Sets streets.
     * 
     * @param streets
     *            Street location and direction.
     */
    public void setupStreets(Street[] streets);

    /**
     * Sets building.
     *
     * @param buildings
     *            Building location and direction.
     */
    public void setupBuilding(Building[] buildings);

    /**
     * Returns true when place is valid and false when place is not valid.
     *
     * @param point
     *            Available point.
     * @param map
     *            Valid place on map.
     * @return True or false.
     */
    public boolean isValidPlace(Map map, Point point);

    /**
     * Get relative map and direction.
     * 
     * @param map
     *            Valid place on map.
     * @param direction
     *            Available directions.
     * @return Map and directions.
     */
    public Tile getRelative(Map map, Direction direction);

    /**
     * Checks if place is valid.
     *
     * @param map
     *            Valid place on map.
     * @param direction
     *            Available directions.
     * @return Valid place and direction.
     */
    public default boolean isValidPlace(Map map, Direction direction) {
	return (this instanceof TileLand || map.getRelativeTile(this, direction) instanceof TileLand)
		&& getStreet(direction) != null && getStreet(direction).getOwner() == null;
    }

}
