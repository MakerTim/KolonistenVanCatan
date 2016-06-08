package nl.groep4.kvc.server.model.map;

import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.TileResource;
import nl.groep4.kvc.common.map.TileType;

public class ServerTileResource extends ServerTile implements TileResource {

    private static final long serialVersionUID = 19071996112112112L;

    public ServerTileResource(TileType type, Coordinate position) {
	super(type, position);
    }

    @Override
    public boolean hasRover() {
	// TODO TileLand#hasrover
	return false;
    }

    @Override
    public Resource getResource() {
	return Resource.values()[getType().ordinal()];
    }

    @Override
    public String toString() {
	return "Resource " + getResource().name() + " @" + getPosition().toString();
    }
}
