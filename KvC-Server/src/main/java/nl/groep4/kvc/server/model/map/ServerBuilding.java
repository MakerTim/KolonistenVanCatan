package nl.groep4.kvc.server.model.map;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Tile;

/**
 * instance of Building
 * 
 * @author Tim
 *
 */
public class ServerBuilding implements Building {

    private static final long serialVersionUID = -1934578502691308270L;

    private BuildingType type;
    private Player owner;
    private Coordinate location;

    /**
     * Makes an empty building at the corners of the tiles
     * 
     * @param location
     */
    public ServerBuilding(Coordinate location) {
	this.location = location;
    }

    @Override
    public Player getOwner() {
	return owner;
    }

    @Override
    public void setOwner(Player player) {
	owner = player;
    }

    @Override
    public BuildingType getBuildingType() {
	return type;
    }

    @Override
    public void setBuildingType(BuildingType type) {
	this.type = type;
    }

    @Override
    public Tile[] getConnectedTiles(Map map) {
	return null;
    }

    @Override
    public Coordinate getPosition() {
	return location;
    }

}
