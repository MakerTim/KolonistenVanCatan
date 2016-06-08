package nl.groep4.kvc.server.model.map;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.map.TileLand;
import nl.groep4.kvc.common.map.TileType;
import nl.groep4.kvc.common.util.CollectionUtil;
import nl.groep4.kvc.server.factory.TileFactory;

public class ServerMap implements Map {

    private final List<Tile> tiles = new ArrayList<>();
    private final List<Building> buildings = new ArrayList<>();
    private final List<Street> streets = new ArrayList<>();

    public static void main(String[] args) {
	Map map = new ServerMap();
	map.createMap();
	map.getAdjacentTile(new Coordinate(0.5, 1));
    }

    @Override
    public List<Tile> getTiles() {
	return tiles;
    }

    @Override
    public void createMap() {
	List<TileType> typesTodo = TileFactory.getNeeded();
	typesTodo.remove(TileType.DESERT);
	typesTodo.remove(TileType.DESERT);
	int cols = 9;
	for (int col = 0; col < cols; col++) {
	    int rows = cols - Math.abs(col - ((cols - 1) / 2)) - 1;
	    for (int row = 0; row < rows; row++) {
		Coordinate position = new Coordinate(col - cols / 2, row - rows / 2);
		if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
		    getTiles().add(new ServerTileSea(position));
		} else if (position.getX() == 0 && (position.getY() == -2 || position.getY() == 1)) {
		    getTiles().add(new ServerTileDesert(position));
		} else {
		    TileType randomType = CollectionUtil.randomItem(typesTodo);
		    typesTodo.remove(randomType);
		    getTiles().add(new ServerTileResource(randomType, position));
		}
	    }
	}
	setupStreets();
	setupBuildings();
    }

    private void setupStreets() {
	for (Tile tile : getTiles()) {
	    Street[] streets = new Street[Direction.values().length];
	    for (int i = 0; i < streets.length; i++) {
		Direction direction = Direction.values()[i];
		Tile relative = getRelativeTile(tile, direction);
		if (tile instanceof TileLand || relative instanceof TileLand) {
		    Coordinate location = tile.getPosition().add(direction.offset(tile.getPosition()).subtract(2));
		    Street street = getStreet(location);
		    if (street == null) {
			street = new ServerStreet(location);
		    }
		    streets[i] = street;
		    this.streets.add(street);
		}
	    }
	    tile.setupStreets(streets);
	}
    }

    private void setupBuildings() {
	for (Tile tile : getTiles()) {
	    Building[] buildings = new Building[Point.values().length];
	    for (int i = 0; i < buildings.length; i++) {
		Point point = Point.values()[i];
		Building building;
		if (getAdjacentTile(point.addTo(tile.getPosition())).length == 0) {
		    Coordinate location = tile.getPosition().add(point.offset(tile.getPosition()).subtract(2));
		    building = new ServerBuilding(location);
		    this.buildings.add(building);
		}

	    }
	    tile.setupBuilding(buildings);
	}
    }

    @Override
    public List<Building> getAllBuildings() {
	return buildings;
    }

    @Override
    public Building getBuilding(Tile tile, Point point) {
	return tile.getBuilding(point);
    }

    @Override
    public Building getBuilding(Coordinate location) {
	return buildings.stream().filter(building -> building.getPosition().equals(location)).findAny().orElse(null);
    }

    @Override
    public List<Street> getAllStreets() {
	return streets;
    }

    @Override
    public Street getStreet(Tile tile, Direction direction) {
	return tile.getStreet(direction);
    }

    @Override
    public Street getStreet(Coordinate location) {
	return streets.stream().filter(street -> street.getPosition().equals(location)).findAny().orElse(null);
    }

    @Override
    public Tile getRelativeTile(Tile tile, Direction direction) {
	return getTiles().stream().filter(aTile -> aTile.getPosition().equals(direction.addTo(tile.getPosition())))
		.findAny().orElse(null);
    }

    @Override
    public Tile getTile(Coordinate coord) {
	return getTiles().stream().filter(aTile -> aTile.getPosition().equals(coord)).findAny().orElse(null);
    }

    @Override
    public Tile getTile(int x, int y) {
	return getTile(new Coordinate(x, y));
    }

    @Override
    public Tile[] getAdjacentTile(Building building) {
	return getAdjacentTile(building.getPosition());
    }

    @Override
    public Tile[] getAdjacentTile(Coordinate location) {
	List<Tile> adjacent = new ArrayList<>();
	for (Point point : Point.values()) {
	    System.out.println(point.name());
	}
	return adjacent.toArray(new Tile[adjacent.size()]);
    }
}
