package nl.groep4.kvc.client.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import nl.groep4.kvc.client.util.ExceptionManager;
import nl.groep4.kvc.client.view.ExceptionDialog;
import nl.groep4.kvc.client.view.scene.SceneLogin;
import nl.groep4.kvc.common.KvCStaticNaming;
import nl.groep4.kvc.common.interfaces.Lobby;

/**
 * Controls the form of the lobby and returns error messages
 * 
 * @author Tim
 * @version 1.0
 */
public final class LoginController {

    private LoginController() {
    }

    /**
     * 
     * 
     * @param sceneLogin
     * @return gets the lobby
     */
    public static Lobby connect(SceneLogin sceneLogin) {
	String ip = "";
	int port = 1099;
	String username = "";
	Lobby lobby = null;
	try {
	    ip = sceneLogin.getIpInput();
	    port = sceneLogin.getPortInput();
	    username = sceneLogin.getUsernameInput().trim();

	    /* Check if valid IP */
	    if (InetAddress.getByName(ip) == null) {
		throw new UnknownHostException();
	    }
	    /* Check if name is empty */
	    if (username.isEmpty()) {
		ExceptionDialog.warning("login.error.nousername");
		return null;
	    }

	    /* Connect to Server */
	    Registry registry = LocateRegistry.getRegistry(ip, port);
	    lobby = (Lobby) registry.lookup(KvCStaticNaming.LOBBY_KEY);
	    /* Register self */
	    ConnectionController.setLobby(lobby);
	    lobby.registerPlayer(username);
	    ConnectionController.setThePlayer(username);
	} catch (UnknownHostException ex) {
	    ExceptionDialog.warning("login.error.novalidip");
	    return null;
	} catch (NumberFormatException ex) {
	    ExceptionDialog.warning("login.error.portnonumber");
	    return null;
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	    return null;
	} catch (Exception ex) {
	    ExceptionDialog.error(ex);
	    return null;
	}
	return lobby;
    }

}
