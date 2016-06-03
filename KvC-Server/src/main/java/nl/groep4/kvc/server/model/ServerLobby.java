package nl.groep4.kvc.server.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdatableLobby;

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
    public Player registerPlayer(String username) {
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
	return pl;
    }

    @Override
    public void unregisterPlayer(Player pl) throws RemoteException {
	System.out.printf("Player %s has been logged off.\n", pl.getUsername());
	setColor(pl, null);
	players.remove(getServerPlayer(pl));
    }

    @Override
    public void startGame() {
	state = State.STARTING;
	players.stream().filter(pl -> pl.getUpdateable() instanceof UpdatableLobby).forEach(pl -> {
	    try {
		((UpdatableLobby) pl.getUpdateable()).start();
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	});
	// TODO Lobby#startGame - iets dat de client update krijgt van
	// open game scherm -> hier is de game
    }

    @Override
    public void loadSave(String safeFile) {
	// TODO Lobby#loadSafe
    }

    @Override
    public void setColor(Player clientPlayer, Color color) throws RemoteException {
	if (!players.stream().filter(pl -> pl.getColor() == color).findAny().isPresent()) {
	    Player player = getServerPlayer(clientPlayer);
	    player.setColor(color);
	    players.stream().filter(pl -> pl.getUpdateable() instanceof UpdatableLobby).forEach(pl -> {
		try {
		    ((UpdatableLobby) pl.getUpdateable()).updateColor(null, clientPlayer.getColor());
		    ((UpdatableLobby) pl.getUpdateable()).updateColor(player, color);
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		}
	    });
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
    public void registerView(Player pl, UpdatableLobby updateable) throws RemoteException {
	getServerPlayer(pl).registerUpdateable(updateable);
	updateable.setModel(this);
    }
}
