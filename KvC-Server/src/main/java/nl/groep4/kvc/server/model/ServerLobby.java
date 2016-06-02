package nl.groep4.kvc.server.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nl.groep4.kvc.common.Lobby;
import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Updatable;
import nl.groep4.kvc.common.Player;

/**
 * The lobby for the game
 * 
 * @version 1.0
 * @author Tim
 **/
public class ServerLobby implements Lobby {

    private final List<Updatable<Lobby>> views = new ArrayList<>();
    private final List<Player> players = new ArrayList<>();

    @Override
    public List<Player> getConnectedPlayers() {
	return players;
    }

    @Override
    public void registerPlayer(String username) {
	Optional<Player> existingPlayer = players.stream().filter(player -> username.equals(player.getUsername()))
		.findFirst();
	if (existingPlayer.isPresent()) {
	    System.out.printf("Player %s reconnected.\n", existingPlayer.get().getUsername());
	} else {
	    Player pl = new nl.groep4.kvc.server.model.Player(username);
	    players.add(pl);
	    System.out.printf("Player %s has connected.\n", pl.getUsername());
	}
	try {
	    update();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    @Override
    public void unregisterPlayer(Player pl) throws RemoteException {
	System.out.printf("Player %s has been logged off.\n", pl.getUsername());
	players.remove(getServerPlayer(pl));
    }

    @Override
    public void startGame() {
	// TODO Lobby#StartGame
    }

    @Override
    public void loadSafe(String safeFile) {
	// TODO Lobby#loadSafe
    }

    @Override
    public void setColor(Player player, Color color) {
	if (!players.stream().filter(pl -> pl.getColor() == color).findAny().isPresent()) {
	    player = getServerPlayer(player);
	    player.setColor(color);
	    try {
		update();
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	}
    }

    @Override
    public List<Updatable<Lobby>> getUpdatable() throws RemoteException {
	return views;
    }

    @Override
    public void registerUpdateable(Updatable<Lobby> updateable) throws RemoteException {
	views.add(updateable);
    }

    private Player getServerPlayer(Player player) {
	return players.stream().filter(pl -> pl.equals(player)).findAny().orElse(player);
    }
}
