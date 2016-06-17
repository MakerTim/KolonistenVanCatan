package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.GameState;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Throw;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.server.model.map.ServerMap;

/**
 * Instance of KolonistenVanCatan
 * 
 * @author Tim
 * @version 1.0
 */
public class ServerKolonistenVanCatan implements KolonistenVanCatan {

    private ServerTurnController turnController;

    private final List<Player> players;
    private ServerMap map = new ServerMap();
    private int round = -2;
    private int turn = -1;
    private Throw lastThrow;
    private GameState state;

    public ServerKolonistenVanCatan(List<Player> players) {
	System.out.println("Starting game!");
	this.players = players;
	turnController = new ServerTurnController(this);
	players.sort((pl1, pl2) -> {
	    return Integer.compare(pl1.hashCode(), pl2.hashCode());
	});
	System.out.println("\tRandomized players");
    }

    @Override
    public void start() {
	System.out.println("\tStarted game!");
	state = GameState.INIT;
	nextTurn();
    }

    @Override
    public Map getMap() {
	return map;
    }

    /**
     * Creates the map
     */
    @Override
    public void createMap() {
	map.createMap();
    }

    @Override
    public GameState getState() {
	return this.state;
    }

    @Override
    public int getRound() {
	return round;
    }

    @Override
    public void nextRound() {
	round++;
    }

    @Override
    public List<Player> getPlayers() {
	return players;
    }

    @Override
    public void nextTurn() {
	if (turn++ >= players.size() - 1) {
	    turn = 0;
	    nextRound();
	    if (state == GameState.INIT && round == 0) {
		state = GameState.IN_GAME;
	    }
	}
	turnController.fixButtons();
	switch (state) {
	case END:
	    break;
	case INIT:
	    turnController.initTurnBuilding();
	    break;
	case IN_GAME:
	    turnController.onTurn();
	    break;
	}
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
    public void placeBuilding(Coordinate coord, Player newOwner, BuildingType type) throws RemoteException {
	if (newOwner.equals(getTurn())) {
	    if (newOwner.getRemainingBuidlings() > 0) {
		Building building = map.getBuilding(coord);
		building.setOwner(newOwner);
		building.setBuildingType(type);
		update();
		if (state == GameState.INIT) {
		    turnController.initTurnStreet(building);
		}
	    } else {
		newOwner.getUpdateable().popup("nobuilding");
	    }
	} else {
	    newOwner.getUpdateable().popup("noturn");
	}
    }

    @Override
    public void placeStreet(Coordinate coord, Player newOwner) throws RemoteException {
	if (newOwner.equals(getTurn())) {
	    if (newOwner.getRemainingStreets() > 0) {
		Street street = map.getStreet(coord);
		street.setOwner(newOwner);
		update();
		if (state == GameState.INIT) {
		    nextTurn();
		}
	    } else {
		newOwner.getUpdateable().popup("nostreet");
	    }
	} else {
	    newOwner.getUpdateable().popup("noturn");
	}
    }

    @Override
    public void throwDices() throws RemoteException {
	ServerThrowController diceController = new ServerThrowController(this);
	lastThrow = diceController.getThrow();
	diceController.updateThrow();
    }

    @Override
    public void distrube() throws RemoteException {
	lastThrow.toString();
	// FIXME: Distribute
    }

}
