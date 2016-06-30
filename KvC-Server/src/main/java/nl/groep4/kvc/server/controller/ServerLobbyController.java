package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Lobby.State;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateLobby;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.common.util.Scheduler;
import nl.groep4.kvc.server.model.ServerLobby;
import nl.groep4.kvc.server.model.ServerPlayer;
import nl.groep4.kvc.server.util.ConnectionUtil;

/**
 * Starts the game and registers player.
 * 
 * @author Tim
 * @version 1.0
 */
public class ServerLobbyController {

    private Lobby lobby;

    /**
     * Controls server lobby.
     * 
     * @param lobby
     *            Lobby value.
     */
    public ServerLobbyController(ServerLobby lobby) {
	this.lobby = lobby;
    }

    /**
     * Start the game.
     * 
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void startGame() throws RemoteException {
	if (lobby.getState() != State.LOBBY) {
	    lobby.setState(State.STARTING);
	}
	List<Player> playersReady = new ArrayList<>();
	List<Player> playersUnready = new ArrayList<>();
	for (Player player : lobby.getPlayers()) {
	    if (player.getColor() != null) {
		playersReady.add(player);
	    } else {
		playersUnready.add(player);
	    }
	}
	if (playersReady.size() < 2) {
	    for (Player pl : lobby.getPlayers()) {
		pl.getUpdateable().popup("noplayers");
	    }
	    return;
	}
	List<Runnable> runs = new ArrayList<>();
	for (Player player : playersUnready) {
	    runs.add(() -> {
		try {
		    lobby.getPlayers().remove(player);
		    player.getUpdateable(UpdateLobby.class).close("nocolor");
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    });
	}
	Scheduler.runAsyncdSync(runs);
	KolonistenVanCatan kvc = new ServerKolonistenVanCatan(lobby.getPlayers());
	kvc.createMap();
	startupGame(kvc);
    }

    /**
     * When the game starts, this methods starts it.
     * 
     * @param kvc
     *            The game that will be played.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void startupGame(KolonistenVanCatan kvc) throws RemoteException {
	KolonistenVanCatan kvcSkeleton = (KolonistenVanCatan) UnicastRemoteObject.exportObject(kvc, 0);
	lobby.setState(State.IN_GAME);
	lobby.setGame(kvcSkeleton);
	List<Runnable> runners = new ArrayList<>();
	for (Player pl : lobby.getPlayers()) {
	    runners.add(() -> {
		try {
		    pl.getUpdateable(UpdateLobby.class).start();
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    });
	}
	Scheduler.runAsyncdSync(runners);
	Scheduler.runAsync(() -> {
	    boolean hasCastException;
	    do {
		hasCastException = false;
		try {
		    Thread.sleep(100L);
		    for (Player pl : kvcSkeleton.getPlayers()) {
			UpdateMap updateMap = pl.getUpdateable(UpdateMap.class);
			updateMap.testConnection();
		    }
		} catch (Exception ex) {
		    hasCastException = true;
		    break;
		}
	    } while (hasCastException);
	    try {
		kvcSkeleton.start();
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	});
    }

    /**
     * Registers player.
     * 
     * @param username
     *            Name of player.
     * @return Null.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public Player registerPlayer(String username) throws RemoteException {
	switch (lobby.getState()) {
	case IN_GAME:
	case STARTING:
	    for (Player kvcPlayer : lobby.getGame().getPlayers()) {
		if (kvcPlayer.getUsername().equals(username)) {
		    return kvcPlayer;
		}
	    }
	    break;
	case LOBBY:
	    Player pl = (Player) UnicastRemoteObject.exportObject(new ServerPlayer(username), 0);
	    Iterator<Player> playersIT = lobby.getPlayers().iterator();
	    while (playersIT.hasNext()) {
		Player player = playersIT.next();
		try {
		    if (player.getUsername().equals(pl.getUsername())) {
			System.out.printf("Kicking player %s for dube name\n", pl.getUsername());
			player.getUpdateable().close("other");
			playersIT.remove();
		    }
		} catch (RemoteException ex) {
		}
	    }
	    lobby.getPlayers().add(pl);
	    System.out.printf("Player %s has joined!\n", pl.getUsername());
	    return pl;
	default:
	    break;
	}
	return null;
    }

    /**
     * Sets color.
     * 
     * @param pl
     *            Player which color to set.
     * @param newColor
     *            New color to set for player.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void setColor(Player pl, Color newColor) throws RemoteException {
	ConnectionUtil.cleanup(lobby.getPlayers(), player -> {
	    try {
		removePlayer(player, false);
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	});
	switch (lobby.getState()) {
	case STARTING:
	    pl.getUpdateable().popup("ingame");
	    break;
	case IN_GAME:
	    break;
	case LOBBY:
	    boolean freeColor = true;
	    for (Player player : lobby.getPlayers()) {
		if (player.getColor() == newColor && newColor != null) {
		    freeColor = false;
		}
	    }
	    if (!freeColor) {
		break;
	    }
	    System.out.printf("\t%s [%s] - new color = %s\n", pl.getUsername(), pl.getColor(), newColor);
	    Color color = pl.getColor();
	    pl.setColor(newColor);
	    List<Runnable> runs = new ArrayList<>();
	    for (Player player : lobby.getPlayers()) {
		if (player.getUpdateable() instanceof UpdateLobby) {
		    runs.add(() -> {
			try {
			    player.getUpdateable(UpdateLobby.class).updatePlayerColor(null, color);
			    player.getUpdateable(UpdateLobby.class).updatePlayerColor(pl, newColor);
			} catch (RemoteException ex) {
			}
		    });
		}
	    }
	    Scheduler.runAsyncdSync(runs);
	}
    }

    /**
     * Removes player.
     * 
     * @param pl
     *            Player to remove from the lobby.
     * @param shouldRemove
     *            If true player will be removed.
     * @return Returns true.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public boolean removePlayer(Player pl, boolean shouldRemove) throws RemoteException {
	lobby.setColor(pl, null);
	if (shouldRemove) {
	    return lobby.getPlayers().remove(pl);
	}
	return true;
    }
}
