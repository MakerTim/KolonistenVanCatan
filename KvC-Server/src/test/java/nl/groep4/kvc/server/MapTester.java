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
	Tile tile = map.getTile(new Coordinate(0, 0));

	Tile foundTile;

	foundTile = map.getRelativeTile(tile, Direction.NORTH);
	assertEquals(foundTile.getPosition(), new Coordinate(0, -1));
	foundTile = map.getRelativeTile(tile, Direction.SOUTH);
	assertEquals(foundTile.getPosition(), new Coordinate(0, 1));
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_EAST);
	assertEquals(foundTile.getPosition(), new Coordinate(1, 1));
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_WEST);
	assertEquals(foundTile.getPosition(), new Coordinate(-1, 1));
	foundTile = map.getRelativeTile(tile, Direction.NORTH_EAST);
	assertEquals(foundTile.getPosition(), new Coordinate(1, 0));
	foundTile = map.getRelativeTile(tile, Direction.NORTH_WEST);
	assertEquals(foundTile.getPosition(), new Coordinate(-1, 0));

	tile = map.getTile(new Coordinate(0, 2));

	foundTile = map.getRelativeTile(tile, Direction.NORTH);
	assertEquals(foundTile.getPosition(), new Coordinate(0, 1));
	foundTile = map.getRelativeTile(tile, Direction.SOUTH);
	assertEquals(foundTile.getPosition(), new Coordinate(0, 3));
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_EAST);
	assertEquals(foundTile.getPosition(), new Coordinate(1, 3));
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_WEST);
	assertEquals(foundTile.getPosition(), new Coordinate(-1, 3));
	foundTile = map.getRelativeTile(tile, Direction.NORTH_EAST);
	assertEquals(foundTile.getPosition(), new Coordinate(1, 2));
	foundTile = map.getRelativeTile(tile, Direction.NORTH_WEST);
	assertEquals(foundTile.getPosition(), new Coordinate(-1, 2));

	tile = map.getTile(new Coordinate(-2, -1));

	foundTile = map.getRelativeTile(tile, Direction.NORTH);
	assertEquals(foundTile.getPosition(), new Coordinate(-2, -2));
	foundTile = map.getRelativeTile(tile, Direction.SOUTH);
	assertEquals(foundTile.getPosition(), new Coordinate(-2, 0));
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_EAST);
	assertEquals(foundTile.getPosition(), new Coordinate(-1, 0));
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_WEST);
	assertEquals(foundTile.getPosition(), new Coordinate(-3, 0));
	foundTile = map.getRelativeTile(tile, Direction.NORTH_EAST);
	assertEquals(foundTile.getPosition(), new Coordinate(-1, -1));
	foundTile = map.getRelativeTile(tile, Direction.NORTH_WEST);
	assertEquals(foundTile.getPosition(), new Coordinate(-3, -1));

	tile = map.getTile(new Coordinate(0, -4));

	assertNotNull(foundTile);
	foundTile = map.getRelativeTile(tile, Direction.NORTH);
	assertNull(null);
	foundTile = map.getRelativeTile(tile, Direction.SOUTH);
	assertEquals(foundTile.getPosition(), new Coordinate(0, -3));
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_EAST);
	assertEquals(foundTile.getPosition(), new Coordinate(1, -3));
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_WEST);
	assertEquals(foundTile.getPosition(), new Coordinate(-1, -3));
	foundTile = map.getRelativeTile(tile, Direction.NORTH_EAST);
	assertNull(null);
	foundTile = map.getRelativeTile(tile, Direction.NORTH_WEST);
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

    }

    @Test
    public void correctBuilding() {
	// Kijken of elke landtegel 6 buildings heeft
	// map.getAllBuidlings()
	// Kijk of Tile x met als buur tile y dezelfde building delen
	/** het geeft nog een fail, nog niet af */
    }
}
