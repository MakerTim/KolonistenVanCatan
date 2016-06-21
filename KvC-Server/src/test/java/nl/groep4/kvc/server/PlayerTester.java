package nl.groep4.kvc.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;

import org.junit.Test;

import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.server.model.ServerPlayer;

public class PlayerTester {
    Player player = new ServerPlayer("MakerTim");

    @Test
    public void testVillage() throws RemoteException {
	player.addRemainingVillages(1);
	assertEquals(1, player.getRemainingVillages());
	player.addRemainingVillages(1);
	assertEquals(2, player.getRemainingVillages());
	player.addRemainingVillages(3);
	assertEquals(5, player.getRemainingVillages());

	player.removeRemainingVillage();
	assertEquals(4, player.getRemainingVillages());
	assertTrue(player.hasRemainingVillages());
	player.removeRemainingVillage();
	assertTrue(player.hasRemainingVillages());
	assertEquals(3, player.getRemainingVillages());
	player.removeRemainingVillage();
	assertTrue(player.hasRemainingVillages());
	assertEquals(2, player.getRemainingVillages());
	player.removeRemainingVillage();
	assertTrue(player.hasRemainingVillages());
	assertEquals(1, player.getRemainingVillages());
	player.removeRemainingVillage();
	assertTrue(!player.hasRemainingVillages());
	assertEquals(0, player.getRemainingVillages());
    }

    @Test
    public void testCity() throws RemoteException {
	player.addRemainingCitys(1);
	assertEquals(1, player.getRemainingCitys());
	player.addRemainingCitys(1);
	assertEquals(2, player.getRemainingCitys());
	player.addRemainingCitys(3);
	assertEquals(5, player.getRemainingCitys());

	player.removeRemainingCity();
	assertEquals(4, player.getRemainingCitys());
	assertTrue(player.hasRemainingCitys());
	player.removeRemainingCity();
	assertTrue(player.hasRemainingCitys());
	assertEquals(3, player.getRemainingCitys());
	player.removeRemainingCity();
	assertTrue(player.hasRemainingCitys());
	assertEquals(2, player.getRemainingCitys());
	player.removeRemainingCity();
	assertTrue(player.hasRemainingCitys());
	assertEquals(1, player.getRemainingCitys());
	player.removeRemainingCity();
	assertTrue(!player.hasRemainingCitys());
	assertEquals(0, player.getRemainingCitys());
    }

    @Test
    public void testStreet() throws RemoteException {
	player.addRemainingStreets(1);
	assertEquals(1, player.getRemainingStreets());
	player.addRemainingStreets(1);
	assertEquals(2, player.getRemainingStreets());
	player.addRemainingStreets(3);
	assertEquals(5, player.getRemainingStreets());

	player.removeRemainingStreet();
	assertEquals(4, player.getRemainingStreets());
	assertTrue(player.hasRemainingStreets());
	player.removeRemainingStreet();
	assertTrue(player.hasRemainingStreets());
	assertEquals(3, player.getRemainingStreets());
	player.removeRemainingStreet();
	assertTrue(player.hasRemainingStreets());
	assertEquals(2, player.getRemainingStreets());
	player.removeRemainingStreet();
	assertTrue(player.hasRemainingStreets());
	assertEquals(1, player.getRemainingStreets());
	player.removeRemainingStreet();
	assertTrue(!player.hasRemainingStreets());
	assertEquals(0, player.getRemainingStreets());
    }
}
