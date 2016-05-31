package nl.groep4.kvc.server.model;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.Lobby;
import nl.groep4.kvc.common.Player;
import nl.groep4.kvc.common.enumeration.Color;

/**
 * The lobby for the game
 * 
 * @version 1.0
 * @author Tim
 **/
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
	    System.out.printf("Player %s has connected.\n", pl.getUsername());
	} else {
	    System.out.printf("Player %s reconnected.\n", pl.getUsername());

	}
	return ret;
    }

    @Override
    public void startSpel() {
	// TODO
    }

    @Override
    public void loadSafe(String safeFile) {
	// TODO
    }

    @Override
    public void setColor(Player player, Color color) {
	boolean alreadyUsed = false;
	for (Player pl : players) {
	    if (pl.getColor() == color) {
		alreadyUsed = true;
	    }
	}
	if (!alreadyUsed) {
	    player.setColor(color);
	}
	update();
    }

    @Override
    public void update() {

    }

}
