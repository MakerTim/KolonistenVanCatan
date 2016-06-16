package nl.groep4.kvc.server.util;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import nl.groep4.kvc.common.interfaces.Player;

public class ConnectionUtil {

    public static void cleanup(List<Player> players, Consumer<Player> onRemove) throws RemoteException {
	Iterator<Player> playerIT = players.iterator();
	while (playerIT.hasNext()) {
	    Player pl = playerIT.next();
	    try {
		pl.getUpdateable().testConnection();
	    } catch (RemoteException ex) {
		try {
		    playerIT.remove();
		    onRemove.accept(pl);
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