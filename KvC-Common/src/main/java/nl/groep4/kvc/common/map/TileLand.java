package nl.groep4.kvc.common.map;

/**
 * Checks if rover is on tile, places rover and removes rover.
 * 
 * @author Tim
 * @version 1.0
 */
public interface TileLand extends Tile {

    /**
     * Checks If rover is on tile.
     * 
     * @return True or false, depends if rover is on the selected tile.
     */
    public boolean hasRover();

    /**
     * Paces rover.
     */
    public void placeRover();

    /**
     * Removes rover.
     */
    public void removeRover();
}
