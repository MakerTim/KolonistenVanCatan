package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.GameState;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.enumeration.SelectState;
import nl.groep4.kvc.common.enumeration.TurnState;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Throw;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.util.Scheduler;
import nl.groep4.kvc.server.model.ServerCosts;
import nl.groep4.kvc.server.model.map.ServerMap;

/**
 * Instance of KolonistenVanCatan
 * 
 * @author Tim
 * @version 1.0
 */
public class ServerKolonistenVanCatan implements KolonistenVanCatan {

    ServerTradeController tradeController;
    ServerTurnController turnController;
    ServerShopController shopController;
    ServerMapController mapController;
    ServerCardController cardController;

    private final List<Player> players;
    private ServerMap map = new ServerMap();
    private int round = -1;
    private int turn = -1;
    private Throw lastThrow;
    private GameState state;

    public ServerKolonistenVanCatan(List<Player> players) {
	System.out.println("Starting game!");
	this.players = players;
	tradeController = new ServerTradeController(this);
	turnController = new ServerTurnController(this);
	shopController = new ServerShopController(this);
	mapController = new ServerMapController(this);
	cardController = new ServerCardController(this);
	players.sort((pl1, pl2) -> {
	    return Integer.compare(pl1.hashCode(), pl2.hashCode());
	});
	System.out.println("\tRandomized players");
    }

    @Override
    public void start() {
	System.out.println("\tStarted game!");
	state = GameState.INIT;
	updateCosts();
	nextTurn();
    }

    @Override
    public Map getMap() {
	return map;
    }

    @Override
    public void createMap() {
	map.createMap();
    }

    @Override
    public GameState getState() {
	return this.state;
    }

    @Override
    public boolean isMovingRover() {
	return mapController.isMovingRover();
    }

    public void setState(GameState state) {
	this.state = state;
    }

    @Override
    public int getRound() {
	return round;
    }

    @Override
    public void nextRound() {
	round++;
    }

    public int newTurn() {
	return ++turn - 1;
    }

    public void resetTurn() {
	turn = 0;
    }

    @Override
    public List<Player> getPlayers() {
	return players;
    }

    @Override
    public void nextTurn() {
	turnController.nextTurn();
    }

    @Override
    public Player getTurn() {
	return getPlayersOrded().get(0);
    }

    @Override
    public List<Player> getPlayersOrded() {
	List<Player> orded = new ArrayList<>();
	for (int i = 0; i < players.size(); i++) {
	    orded.add(players.get((i + turn) % players.size()));
	}
	return orded;
    }

    @Override
    public void placeBuilding(Player newOwner, Coordinate coord, BuildingType type) {
	mapController.placeBuilding(newOwner, coord, type);
    }

    @Override
    public void placeStreet(Player newOwner, Coordinate coord) {
	mapController.placeStreet(newOwner, coord);
    }

    @Override
    public void moveFromRover(Player turn, Coordinate position) throws RemoteException {
	if (getTurn().equals(turn)) {
	    mapController.moveRoverFrom(position);
	}
    }

    @Override
    public void moveToRover(Player turn, Coordinate position) throws RemoteException {
	if (getTurn().equals(turn)) {
	    mapController.moveRoverTo(position);
	}
    }

    @Override
    public void throwDices() {
	ServerThrowController diceController = new ServerThrowController(this);
	lastThrow = diceController.getThrow();
	diceController.updateThrow();
    }

    public Throw getLastThrow() {
	return this.lastThrow;
    }

    @Override
    public void distribute() {
	mapController.distribute();
    }

