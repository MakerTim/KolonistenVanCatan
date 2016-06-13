package nl.groep4.kvc.client.debug;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import nl.groep4.kvc.client.controller.MapController;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Updatable;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.map.TileLand;
import nl.groep4.kvc.common.map.TileType;
import nl.groep4.kvc.common.util.CollectionUtil;
import nl.groep4.kvc.server.factory.TileFactory;
import nl.groep4.kvc.server.model.map.ServerBuilding;
import nl.groep4.kvc.server.model.map.ServerStreet;
import nl.groep4.kvc.server.model.map.ServerTileDesert;
import nl.groep4.kvc.server.model.map.ServerTileResource;
import nl.groep4.kvc.server.model.map.ServerTileSea;

public class TestMapController extends MapController {

    private static Map map;

    public TestMapController(SceneMap view) {
	super((DebugKolonistenVanCatan) (map = new DebugKolonistenVanCatan()), view);
    }

    public Map getMap() {
	return map;
    }

    private static class DebugKolonistenVanCatan implements KolonistenVanCatan, Map, Tile, Player {

	private static final long serialVersionUID = 0L;
	private int nxtRound = 0;
	private List<Player> players;

	@Override
	public int getRound() throws RemoteException {
	    return nxtRound;
	}

	@Override
	public void nextRound() throws RemoteException {
	    nxtRound++;
	}

	@Override
	public void nextTurn() throws RemoteException {
	    nextRound();
	}

	@Override
	public Map getMap() throws RemoteException {
	    return this;
	}

	@Override
	public List<Player> getPlayers() throws RemoteException {
	    if (players == null) {
		players = new ArrayList<>();
		players.add(this);
	    }
	    return null;
	}

	@Override
	public List<Player> getPlayersOrded() throws RemoteException {
	    return getPlayers();
	}

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
	    for (Tile tile : getTiles()) {
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
	    return buildings.stream().filter(building -> building.getPosition().equals(location)).findAny()
		    .orElse(null);
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

	@Override
	public void placeStreet(Coordinate coord, Player newOwner) throws RemoteException {
	    Street street = this.getStreet(coord);
	    try {
		street.setOwner(newOwner);
	    } catch (Exception ex) {
		System.err.println(ex);
	    }
	}

	private Coordinate position = new Coordinate(0, 0);
	private Street[] streetz;
	private Building[] buildingz;
	private TileType type = TileType.WHEAT;

	@Override
	public Coordinate getPosition() {
	    return position;
	}

	@Override
	public TileType getType() {
	    return type;
	}

	@Override
	public Street getStreet(Direction direction) {
	    return streetz[direction.ordinal()];
	}

	@Override
	public Building getBuilding(Point point) {
	    return buildingz[point.ordinal()];
	}

	@Override
	public void setupStreets(Street[] streets) {
	    this.streetz = streets;
	}

	@Override
	public void setupBuilding(Building[] buildings) {
	    this.buildingz = buildings;
	}

	@Override
	public boolean isValidPlace(Point point) {
	    for (int i = 0; i < 2; i++) {
		if (streetz[CollectionUtil.modInRange(streetz, i)] == null) {
		    return false;
		}
	    }
	    return true;
	}

	private String username = "TestPersoon";
	private Updatable<?> updatable;
	private Color color;
	private List<Card> cards = new ArrayList<>();
	private EnumMap<Resource, Integer> resources;

	@Override
	public String getUsername() {
	    return username;
	}

	@Override
	public void registerUpdateable(Updatable<?> updatable) {
	    this.updatable = updatable;
	}

	@Override
	public Updatable<?> getUpdateable() {
	    return updatable;
	}

	@Override
	public boolean equals(Object obj) {
	    if (obj instanceof Player) {
		Player other = (Player) obj;
		try {
		    return other.getUsername().equals(getUsername());
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		}
	    }
	    return super.equals(obj);
	}

	@Override
	public Color getColor() throws RemoteException {
	    return color;
	}

	@Override
	public void setColor(Color color) throws RemoteException {
	    this.color = color;
	}

	@Override
	public List<Card> getCards() throws RemoteException {
	    return cards;
	}

	@Override
	public EnumMap<Resource, Integer> getResources() throws RemoteException {
	    return resources;
	}

	@Override
	public void placeBuilding(Coordinate coord, Player newOwner, BuildingType type) throws RemoteException {
	    Building building = this.getBuilding(coord);
	    building.setOwner(newOwner);
	    building.setBuildingType(type);
	}
    }
}
