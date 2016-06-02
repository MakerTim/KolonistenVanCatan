package nl.groep4.kvc.common.map;

import java.util.List;

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
     * @return gets current round
     */
    public int getRound();

    /**
     * 
     * @return gets a list of all buildings
     */
    public List<Building> getAllBuildings();

    /**
     * 
     * @return gets a list of all streets
     */
    public List<Street> getAllStreets();
}
