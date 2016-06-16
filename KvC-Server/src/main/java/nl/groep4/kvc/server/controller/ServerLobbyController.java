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
import nl.groep4.kvc.common.util.Scheduler;
import nl.groep4.kvc.server.model.ServerLobby;
import nl.groep4.kvc.server.model.ServerPlayer;

public class ServerLobbyController {

    private Lobby lobby;

    public ServerLobbyController(ServerLobby lobby) {
	this.lobby = lobby;
    }

    public void startGame() throws RemoteException {
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
	for (Player player : playersUnready) {
	    player.getUpdateable(UpdateLobby.class).close("nocolor");
	}
	KolonistenVanCatan kvc = (KolonistenVanCatan) UnicastRemoteObject
		.exportObject(new ServerKolonistenVanCatan(lobby.getPlayers()), 2);
	kvc.createMap();
	lobby.setState(State.IN_GAME);
	lobby.setGame(kvc);
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
	Scheduler.runSync(runners);
	Scheduler.runAsyncLater(() -> {
	    try {
		kvc.start();
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}, 1000L);
    }

    public Player registerPlayer(String username) throws RemoteException {
	switch (lobby.getState()) {
	case IN_GAME:
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
	}
	return null;
    }

    public void setColor(Player pl, Color newColor) throws RemoteException {
	cleanup();
	switch (lobby.getState()) {
	case IN_GAME:
	    pl.getUpdateable().popup("ingame");
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
	    Scheduler.runSync(runs);
	}
    }

    public boolean removePlayer(Player pl, boolean shouldRemove) throws RemoteException {
	lobby.setColor(pl, null);
	if (shouldRemove) {
	    return lobby.getPlayers().remove(pl);
	}
	return true;
    }

    public void cleanup() throws RemoteException {
	Iterator<Player> playerIT = lobby.getPlayers().iterator();
	while (playerIT.hasNext()) {
	    Player pl = playerIT.next();
	    try {
		pl.getUpdateable().testConnection();
	    } catch (RemoteException ex) {
		try {
		    playerIT.remove();
		    removePlayer(pl, false);
		    System.out.printf("Player %s has been removed by disconnecting.\n", pl.getUsername());
		} catch (Exception subex) {
		    System.err.println(subex);
		}
	    } catch (NullPointerException npe) {
		System.out.printf("No updateable for %s\n", pl.getUsername());
	    }
	}
    }

}
