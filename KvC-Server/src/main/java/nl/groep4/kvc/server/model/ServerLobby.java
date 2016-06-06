package nl.groep4.kvc.server.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.client.controller.ClientRefrence;
import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateLobby;
import nl.groep4.kvc.common.util.Scheduler;

public class ServerLobby implements Lobby {

    protected List<Player> players = new ArrayList<>();

    @Override
    public Player registerPlayer(String player) throws RemoteException {
	Player pl = (Player) UnicastRemoteObject.exportObject(new ServerPlayer(player), ClientRefrence.getPort());
	System.out.printf("Player %s has joined!\n", pl.getUsername());
	players.add(pl);
	return pl;
    }

    public void update(String s) {
	players.forEach(pl -> {
	    try {
		pl.getUpdateable().close(s);
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	});
    }

    @Override
    public List<Player> getPlayers() throws RemoteException {
	return players;
    }

    @Override
    public void disconnect(Player pl) throws RemoteException {
	System.out.printf("Player %s has %s logged off.\n", pl.getUsername(),
		players.remove(pl) ? "succesfully" : "unsuccesfully");
	setColor(pl, null);
    }

    @Override
    public void setColor(Player pl, Color newColor) throws RemoteException {
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
		    ex.printStackTrace();
		    return false;
		}
	    }).forEach(player -> {
		Scheduler.runAsync(() -> {
		    try {
			((UpdateLobby) player.getUpdateable()).updatePlayerColor(null, color);
			((UpdateLobby) player.getUpdateable()).updatePlayerColor(pl, newColor);
		    } catch (RemoteException ex) {
			ex.printStackTrace();
		    }
		});
	    });
	}
    }

    @Override
    public void startGame() throws RemoteException {
	// TODO Auto-generated method stub
    }
}
