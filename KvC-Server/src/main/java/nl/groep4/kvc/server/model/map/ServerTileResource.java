package nl.groep4.kvc.server.model.map;

import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.TileResource;
import nl.groep4.kvc.common.map.TileType;

/**
 * Instance of tile resource.
 *
 * @author Tim
 * @version 1.1
 */
public class ServerTileResource extends ServerTile implements TileResource {

    private static final long serialVersionUID = 19071996112112112L;

    private boolean hasRover = false;
    private int number;

    /**
     * Makes an empty resource tile.
     *
     * @param type
     *            The Type of resource on the tile.
     * @param position
     *            The position of the tile.
     */
    public ServerTileResource(TileType type, int number, Coordinate position) {
	super(type, position);
	this.number = number;
    }

    @Override
    public boolean hasRover() {
	return hasRover;
    }

    @Override
    public void placeRover() {
	hasRover = true;
    }

    @Override
    public void removeRover() {
	hasRover = false;
    }

    @Override
    public Resource getResource() {
	return Resource.values()[getType().ordinal()];
    }

    @Override
    public int getNumber() {
	return number;
    }

    @Override
    public String toString() {
	return "Resource " + getResource().name() + " @" + getPosition().toString();
    }
}
