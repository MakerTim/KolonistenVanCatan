package nl.groep4.kvc.server.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.KvCStatics;
import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;

public class ServerLobby implements Lobby {

    protected List<Player> players = new ArrayList<>();

    @Override
    public Player registerPlayer(String player) throws RemoteException {
	Player pl = (Player) UnicastRemoteObject.exportObject(new ServerPlayer(player), KvCStatics.RMI_OBJ++);
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
	players.remove(pl);
    }

    @Override
    public void setColor(Player pl, Color newColor) throws RemoteException {
	pl.setColor(newColor);
    }

    @Override
    public void startGame() throws RemoteException {
	// TODO Auto-generated method stub
    }
}
