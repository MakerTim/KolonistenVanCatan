package nl.groep4.kvc.common.map;

import java.io.Serializable;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;

/**
 * Get all tiles to generate map. Determine what round and place all buildings
 * and streets.
 *
 * @version 1.0
 * @author Tim
 */
public interface Map extends Serializable {

    /**
     * Amount of columns
     */
    public static final int COLUMS = 9;

    /**
     * Gets tiles.
     * 
     * @return A list of the tiles.
     */
    public List<Tile> getTiles();

    /**
     * Creates map
     */
    public void createMap();

    /**
     * Gets all buildings.
     * 
     * @return Gets a list of all buildings.
     */
    public List<Building> getAllBuildings();

    /**
     * Gets building location.
     * 
     * @param location
     *            The coordinates of the building.
     * @return Gets building coordinates.
     */
    public Building getBuilding(Coordinate location);

    /**
     * Gets building tile and point.
     *
     * @param tile
     *            Location of the tile.
     * @param point
     *            Available placement points.
     * @return Gets building locations on the map.
     */
    public Building getBuilding(Tile tile, Point point);

    /**
     * Gets all streets.
     *
     * @return Gets a list of all streets.
     */
    public List<Street> getAllStreets();

    /**
     * Gets street coordinate.
     *
     * @param location
     *            Street coordinates.
     * @return Gets street location.
     */
    public Street getStreet(Coordinate location);

    /**
     * Gets street tile and direction.
     * 
     * @param tile
     *            Defines street location.
     * @param direction
     *            Retrieves street location on map.
     * @return Gets street direction relative to map.
     */
    public Street getStreet(Tile tile, Direction direction);

    /**
     * Get relative tile and direction.
     *
     * @param direction
     *            Refers to tile orientation.
     * @return Gets tile being interacted with.
     */
    public Tile getRelativeTile(Tile tile, Direction direction);

    /**
     * Gets adjacent Tile.
     *
     * @param building
     *            Refers to buildings on adjacent tiles.
     * @return Building and building type on adjacent tile.
     */
    public Tile[] getAdjacentTile(Building building);

    /**
     * Gets tile coordinate.
     *
     * @param coord
     *            Refers to tile position on map.
     * @return Gets Tile coordinates.
     */
    public Tile getTile(Coordinate coord);

    /**
     * Gets tile variables.
     *
     * @param x
     *            X coordinate relative to centre.
     * @param y
     *            Y coordinate relative to centre.
     * @return Gets tile location relative to centre tile.
     */
    public Tile getTile(int x, int y);
}
