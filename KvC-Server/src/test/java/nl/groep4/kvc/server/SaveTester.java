package nl.groep4.kvc.server;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.server.controller.ServerKolonistenVanCatan;
import nl.groep4.kvc.server.controller.ServerLobbyController;
import nl.groep4.kvc.server.model.ServerLobby;
import nl.groep4.kvc.server.util.LoadHelper;
import nl.groep4.kvc.server.util.SaveHelper;

public class SaveTester {

    private ServerKolonistenVanCatan skvc;

    @Before
    public void setup() throws RemoteException {
	ServerLobby lobby = new ServerLobby();
	ServerLobbyController lobbyController = new ServerLobbyController(lobby);
	lobby.setController(lobbyController);
	ServerStarter.setLobby(lobby);
	Player makertim = ServerStarter.getLobby().registerPlayer("MakerTim");
	makertim.setColor(Color.ORANGE);
	ServerStarter.getLobby().registerPlayer("Luc").setColor(Color.GREEN);
	ServerStarter.getLobby().registerPlayer("Lisa").setColor(Color.RED);

	skvc = new ServerKolonistenVanCatan(ServerStarter.getLobby().getPlayers());
	skvc.createMap();
	skvc.start();

	for (Direction direction : Direction.values()) {
	    skvc.getMap().getTile(0, 0).getStreet(direction).setOwner(makertim);
	}
    }

    @Test
    public void testSave() throws RemoteException {
	String save = SaveHelper.toSaveFile(skvc);
	System.out.println(save);
	ServerKolonistenVanCatan skvc = LoadHelper.loadFromSave(save);
	System.out.println(skvc);
    }

}
