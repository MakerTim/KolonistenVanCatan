package nl.groep4.kvc.server.model;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.Lobby;
import nl.groep4.kvc.common.Player;
import nl.groep4.kvc.common.enumeration.Color;

public class ServerLobby implements Lobby {

	private final List<Player> players = new ArrayList<>();

	@Override
	public List<Player> getConnectedPlayers() {
		return players;
	}

	@Override
	public Player registerPlayer(Player pl) {
		Player ret = null;
		for (Player player : players) {
			if (pl.getUsername() == player.getUsername()) {
				ret = player;
			}
		}
		if (ret == null) {
			players.add(ret = pl);
			// TODO: ensure no dube color
		}
		return ret;
	}

	@Override
	public void startSpel() {
		// TODO
	}

	@Override
	public void loadSafe() {
		// TODO
	}

	@Override
	public void setColor(Player pl, Color color) {
		// TODO ensure no dube color
	}

	@Override
	public void update() {
	}

}
