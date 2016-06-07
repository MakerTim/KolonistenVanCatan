package nl.groep4.kvc.server;

import org.junit.*;

import nl.groep4.kvc.common.map.Map;
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
