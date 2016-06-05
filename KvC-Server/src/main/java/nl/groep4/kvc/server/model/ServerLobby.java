package nl.groep4.kvc.server.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Updatable;

public class ServerLobby implements Lobby {

    protected List<Player> players = new ArrayList<>();

    @Override
    public Player registerPlayer(String player) throws RemoteException {
	Player pl = new ServerPlayer(player);
	players.add(pl);
	return pl;
    }

    @Override
    public void registerUpdatable(Player pl, Updatable<?> update) throws RemoteException {
	players.stream().filter(player -> pl.equals(player)).forEach(player -> player.registerUpdateable(update));
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
}
