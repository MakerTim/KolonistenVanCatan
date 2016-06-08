package nl.groep4.kvc.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.server.model.map.ServerMap;

public class MapTester {

    private Map map;

    @Before
    public void build() {
	map = new ServerMap();
	map.createMap();
    }

    @Test
    public void testRelativeTile() {
	Tile tile = map.getTile(new Coordinate((short) 0, (short) 0));

	Tile foundTile;

	foundTile = map.getRelativeTile(tile, Direction.NORTH);
	assertEquals(foundTile.getPosition(), new Coordinate((short) 0, (short) -1));
	foundTile = map.getRelativeTile(tile, Direction.SOUTH);
	assertEquals(foundTile.getPosition(), new Coordinate((short) 0, (short) 1));
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_EAST);
	assertEquals(foundTile.getPosition(), new Coordinate((short) 1, (short) 1));
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_WEST);
	assertEquals(foundTile.getPosition(), new Coordinate((short) -1, (short) 1));
	foundTile = map.getRelativeTile(tile, Direction.NORTH_EAST);
	assertEquals(foundTile.getPosition(), new Coordinate((short) 1, (short) 0));
	foundTile = map.getRelativeTile(tile, Direction.NORTH_WEST);
	assertEquals(foundTile.getPosition(), new Coordinate((short) -1, (short) 0));
	assertNotNull(foundTile);
	assertNull(null);
	// Kijken of daadwerkelijk een tegel naast de ander zit
	// map.getRelativeTile(tile, direction)
    }

    @Test
    public void testTilesCreated() {
	// Kijken of daadwerkelijk een tegel naast de ander zit
	// map.getTiles()
	// kijken of de desert op goede plek staat
	// kijken of er 6forest,6...,6... 5mesa, 5mountain
	// kijken of buitenrand helemaal uit water bestaat
    }

    @Test
    public void correctStreets() {
	// Kijken of elke landtegel 6 straten heeft
	// map.getAllStreets()
	// Kijk of Tile x met als buur tile y dezelfde street delen
	/** het geeft nog een fail, nog niet af */
    }

    @Test
    public void correctBuilding() {
	// Kijken of elke landtegel 6 buildings heeft
	// map.getAllBuidlings()
	// Kijk of Tile x met als buur tile y dezelfde building delen
	/** het geeft nog een fail, nog niet af */
    }
}
