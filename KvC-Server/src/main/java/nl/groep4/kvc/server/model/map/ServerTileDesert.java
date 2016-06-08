package nl.groep4.kvc.server.model.map;

import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.TileLand;
import nl.groep4.kvc.common.map.TileType;

public class ServerTileDesert extends ServerTile implements TileLand {

    private static final long serialVersionUID = 19960811L;

    public ServerTileDesert(Coordinate position) {
	super(TileType.DESERT, position);
    }

    @Override
    public boolean hasRover() {
	// TODO TileLand#hasrover
	return false;
    }

    @Override
    public String toString() {
	return "Desert @" + getPosition().toString();
    }
}
