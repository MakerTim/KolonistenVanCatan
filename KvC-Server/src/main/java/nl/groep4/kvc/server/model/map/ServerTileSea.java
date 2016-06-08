package nl.groep4.kvc.server.model.map;

import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.TileSea;
import nl.groep4.kvc.common.map.TileType;

/**
 * Instance of Tilesea
 * 
 * @author Tim
 * @version 1.0
 */
public class ServerTileSea extends ServerTile implements TileSea {

    private static final long serialVersionUID = 888777333123L;

    /**
     * Makes an empty sea tile
     * 
     * @param position
     */
    public ServerTileSea(Coordinate position) {
	super(TileType.WATER, position);
    }
}
