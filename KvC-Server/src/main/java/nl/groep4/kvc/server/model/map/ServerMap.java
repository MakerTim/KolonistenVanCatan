package nl.groep4.kvc.server.model.map;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;

public class ServerMap implements Map {

    private final List<Tile> tiles = new ArrayList<>();

    public static void main(String[] args) {

    }

    public ServerMap() {
	int cols = 9;
	for (short col = 0; col < cols; col++) {
	    int rows = cols - Math.abs(col - ((cols - 1) / 2)) - 1;
	    for (short row = 0; row < rows; row++) {
		Coordinate position = new Coordinate(row, col);
		if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
		    tiles.add(new ServerTileSea(position));
		} else if (col == 0 && (row == -2 || row == 1)) {

		} else {
		    tiles.add(new ServerTileResource(position));
		}

	    }
	}
    }

    @Override
    public List<Tile> getTiles() {
	return tiles;
    }

    @Override
    public void createMap() {
	// TODO Auto-generated method stub

    }

    @Override
    public List<Building> getAllBuildings() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Street> getAllStreets() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Tile getRelativeTile(Tile tile, Direction direction) {
	// TODO Auto-generated method stub
	return null;
    }

}