    public void highlightBandit(Player pl) {
	try {
	    pl.setSelectable(SelectState.BANDIT);
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    public void highlightBuildings(Player pl, BuildingType type) {
	Set<Building> buildings;
	switch (type) {
	case VILLAGE:
	    switch (getState()) {
	    case INIT:
		buildings = new HashSet<>(getMap().getAllBuildings());
		for (Tile tile : getMap().getTiles()) {
		    for (Point point : Point.values()) {
			if (!tile.isValidPlace(getMap(), point)) {
			    buildings.remove(tile.getBuilding(point));
			}
		    }
		}
		break;
	    case IN_GAME:
		buildings = new HashSet<>();
		for (Tile tile : getMap().getTiles()) {
		    for (Direction direction : Direction.values()) {
			if (tile.getStreet(direction) != null
				&& getTurn().equals(tile.getStreet(direction).getOwner())) {
			    for (Point point : direction.getAttached()) {
				buildings.add(tile.getBuilding(point));
			    }
			}
		    }
		}
		for (Tile tile : getMap().getTiles()) {
		    for (Point point : Point.values()) {
			if (!tile.isValidPlace(getMap(), point)) {
			    buildings.remove(tile.getBuilding(point));
			}
		    }
		}
		break;
	    default:
	    case END:
		buildings = new HashSet<>();
		break;
	    }
	    break;
	case CITY:
	    buildings = new HashSet<>();
	    for (Building building : getMap().getAllBuildings()) {
		if (pl.equals(building.getOwner())) {
		    buildings.add(building);
		}
	    }
	    break;
	default:
	case EMPTY:
	    buildings = new HashSet<>();
	    break;
	}
	highlightBuildings(pl, buildings, type);
    }

    public void highlightStreet(Player pl) {
	Set<Street> streets = new HashSet<>();
	for (Tile tile : getMap().getTiles()) {
	    for (Direction direction : Direction.values()) {
		Street street = tile.getStreet(direction);
		if (street != null && pl.equals(street.getOwner())) {
		    for (Direction connectedDirection : direction.getConnected()) {
			Street connected = tile.getStreet(connectedDirection);
			if (connected != null && connected.getOwner() == null) {
			    streets.add(connected);
			}
		    }
		}
	    }
	}
	highlightStreets(pl, streets);
    }

    public void highlightBuildings(Player pl, Collection<Building> buildings, BuildingType type) {
	try {
	    pl.getUpdateable(UpdateMap.class).highlightBuildings(buildings, type);
	    pl.setSelectable(SelectState.BUILDING);
	    updateState(TurnState.BUILDING_BUILDING);
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    public void highlightStreets(Player pl, Collection<Street> streets) {
	try {
	    pl.getUpdateable(UpdateMap.class).highlightStreets(streets);
	    pl.setSelectable(SelectState.STREET);
	    updateState(TurnState.BUILDING_STREET);
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    @Override
    public void buyStreet() {
	shopController.buyStreet();
    }

    @Override
    public void buyVillage() {
	shopController.buyVillage();
    }

    @Override
    public void buyCity() {
	shopController.buyCity();
    }

    @Override
    public void buyCard() {
	shopController.buyCard();
    }

    @Override
    public void useCard(Card card) throws RemoteException {
	cardController.useCard(getTurn(), card);
    }

    @Override
    public void trade(UUID tradeKey, Player with) throws RemoteException {
	tradeController.onTrade(tradeController.getTrade(tradeKey), with);
    }

    @Override
    public void addTrade(Player player, java.util.Map<Resource, Integer> request,
	    java.util.Map<Resource, Integer> reward) throws RemoteException {
	tradeController.addTrade(player, request, reward);
	updateTrades();
    }

    @Override
    public void remvoeTrade(UUID key) throws RemoteException {
	tradeController.removeTrade(key);
	updateTrades();
    }

    @Override
    public void reconnect() throws RemoteException {
	updateMap();
	updateResources();
	updateTrades();
	updateRound();
	updateTurn();
	updateTrades();
    }

    @Override
    public void updateMap() {
	List<Runnable> runs = new ArrayList<>();
	for (Player pl : getPlayers()) {
	    runs.add(() -> {
		try {
		    pl.getUpdateable(UpdateMap.class).setModel(getMap());
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		}
	    });
	}
	Scheduler.runAsyncdSync(runs);
    }

    public void updateResources() {
	List<Runnable> runs = new ArrayList<>();
	for (Player pl : getPlayers()) {
	    runs.add(() -> {
		for (Player player : getPlayers()) {
		    try {
			pl.getUpdateable(UpdateMap.class).updateStock(player, player.getResources());
		    } catch (Exception ex) {
			ex.printStackTrace();
		    }
		}
	    });
	}
	Scheduler.runAsyncdSync(runs);
    }

    public void updateCards() {
	List<Runnable> runs = new ArrayList<>();
	for (Player pl : getPlayers()) {
	    runs.add(() -> {
		for (Player player : getPlayers()) {
		    try {
			pl.getUpdateable(UpdateMap.class).updateStock(player, player.getCards());
		    } catch (Exception ex) {
			ex.printStackTrace();
		    }
		}
	    });
	}
	Scheduler.runAsyncdSync(runs);
    }

    public void updateCosts() {
	List<Runnable> runs = new ArrayList<>();
	for (Player pl : getPlayers()) {
	    runs.add(() -> {
		try {
		    UpdateMap view = pl.getUpdateable(UpdateMap.class);
		    view.updateCardCosts(ServerCosts.DEVELOPMENT_CARD_COSTS);
		    view.updateCityCosts(ServerCosts.CITY_COSTS);
		    view.updateVillageCosts(ServerCosts.VILLAGE_COSTS);
		    view.updateStreetCosts(ServerCosts.STREET_COSTS);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    });
	}
	Scheduler.runAsyncdSync(runs);
    }

    public void updateTrades() {
	List<Runnable> runs = new ArrayList<>();
	for (Player pl : getPlayers()) {
	    runs.add(() -> {
		try {
		    pl.getUpdateable(UpdateMap.class).updateTrades(tradeController.getTrades());
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    });
	}
	Scheduler.runAsyncdSync(runs);
    }

    public void updateRound() {
	List<Runnable> runs = new ArrayList<>();
	for (Player pl : getPlayers()) {
	    runs.add(() -> {
		try {
		    pl.getUpdateable(UpdateMap.class).updateRound(getRound());
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    });
	}
	Scheduler.runAsyncdSync(runs);
    }

    public void updateState(TurnState state) {
	List<Runnable> runs = new ArrayList<>();
	for (Player pl : getPlayers()) {
	    runs.add(() -> {
		try {
		    pl.getUpdateable(UpdateMap.class).updateTurn(getTurn(), state);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    });
	}
	Scheduler.runAsyncdSync(runs);
    }

    public void updateTurn() {
	List<Runnable> runs = new ArrayList<>();
	for (Player pl : getPlayers()) {
	    runs.add(() -> {
		try {
		    pl.getUpdateable(UpdateMap.class).updatePlayerOrder(getPlayersOrded());
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    });
	}
	Scheduler.runAsyncdSync(runs);
    }

    public void openDicePane() {
	try {
	    for (Player pl : getPlayersOrded()) {
		if (pl.equals(getTurn())) {
		    continue;
		}
		try {
		    pl.getUpdateable(UpdateMap.class).openDicePane(false);
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		}
	    }
	    getTurn().getUpdateable(UpdateMap.class).openDicePane(true);
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    @Override
    public void openPausePane(Player requester) {
	try {
	    for (Player pl : getPlayers()) {
		try {
		    pl.getUpdateable(UpdateMap.class).openPausePane(false);
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		}
	    }
	    requester.getUpdateable(UpdateMap.class).openPausePane(true);
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    @Override
    public void closePausePane() throws RemoteException {
	List<Runnable> runs = new ArrayList<>();
	for (Player pl : getPlayers()) {
	    runs.add(() -> {
		try {
		    pl.getUpdateable(UpdateMap.class).closeOverlay();
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		}
	    });
	}
	Scheduler.runAsyncdSync(runs);
    }

    public void moveBanditModus() {
	// TODO: move rover modus
    }

    public void buildStreetModus(int streetsToBuild) {
	try {
	    Player who = getTurn();
	    who.addRemainingStreets(streetsToBuild);
	    UpdateMap view = who.getUpdateable(UpdateMap.class);
	    view.closeOverlay();
	    view.setSelectable(SelectState.STREET);
	    view.blockActions();
	    highlightStreet(who);
	    updateResources();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    public void buildVillageModus(int villageToBuild) {
	try {
	    Player who = getTurn();
	    who.addRemainingVillages(villageToBuild);
	    UpdateMap view = who.getUpdateable(UpdateMap.class);
	    view.closeOverlay();
	    view.setSelectable(SelectState.BUILDING);
	    highlightBuildings(who, BuildingType.VILLAGE);
	    updateResources();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    public void buildCityModus(int cityToBuild) {
	try {
	    Player who = getTurn();
	    who.addRemainingCitys(cityToBuild);
	    UpdateMap view = who.getUpdateable(UpdateMap.class);
	    view.closeOverlay();
	    view.setSelectable(SelectState.BUILDING);
	    highlightBuildings(who, BuildingType.CITY);
	    updateResources();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    @Override
    public void targetInvention(Player who, Resource resource) throws RemoteException {
	cardController.useInvention(who, resource);
    }

    @Override
    public void targetMonopoly(Player who, Resource resource) throws RemoteException {
	cardController.targetMonopoly(who, resource);
    }
}
