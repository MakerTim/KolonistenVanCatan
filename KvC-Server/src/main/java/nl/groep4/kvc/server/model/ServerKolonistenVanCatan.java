package nl.groep4.kvc.server.model;

import java.util.List;

import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.server.model.map.ServerMap;

public class ServerKolonistenVanCatan implements KolonistenVanCatan {

    private final List<Player> players;
    private ServerMap map;
    private int round;

    public ServerKolonistenVanCatan(List<Player> players) {
	this.players = players;
    }

    @Override
    public Map getMap() {
	return map;
    }

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

}
