package nl.groep4.kvc.client.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import nl.groep4.kvc.client.util.ExceptionManager;
import nl.groep4.kvc.client.view.ExceptionDialog;
import nl.groep4.kvc.client.view.scene.SceneLobby;
import nl.groep4.kvc.client.view.scene.SceneLogin;
import nl.groep4.kvc.common.KvCStatics;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Updatable;

public class LoginController {

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
	    lobby = (Lobby) registry.lookup(KvCStatics.LOBBY_KEY);
	    /* Register self */
	    Player pl = lobby.registerPlayer(username);
	    PlayerRefrence.setThePlayer(pl);
	    openLobby(lobby, pl);
	} catch (UnknownHostException ex) {
	    ExceptionDialog.warning("login.error.novalidip");
	} catch (NumberFormatException ex) {
	    ExceptionDialog.warning("login.error.portnonumber");
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	} catch (Exception ex) {
	    ExceptionDialog.error(ex);
	}
	return lobby;
    }

    private static void openLobby(Lobby model, Player pl) {
	SceneLobby view = new SceneLobby();
	LobbyController controller = new LobbyController(model);
	view.registerController(controller);
	try {
	    pl.registerUpdateable((Updatable<Lobby>) UnicastRemoteObject.exportObject(view, KvCStatics.RMI_OBJ++));
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

}
