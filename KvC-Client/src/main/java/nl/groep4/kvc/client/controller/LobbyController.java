package nl.groep4.kvc.client.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import nl.groep4.kvc.client.model.Player;
import nl.groep4.kvc.client.view.ExceptionDialog;
import nl.groep4.kvc.client.view.ViewLobby;
import nl.groep4.kvc.common.KvCStaticNaming;
import nl.groep4.kvc.common.Lobby;

public final class LobbyController {

    private LobbyController() {
    }

    public static boolean connect(ViewLobby viewLobby) {
	String ip = "";
	int port = 1099;
	String username = "";
	try {
	    ip = viewLobby.getIpInput();
	    port = viewLobby.getPortInput();
	    username = viewLobby.getUsernameInput().trim();

	    /* Check if valid IP */
	    InetAddress.getByName(ip);
	    /* Check if name is empty */
	    if (username.isEmpty()) {
		throw new IllegalArgumentException("Username cannot be empty.");
	    }

	    /* Connect to Server */
	    Registry registry = LocateRegistry.getRegistry(ip, port);
	    Lobby lobby = (Lobby) registry.lookup(KvCStaticNaming.LOBBY_KEY);
	    /* Register self */
	    lobby.registerPlayer(new Player(username));
	} catch (UnknownHostException ex) {
	    ExceptionDialog.warning("IP is not a valid ip address", "No valid IP",
		    String.format("'%s' is not a valid ip address", ip));
	    return false;
	} catch (NumberFormatException ex) {
	    ExceptionDialog.warning("Port should be a number", "No valid PORT", "Port should be a number");
	    return false;
	} catch (IllegalArgumentException ex) {
	    ExceptionDialog.warning(ex.getMessage(), ex.getMessage(), "");
	    return false;
	} catch (RemoteException ex) {
	    ExceptionDialog.error(ex);
	    // ExceptionDialog.warning("Server not found",
	    // "The server you are looking for is not found",
	    // "There is no server found on this ip/port combo.");
	    return false;
	} catch (Exception ex) {
	    ExceptionDialog.error(ex);
	    return false;
	}
	return true;
    }

}
