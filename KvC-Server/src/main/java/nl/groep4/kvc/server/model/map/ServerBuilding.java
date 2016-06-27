package nl.groep4.kvc.server.model.map;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Tile;

/**
 * Instance of Building.
 * 
 * @author Tim
 * @version 1.1
 */
public class ServerBuilding implements Building {

    private static final long serialVersionUID = -1934578502691308270L;

    private BuildingType type;
    private Player owner;
    private Coordinate location;
    private List<Tile> tiles = new ArrayList<>();

    /**
     * Makes empty buildings at the corners of the tiles.
     * 
     * @param location
     *            The location of the buildings.
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
    public void registerTile(Tile tile) {
	tiles.add(tile);
    }

    @Override
    public void setBuildingType(BuildingType type) {
	this.type = type;
    }

    @Override
    public Tile[] getConnectedTiles() {
	return tiles.toArray(new Tile[tiles.size()]);
    }

    @Override
    public Coordinate getPosition() {
	return location;
    }

    @Override
    public String toString() {
	return "Building @" + getPosition().toString() + " owned by " + getOwner();
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Building) {
	    Building other = (Building) obj;
	    return other.getPosition().equals(getPosition());
	}
	return super.equals(obj);
    }
}
