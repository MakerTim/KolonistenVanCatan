package nl.groep4.kvc.common.map;

import java.util.List;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.server.model.map.ServerMap;

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

    /**
     * 
     * @param location
     *            Refers to {@link Coordinate} for building location
     * @return Gets building coordinates
     */
    public Building getBuilding(Coordinate location);

    /**
     * 
     * @param tile
     *            Refers to {@link Tile} for tile location
     * @param point
     *            Refers to {@link Point} for available placement points
     * @return Gets building locations on the map
     */
    public Building getBuilding(Tile tile, Point point);

    /**
     * 
     * @return gets a list of all streets
     */
    public List<Street> getAllStreets();

    /**
     * 
     * @param location
     *            Refers to {@link Coordinate} for street coordinates
     * @return Gets street location
     */
    public Street getStreet(Coordinate location);

    /**
     * 
     * @param tile
     *            Refers to {@link Tile} to define street location
     * @param direction
     *            Refers to {@link Direction} to retrieve street direction on
     *            map
     * @return Gets street direction relative to map
     */
    public Street getStreet(Tile tile, Direction direction);

    /**
     * 
     * @param direction
     *            Refers to {@link Direction} for tile orientation
     * @return Gets tile being interacted with
     */
    public Tile getRelativeTile(Tile tile, Direction direction);

    /**
     * 
     * @param building
     *            Refers to {@link Building} for buildings on adjacent tiles
     * @return Building and building type on adjacent tile
     */
    public Tile[] getAdjacentTile(Building building);

    /**
     * 
     * @param location
     *            Refers to {@link ServerMap} for building location on adjacent
     *            tiles
     * @return Gets location of building on adjacent tile
     */
    public Tile[] getAdjacentTile(Coordinate location);

    /**
     * 
     * @param coord
     *            Refers to {@link ServerMap} for tile position on map
     * @return Gets Tile coordinates
     */
    public Tile getTile(Coordinate coord);

    /**
     * 
     * @param x
     *            Refers to {@link ServerMap} for X coordinate relative to
     *            centre
     * @param y
     *            Refers to {@link ServerMap} for Y coordinate relative to
     *            centre
     * @return Gets tile location relative to centre tile
     */
    public Tile getTile(int x, int y);
}
