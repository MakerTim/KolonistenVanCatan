package nl.groep4.kvc.server.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdatableLobby;
import nl.groep4.kvc.common.util.Scheduler;

/**
 * The lobby for the game
 * 
 * @version 1.0
 * @author Tim
 **/
public class ServerLobby implements Lobby {

    private final List<Player> players = new ArrayList<>();

    private State state = State.LOBBY;
    private KolonistenVanCatan game;

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
	    try {
		pl.getUpdateable().close("other");
	    } catch (Exception ex) {
	    }
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
	players.stream().filter(pl -> pl.getColor() == null).forEach(pl -> {
	    try {
		((UpdatableLobby) pl.getUpdateable()).close("nocolor");
	    } catch (Exception ex) {
	    }
	});
	players.stream().filter(pl -> pl.getUpdateable() instanceof UpdatableLobby).forEach(pl -> {
	    Scheduler.runAsync(() -> {
		try {
		    ((UpdatableLobby) pl.getUpdateable()).start();
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		}
	    });
	});
	state = State.STARTED;
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
		Scheduler.runAsync(() -> {
		    try {
			((UpdatableLobby) pl.getUpdateable()).updateColor(null, clientPlayer.getColor());
			((UpdatableLobby) pl.getUpdateable()).updateColor(player, color);
		    } catch (RemoteException ex) {
			pl.registerUpdateable(null);
		    }
		});
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
	pl = getServerPlayer(pl);
	pl.registerUpdateable(updateable);
	if (state.isAfterStart() && !game.getPlayers().contains(pl)) {
	    updateable.close("started");
	    return;
	}
	Scheduler.runAsync(() -> {
	    try {
		updateable.setModel(this);
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	});
    }
}
