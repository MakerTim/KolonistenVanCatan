package nl.groep4.kvc.server.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.GsonBuilder;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateLobby;
import nl.groep4.kvc.common.util.Scheduler;

public class ServerLobby implements Lobby {

    protected final List<Player> players = new ArrayList<>();
    private KolonistenVanCatan kvc;
    private State state = State.LOBBY;

    @Override
    public Player registerPlayer(String playerName) throws RemoteException {
	switch (state) {
	case IN_GAME:
	    for (Player kvcPlayer : kvc.getPlayers()) {
		if (kvcPlayer.getUsername().equals(playerName)) {
		    return kvcPlayer;
		}
	    }
	    break;
	case LOBBY:
	    Player pl = (Player) UnicastRemoteObject.exportObject(new ServerPlayer(playerName), 0);
	    Iterator<Player> playersIT = players.iterator();
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
	    players.add(pl);
	    System.out.printf("Player %s has joined!\n", pl.getUsername());
	    return pl;
	}
	return null;
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
	cleanup();
	switch (state) {
	case IN_GAME:
	    pl.getUpdateable().popup("ingame");
	    break;
	case LOBBY:
	    boolean freeColor = true;
	    for (Player player : players) {
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
	    for (Player player : players) {
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

    private boolean removePlayer(Player pl, boolean shouldRemove) throws RemoteException {
	setColor(pl, null);
	if (shouldRemove) {
	    return players.remove(pl);
	}
	return true;
    }

    @Override
    public void cleanup() throws RemoteException {
	Iterator<Player> playerIT = players.iterator();
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
	    }
	}
    }

    @Override
    public void startGame() throws RemoteException {
	kvc = (KolonistenVanCatan) UnicastRemoteObject.exportObject(new ServerKolonistenVanCatan(players), 2);
	kvc.createMap();
	state = State.IN_GAME;
	for (Player pl : getPlayers()) {
	    new Thread(() -> {
		try {
		    pl.getUpdateable(UpdateLobby.class).start(kvc);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    }).start();
	}
    }

    @Override
    public void loadSave(String save) throws RemoteException {
	// TODO: ServerLobby#LoadSave Needs to be looked in
	kvc = new GsonBuilder().create().fromJson(save, ServerKolonistenVanCatan.class);
    }
}
