package nl.groep4.kvc.server;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.server.model.ServerPlayer;
import nl.groep4.kvc.server.model.map.ServerMap;
import nl.groep4.kvc.server.util.RoadFinder;

public class LongestRoadAlgorithTester {

    private Map map;
    private Player[] players;

    public Map getMap() {
	return map;
    }

    @Before
    public void setupMap() {
	map = new ServerMap();
	map.createMap();

	Player makerTim = new ServerPlayer("MakerTim");
	Player bachir = new ServerPlayer("Bachir");
	Player lisa = new ServerPlayer("Lisa");
	try {
	    makerTim.setColor(Color.ORANGE);
	    bachir.setColor(Color.GREEN);
	    lisa.setColor(Color.RED);
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
	players = new Player[] { makerTim, bachir, lisa };

	/* SETUP TEST ROADS */
	/* Map layout -> http://puu.sh/pDtZN.jpg */
	Tile tile$2_m1 = map.getTile(2, -1);
	Tile tile$1_0 = map.getTile(1, 0);
	Tile tile$0_0 = map.getTile(0, 0);
	Tile tile$0_m2 = map.getTile(0, -2);
	Tile tile$m1_0 = map.getTile(-1, 0);
	Tile tile$m2_1 = map.getTile(-2, 0);
	Tile tile$m1_m2 = map.getTile(-1, -2);

	tile$2_m1.getStreet(Direction.NORTH_WEST).setOwner(makerTim);
	tile$2_m1.getStreet(Direction.NORTH).setOwner(makerTim);
	tile$2_m1.getStreet(Direction.NORTH_EAST).setOwner(makerTim);
	tile$1_0.getStreet(Direction.NORTH_WEST).setOwner(makerTim);
	tile$1_0.getStreet(Direction.NORTH).setOwner(makerTim);
	tile$1_0.getStreet(Direction.NORTH_EAST).setOwner(makerTim);
	tile$0_0.getStreet(Direction.NORTH).setOwner(makerTim);
	tile$0_m2.getStreet(Direction.NORTH_WEST).setOwner(bachir);
	tile$0_m2.getStreet(Direction.NORTH).setOwner(bachir);
	tile$m1_0.getStreet(Direction.NORTH_WEST).setOwner(lisa);
	tile$m1_0.getStreet(Direction.SOUTH).setOwner(lisa);
	tile$m1_0.getStreet(Direction.NORTH).setOwner(makerTim);
	tile$m1_0.getStreet(Direction.NORTH_EAST).setOwner(makerTim);
	tile$m1_m2.getStreet(Direction.NORTH).setOwner(bachir);
	tile$m1_m2.getStreet(Direction.NORTH_EAST).setOwner(bachir);
	for (Direction direction : Direction.values()) {
	    tile$m2_1.getStreet(direction).setOwner(lisa);
	}
    }

    @Test
    public void playerStreetAmount() {
	setupMap();
	assertEquals(9, map.getAllStreets().stream().filter(street -> {
	    return street.getOwner() != null && street.getOwner().equals(players[0]);
	}).count());
	assertEquals(4, map.getAllStreets().stream().filter(street -> {
	    return street.getOwner() != null && street.getOwner().equals(players[1]);
	}).count());
	assertEquals(8, map.getAllStreets().stream().filter(street -> {
	    return street.getOwner() != null && street.getOwner().equals(players[2]);
	}).count());
    }

    @Test
    public void algorithmCheck() {
	RoadFinder lra = new RoadFinder(map);
	java.util.Map<Player, Integer> roadLenght = lra.getLongestRoadByPlayer();
	assertEquals("MakerTim route (orange).", 8, roadLenght.get(players[0]).intValue());
	assertEquals("Bachir route (blue).", 3, roadLenght.get(players[1]).intValue());
	assertEquals("Lisa route (red).", 7, roadLenght.get(players[2]).intValue());
    }

    @Test
    public void algorithmDoubleCheck() {
	// Set lisa one longer
	Tile tile$0_0 = map.getTile(0, 0);
	tile$0_0.getStreet(Direction.SOUTH_WEST).setOwner(players[2]);
	// Extra one sized road
	tile$0_0.getStreet(Direction.SOUTH_EAST).setOwner(players[0]);

	RoadFinder lra = new RoadFinder(map);
	java.util.Map<Player, Integer> roadLenght = lra.getLongestRoadByPlayer();
	assertEquals("MakerTim route (orange).", 8, roadLenght.get(players[0]).intValue());
	assertEquals("Bachir route (blue).", 3, roadLenght.get(players[1]).intValue());
	assertEquals("Lisa route (red).", 8, roadLenght.get(players[2]).intValue());
    }

}
