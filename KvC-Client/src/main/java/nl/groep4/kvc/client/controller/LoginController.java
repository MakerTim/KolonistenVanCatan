package nl.groep4.kvc.client.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import nl.groep4.kvc.client.util.ExceptionManager;
import nl.groep4.kvc.client.view.ExceptionDialog;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.scene.SceneLobby;
import nl.groep4.kvc.client.view.scene.SceneLogin;
import nl.groep4.kvc.common.KvCStatics;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;

/**
 * Connects with the lobby if all requirements matches
 * 
 * @author Tim
 * @version 1.0
 */
public class LoginController implements Controller {

    private SceneLogin view;

    public LoginController(SceneLogin view) {
	this.view = view;
    }

    /**
     * checks if all input fields are correct
     * 
     * @return lobby key to connect with
     */
    public Lobby connect() {
	String ip = "";
	int port = 1099;
	String username = "";
	Lobby lobby = null;
	try {
	    ip = view.getIpInput();
	    port = view.getPortInput();
	    username = view.getUsernameInput().trim();

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
	    if (pl == null) {
		ExceptionDialog.warning("login.error.ingame");
		return null;
	    }
	    ClientRefrence.setThePlayer(pl);
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

    private static void openLobby(Lobby model, Player pl) throws RemoteException {
	switch (model.getState()) {
	case LOBBY:
	    SceneLobby view = new SceneLobby();
	    LobbyController controller = new LobbyController(model);
	    view.registerController(controller);
	    ViewMaster.setScene(view);
	    ClientRefrence.registerUpdateable(view);
	    try {
		view.setModel(model);
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	    break;
	case IN_GAME:
	case STARTING:
	    new LobbyController(model).reopenGame(pl);
	    // TODO: Resync all data!
	    break;
	default:
	    break;
	}

    }

}
