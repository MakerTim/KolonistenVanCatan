package nl.groep4.kvc.common.map;

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

public interface Map {

    /**
     * 
     * @return a list of the tiles
     */
    public List<Tile> getTiles();

    /**
     * creates map
     */
    public void createMap();

    /**
     * 
     * @return gets a list of all buildings
     */
    public List<Building> getAllBuildings();

    public Building getBuilding(Coordinate location);

    public Building getBuilding(Tile tile, Point point);

    /**
     * 
     * @return gets a list of all streets
     */
    public List<Street> getAllStreets();

    public Street getStreet(Coordinate location);

    public Street getStreet(Tile tile, Direction direction);

    /**
     * 
     * @param direction
     * @return gets tile direction
     */
    public Tile getRelativeTile(Tile tile, Direction direction);

    public Tile[] getAdjacentTile(Building building);

    public Tile[] getAdjacentTile(Coordinate location);

    public Tile getTile(Coordinate coord);

    public Tile getTile(int x, int y);
}
