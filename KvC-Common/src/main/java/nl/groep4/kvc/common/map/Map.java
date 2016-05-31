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

    public List<Tile> getTiles();

    public void createMap();

    public int getRound();

    public List<Building> getAllBuildings();

    public List<Street> getAllStreets();
}
