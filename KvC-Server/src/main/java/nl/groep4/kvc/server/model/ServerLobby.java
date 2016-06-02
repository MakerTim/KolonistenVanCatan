package nl.groep4.kvc.server.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import nl.groep4.kvc.common.Lobby;
import nl.groep4.kvc.common.Player;
import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Updatable;

/**
 * The lobby for the game
 * 
 * @version 1.0
 * @author Tim
 **/
public class ServerLobby implements Lobby {

    private final List<Player> players = new ArrayList<>();

    private State state = State.LOBBY;

    @Override
    public List<Player> getConnectedPlayers() {
	return players;
    }

    @Override
    public void registerPlayer(String username) {
	Optional<Player> existingPlayer = players.stream().filter(player -> username.equals(player.getUsername()))
		.findFirst();
	Player pl;
	if (existingPlayer.isPresent()) {
	    pl = existingPlayer.get();
	    System.out.printf("Player %s reconnected.\n", pl.getUsername());
	} else {
	    pl = new nl.groep4.kvc.server.model.Player(username);
	    players.add(pl);
	    System.out.printf("Player %s has connected.\n", pl.getUsername());
	}
    }

    @Override
    public void unregisterPlayer(Player pl) {
	System.out.printf("Player %s has been logged off.\n", pl.getUsername());
	players.remove(getServerPlayer(pl));
	try {
	    update();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    @Override
    public void startGame() {
	state = State.STARTING;
	try {
	    update();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
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
    public void update() throws RemoteException {
	for (Iterator<Player> playerIt = getConnectedPlayers().iterator(); playerIt.hasNext();) {
	    Player pl = playerIt.next();
	    try {
		Updatable<Lobby> updatable = (Updatable<Lobby>) pl.getUpdateable();
		(updatable).update(this);
	    } catch (NullPointerException ex) {
		ex.printStackTrace();
	    } catch (Exception ex) {
		System.out.printf("%s has been kicked. %s\n", pl.getUsername(), ex.toString());
		playerIt.remove();
	    }
	}
	if (!state.isStarting()) {
	    if (players.stream().filter(pl -> pl.getColor() != null).count() >= 6) {
		state = State.LOBBY_FULL;
	    } else {
		state = State.LOBBY;
	    }
	} else {

	}
    }

    @Override
    public Player getServerPlayer(Player player) {
	return players.stream().filter(pl -> pl.equals(player)).findAny().orElse(player);
    }

    @Override
    public State getState() {
	return state;
    }

    @Override
    public void registerView(Player pl, Updatable<Lobby> updateable) {
	getServerPlayer(pl).registerUpdateable(updateable);
    }
}
