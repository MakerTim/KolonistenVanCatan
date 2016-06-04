package nl.groep4.kvc.server.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Map;

public class KolonistenVanCatan implements nl.groep4.kvc.common.interfaces.KolonistenVanCatan {

    private final List<Player> players = new ArrayList<>();
    private Map map;

    public KolonistenVanCatan(List<Player> players) {
	this.players.addAll(players);
	// TODO: map = new Map();
    }

    @Override
    public Map getMap() throws RemoteException {
	return map;
    }

    @Override
    public List<Player> getPlayers() throws RemoteException {
	return Collections.unmodifiableList(players);
    }

}
