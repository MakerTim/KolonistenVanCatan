package nl.groep4.kvc.server.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Updatable;
import nl.groep4.kvc.common.interfaces.UpdateLobby;
import nl.groep4.kvc.common.util.Scheduler;

/**
 * Instance of Lobby
 * 
 * @author Tim
 * @version 1.0
 */
public class ServerLobby implements Lobby {

    protected final List<Player> players = new ArrayList<>();
    private ServerKolonistenVanCatan kvc;
    private State state = State.LOBBY;

    @Override
    public Player registerPlayer(String playerName) throws RemoteException {
	Player pl = (Player) UnicastRemoteObject.exportObject(new ServerPlayer(playerName), 0);
	for (Player player : players) {
	    try {
		if (player.getUsername().equals(pl.getUsername()) && player.getUpdateable() instanceof UpdateLobby) {
		    System.out.printf("Kicking player %s for dube name\n", pl.getUsername());
		    ((UpdateLobby) player.getUpdateable()).close("other");
		}
	    } catch (RemoteException ex) {
	    }
	}
	players.add(pl);
	System.out.printf("Player %s has joined!\n", pl.getUsername());
	return pl;
    }

    @Override
    public List<Player> getPlayers() throws RemoteException {
	return players;
    }

    @Override
    public void disconnect(Player pl) throws RemoteException {
	boolean removed = removePlayer(pl, true);
	System.out.printf("Player %s has %s logged off.\n", pl.getUsername(),
		removed ? "succesfully" : "unsuccesfully");
    }

    @Override
    public void setColor(Player pl, Color newColor) throws RemoteException {
	Scheduler.runAsync(() -> {
	    cleanup();
	});
	switch (state) {
	case IN_GAME:
	    pl.getUpdateable().popup("ingame");
	    break;
	case LOBBY:
	    if (!players.stream().filter(player -> {
		try {
		    return player.getColor() == newColor && newColor != null;
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		    return false;
		}
	    }).findAny().isPresent()) {
		System.out.printf("\t%s [%s] - new color = %s\n", pl.getUsername(), pl.getColor(), newColor);
		Color color = pl.getColor();
		pl.setColor(newColor);
		players.stream().filter(player -> {
		    try {
			return player.getUpdateable() instanceof UpdateLobby;
		    } catch (RemoteException ex) {
			return false;
		    }
		}).forEach(player -> {
		    Scheduler.runAsync(() -> {
			try {
			    ((UpdateLobby) player.getUpdateable()).updatePlayerColor(null, color);
			    ((UpdateLobby) player.getUpdateable()).updatePlayerColor(pl, newColor);
			} catch (RemoteException ex) {
			}
		    });
		});
		break;
	    }
	}
    }

    private boolean removePlayer(Player pl, boolean shouldRemove) throws RemoteException {
	setColor(pl, null);
	if (!shouldRemove) {
	    return players.remove(pl);
	}
	return true;
    }

    private void cleanup() {
	Iterator<Player> playerIT = players.iterator();
	while (playerIT.hasNext()) {
	    Player pl = playerIT.next();
	    try {
		pl.getUpdateable().toString();
	    } catch (Exception ex) {
		try {
		    removePlayer(pl, false);
		    playerIT.remove();
		    System.out.printf("Player %s has been removed by disconnecting.\n", pl.getUsername());
		} catch (Exception subex) {
		    System.out.println(subex);
		}
	    }
	}
    }

    @Override
    public void startGame() throws RemoteException {
	kvc = new ServerKolonistenVanCatan();
	kvc.createMap();
	state = State.IN_GAME;
	for (Player pl : getPlayers()) {
	    new Thread(() -> {
		try {
		    Updatable<?> view = pl.getUpdateable();
		    if (view instanceof UpdateLobby) {
			((UpdateLobby) view).start(kvc.getMap());
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    }).start();
	}
    }

    @Override
    public void loadSave(String save) throws RemoteException {
	// TODO LoadSave
    }
}
