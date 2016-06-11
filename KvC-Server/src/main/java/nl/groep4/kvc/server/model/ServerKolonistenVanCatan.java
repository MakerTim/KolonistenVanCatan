package nl.groep4.kvc.server.model;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.server.model.map.ServerMap;

public class ServerKolonistenVanCatan implements KolonistenVanCatan {

    private final List<Player> players;
    private ServerMap map;
    private int round;
    private int turn;

    public ServerKolonistenVanCatan(List<Player> players) {
	this.players = players;
    }

    @Override
    public Map getMap() {
	return map;
    }

    @Override
    public void createMap() {
	map = new ServerMap();
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
    }

    @Override
    public List<Player> getPlayersOrded() {
	List<Player> orded = new ArrayList<>();
	for (int i = 0; i < players.size(); i++) {
	    orded.add(players.get((i + turn) % players.size()));
	}
	return orded;
    }

}
