package nl.groep4.kvc.common.map;

import java.io.Serializable;

import nl.groep4.kvc.common.interfaces.Ownable;

/**
 * Defines street
 * 
 * @version 1.0
 * @author Tim
 */
public interface Street extends Ownable, Serializable, Locatable {

    /**
     * Registers tile.
     * 
     * @param tile
     *            Tile to register.
     */
    public void registerTile(Tile tile);

    /**
     * Gets connected tiles.
     * 
     * @return Connected tiles.
     */
    public Tile[] getConnectedTiles();

}
