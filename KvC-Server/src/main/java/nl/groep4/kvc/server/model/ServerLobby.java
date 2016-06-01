package nl.groep4.kvc.server.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	Optional<Player> existingPlayer = players.stream().filter(player -> pl.getUsername() == player.getUsername())
		.findFirst();
	if (existingPlayer.isPresent()) {
	    System.out.printf("Player %s reconnected.\n", pl.getUsername());
	    return existingPlayer.get();
	} else {
	    players.add(pl);
	    System.out.printf("Player %s has connected.\n", pl.getUsername());
	    return pl;
	}
    }

    @Override
    public void unregisterPlayer(Player pl) throws RemoteException {
	System.out.printf("Player %s has been logged off.\n", pl.getUsername());
	players.remove(pl);
    }

    @Override
    public void startGame() {
	// TODO
    }

    @Override
    public void loadSafe(String safeFile) {
	// TODO
    }

    @Override
    public void setColor(Player player, Color color) {
	boolean alreadyUsed = players.stream().filter(pl -> pl.getColor() == color).count() > 0;
	if (!alreadyUsed) {
	    player.setColor(color);
	}
    }

}
