package nl.groep4.kvc.server;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.TileType;
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
	Map<Resource, Integer> resources = new HashMap<>();
	resources.put(Resource.BRICK, 1);
	makertim.giveResource(Resource.BRICK, 10);
	skvc.addTrade(makertim, resources, resources);
	System.out.println(skvc.getTrades().size());
	for (Direction direction : Direction.values()) {
	    skvc.getMap().getTile(0, 0).getStreet(direction).setOwner(makertim);
	}
    }

    @Test
    public void testSave() throws RemoteException {
	String save = SaveHelper.toSaveFile(skvc);
	skvc = LoadHelper.loadFromSave(save);
	assertEquals(52, skvc.getMap().getTiles().size());
	assertEquals(TileType.DESERT, skvc.getMap().getTile(0, -2).getType());
	assertEquals(TileType.DESERT, skvc.getMap().getTile(0, 1).getType());

    }

}
