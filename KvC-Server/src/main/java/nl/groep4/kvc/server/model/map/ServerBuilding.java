package nl.groep4.kvc.server.model.map;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Tile;

public class ServerBuilding implements Building {

    private static final long serialVersionUID = -1934578502691308270L;

    private BuildingType type;
    private Player owner;
    private Coordinate location;
    private Tile[] tiles = new Tile[3];

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
	for (int i = 0; i < tiles.length; i++) {
	    if (tiles[i] == null) {
		tiles[i] = tile;
		return;
	    }
	}
	throw new IllegalArgumentException("A building can only have 3 tiles.");
    }

    @Override
    public void setBuildingType(BuildingType type) {
	this.type = type;
    }

    @Override
    public Tile[] getConnectedTiles() {
	return tiles;
    }

    @Override
    public Coordinate getPosition() {
	return location;
    }

    @Override
    public String toString() {
	return "Building @" + getPosition().toString() + " owned by " + getOwner();
    }
}
