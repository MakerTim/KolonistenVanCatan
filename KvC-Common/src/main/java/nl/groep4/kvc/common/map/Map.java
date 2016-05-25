package nl.groep4.kvc.common.map;

import java.util.List;

public interface Map {

	public List<Tile> getTiles();

	public void createMap();

	public int getRound();

	public List<Building> getAllBuildings();

	public List<Street> getAllStreets();
}
