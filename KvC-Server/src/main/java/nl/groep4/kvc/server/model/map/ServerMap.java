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

/**
 * Instance of the map in the game.
 *
 * @author Tim
 * @version 1.1
 */
public class ServerMap implements Map {

    private static final long serialVersionUID = 2507200235719526105L;

    private final List<Tile> tiles = new ArrayList<>();
    private final List<Building> buildings = new ArrayList<>();
    private final List<Street> streets = new ArrayList<>();

    @Override
    public List<Tile> getTiles() {
	return tiles;
    }

    @Override
    public void createMap() {
	List<TileType> typesTodo = TileFactory.getNeeded();
	List<Integer> numbersTodo = TileFactory.getNumbers();
	typesTodo.remove(TileType.DESERT);
	typesTodo.remove(TileType.DESERT);
	for (int col = 0; col < Map.COLUMS; col++) {
	    int rows = Map.COLUMS - Math.abs(col - ((Map.COLUMS - 1) / 2)) - 1;
	    for (int row = 0; row < rows; row++) {
		Coordinate position = new Coordinate(col - Map.COLUMS / 2, row - rows / 2);
		if (row == 0 || row == rows - 1 || col == 0 || col == Map.COLUMS - 1) {
		    getTiles().add(new ServerTileSea(position));
		} else if (position.getX() == 0 && (position.getY() == -2 || position.getY() == 1)) {
		    getTiles().add(new ServerTileDesert(position));
		} else {
		    Integer number = CollectionUtil.randomItem(numbersTodo);
		    TileType randomType = CollectionUtil.randomItem(typesTodo);
		    numbersTodo.remove(number);
		    typesTodo.remove(randomType);
		    getTiles().add(new ServerTileResource(randomType, number, position));
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
			this.streets.add(street);
		    }
		    street.registerTile(tile);
		    streets[i] = street;
		}
	    }
	    tile.setupStreets(streets);
	}
    }

    private void setupBuildings() {
	List<Tile> toRegister = new ArrayList<>();
	for (Tile tile : getTiles()) {
	    if (!(tile instanceof TileLand)) {
		toRegister.add(tile);
		continue;
	    }
	    Building[] buildings = new Building[Point.values().length];
	    for (int i = 0; i < buildings.length; i++) {
		Coordinate location = Point.values()[i].addTo(tile.getPosition());
		Building building = getBuilding(location);
		if (building == null) {
		    building = new ServerBuilding(location);
		    this.buildings.add(building);
		}
		building.registerTile(tile);
		buildings[i] = building;
	    }
	    tile.setupBuilding(buildings);
	}
	for (Tile todo : toRegister) {
	    Building[] buildings = new Building[Point.values().length];
	    for (int i = 0; i < Point.values().length; i++) {
		Coordinate location = Point.values()[i].addTo(todo.getPosition());
		buildings[i] = getBuilding(location);
	    }
	    todo.setupBuilding(buildings);
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
	return building.getConnectedTiles();
    }
}
