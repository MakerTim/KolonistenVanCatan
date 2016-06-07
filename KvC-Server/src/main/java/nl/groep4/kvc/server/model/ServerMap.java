package nl.groep4.kvc.server.model;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;

public class ServerMap implements Map {

    private final List<Tile> tiles = new ArrayList<>();

    public static void main(String[] args) {
	int size = 9;
	for (int col = 0; col < size; col++) {
	    int rows = size - Math.abs(col - ((size - 1) / 2)) - 1;

	    for (int row = 0; row < rows; row++) {
	    }
	}
    }

    public ServerMap() {

    }

    @Override
    public List<Tile> getTiles() {
	// TODO Auto-generated method stub
	return null;
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

}
