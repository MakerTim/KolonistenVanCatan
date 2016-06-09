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

    public void registerTile(Tile tile);

    public Tile[] getConnectedTiles();

}
