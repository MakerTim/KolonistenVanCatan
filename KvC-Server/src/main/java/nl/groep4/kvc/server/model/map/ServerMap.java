package nl.groep4.kvc.server.model.map;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.map.TileType;
import nl.groep4.kvc.common.util.CollectionUtil;
import nl.groep4.kvc.server.factory.TileFactory;

public class ServerMap implements Map {

    private final List<Tile> tiles = new ArrayList<>();
    private final List<Building> buildings = new ArrayList<>();
    private final List<Street> streets = new ArrayList<>();

    public static void main(String[] args) {
	new ServerMap().createMap();
    }

    @Override
    public List<Tile> getTiles() {
	return tiles;
    }

    @Override
    public void createMap() {
	List<TileType> typesTodo = TileFactory.getNeeded();
	int cols = 9;
	for (short col = 0; col < cols; col++) {
	    int rows = cols - Math.abs(col - ((cols - 1) / 2)) - 1;
	    for (short row = 0; row < rows; row++) {
		Coordinate position = new Coordinate(col - cols / 2, row - rows / 2);
		if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
		    tiles.add(new ServerTileSea(position));
		} else if (col == 0 && (row == -2 || row == 1)) {
		    typesTodo.remove(TileType.DESERT);
		    tiles.add(new ServerTileDesert(position));
		} else {
		    TileType randomType = CollectionUtil.randomItem(typesTodo);
		    typesTodo.remove(randomType);
		    tiles.add(new ServerTileResource(randomType, position));
		}
	    }
	}
	setupStreets();
    }

    private void setupStreets() {

    }

    @Override
    public List<Building> getAllBuildings() {
	return buildings;
    }

    @Override
    public List<Street> getAllStreets() {
	return streets;
    }

    @Override
    public Tile getRelativeTile(Tile tile, Direction direction) {
	Coordinate findAt = tile.getPosition();
	switch (direction) {
	case NORTH:
	    findAt = findAt.add(0, -1);
	    break;
	case NORTH_EAST:
	    if (findAt.getX() % 2 == 0) {
		findAt = findAt.add(1, 0);
	    } else {
		findAt = findAt.add(1, -1);
	    }
	    break;
	case NORTH_WEST:
	    if (findAt.getX() % 2 == 0) {
		findAt = findAt.add(-1, 0);
	    } else {
		findAt = findAt.add(-1, -1);
	    }
	    break;
	case SOUTH:
	    findAt = findAt.add(0, 1);
	    break;
	case SOUTH_EAST:
	    if (findAt.getX() % 2 == 1) {
		findAt = findAt.add(1, 0);
	    } else {
		findAt = findAt.add(1, 1);
	    }
	    break;
	case SOUTH_WEST:
	    if (findAt.getX() % 2 == 1) {
		findAt = findAt.add(1, 0);
	    } else {
		findAt = findAt.add(-1, 1);
	    }
	    break;
	}
	Coordinate toFind = findAt;
	return tiles.stream().filter(aTile -> aTile.getPosition().equals(toFind)).findAny().orElse(null);
    }

    @Override
    public Tile getTile(Coordinate coord) {
	return tiles.stream().filter(aTile -> aTile.getPosition().equals(coord)).findAny().orElse(null);
    }

    @Override
    public Tile getTile(short x, short y) {
	return getTile(new Coordinate(x, y));
    }

}
