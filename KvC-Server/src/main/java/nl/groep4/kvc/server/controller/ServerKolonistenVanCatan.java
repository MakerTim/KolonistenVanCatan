package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.TurnState;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateMap;
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

    private final List<Player> players;
    private ServerMap map = new ServerMap();
    private int round;
    private int turn;

    public ServerKolonistenVanCatan(List<Player> players) {
	System.out.println("Starting game!");
	this.players = players;
	players.sort((pl1, pl2) -> {
	    return Integer.compare(pl1.hashCode(), pl2.hashCode());
	});
	System.out.println("\tRandomized players");
    }

    @Override
    public void start() {
	updateTurn();
	System.out.println("\tStarted game!");
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
	turn++;
	if (turn > players.size()) {
	    turn = 0;
	    nextRound();
	}
	updateTurn();
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
	Building building = map.getBuilding(coord);
	building.setOwner(newOwner);
	building.setBuildingType(type);
	update();
    }

    @Override
    public void placeStreet(Coordinate coord, Player newOwner) throws RemoteException {
	Street street = map.getStreet(coord);
	try {
	    street.setOwner(newOwner);
	} catch (Exception ex) {
	    System.err.println(ex);
	}
	update();
    }

    private void updateTurn() {
	try {
	    UpdateMap view = getPlayersOrded().get(0).getUpdateable(UpdateMap.class);
	    view.unblockActions();
	    view.openDicePane(true);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	for (int i = 1; i < getPlayersOrded().size(); i++) {
	    try {
		UpdateMap view = getPlayersOrded().get(i).getUpdateable(UpdateMap.class);
		view.blockActions();
		view.openDicePane(false);
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}
	for (Player pl : getPlayers()) {
	    try {
		UpdateMap view = pl.getUpdateable(UpdateMap.class);
		view.updateTurn(getPlayersOrded().get(0), TurnState.THROWING_DICE);
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}
    }

}
