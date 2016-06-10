package nl.groep4.kvc.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.map.TileLand;
import nl.groep4.kvc.common.map.TileType;
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
	assertEquals(new Coordinate(0, -1), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.SOUTH);
	assertEquals(new Coordinate(0, 1), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_EAST);
	assertEquals(new Coordinate(1, 1), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_WEST);
	assertEquals(new Coordinate(-1, 1), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.NORTH_EAST);
	assertEquals(new Coordinate(1, 0), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.NORTH_WEST);
	assertEquals(new Coordinate(-1, 0), foundTile.getPosition());

	tile = map.getTile(new Coordinate(0, 2));

	foundTile = map.getRelativeTile(tile, Direction.NORTH);
	assertEquals(new Coordinate(0, 1), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.SOUTH);
	assertEquals(new Coordinate(0, 3), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_EAST);
	assertEquals(new Coordinate(1, 3), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_WEST);
	assertEquals(new Coordinate(-1, 3), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.NORTH_EAST);
	assertEquals(new Coordinate(1, 2), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.NORTH_WEST);
	assertEquals(new Coordinate(-1, 2), foundTile.getPosition());

	tile = map.getTile(new Coordinate(-2, -1));

	foundTile = map.getRelativeTile(tile, Direction.NORTH);
	assertEquals(new Coordinate(-2, -2), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.SOUTH);
	assertEquals(new Coordinate(-2, 0), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_EAST);
	assertEquals(new Coordinate(-1, 0), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_WEST);
	assertEquals(new Coordinate(-3, 0), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.NORTH_EAST);
	assertEquals(new Coordinate(-1, -1), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.NORTH_WEST);
	assertEquals(new Coordinate(-3, -1), foundTile.getPosition());

	tile = map.getTile(new Coordinate(0, -4));

	assertNotNull(foundTile);
	foundTile = map.getRelativeTile(tile, Direction.NORTH);
	assertNull(null);
	foundTile = map.getRelativeTile(tile, Direction.SOUTH);
	assertEquals(new Coordinate(0, -3), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_EAST);
	assertEquals(new Coordinate(1, -3), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.SOUTH_WEST);
	assertEquals(new Coordinate(-1, -3), foundTile.getPosition());
	foundTile = map.getRelativeTile(tile, Direction.NORTH_EAST);
	assertNull(null);
	foundTile = map.getRelativeTile(tile, Direction.NORTH_WEST);
	assertNull(null);

	// Kijken of daadwerkelijk een tegel naast de ander zit
	// map.getRelativeTile(tile, direction)
    }

    @Test
    public void testTilesCreated() {

	int counterFOREST = 0;
	int counterMESA = 0;
	int counterPLAINS = 0;
	int counterMOUNTAIN = 0;
	int counterWHEAT = 0;
	int counterDESERT = 0;
	int counterWATER = 0;

	List<Tile> tiles = map.getTiles();

	for (int i = 0; i < tiles.size(); i++) {
	    Tile tile = tiles.get(i);

	    tile.getType();
	    switch (tile.getType()) {
	    case FOREST:
		counterFOREST++;
		break;
	    case MESA:
		counterMESA++;
		break;
	    case PLAINS:
		counterPLAINS++;
		break;
	    case MOUNTAIN:
		counterMOUNTAIN++;
		break;
	    case WHEAT:
		counterWHEAT++;
		break;
	    case DESERT:
		counterDESERT++;
		break;
	    case WATER:
	    case WATER_TRADE:
	    case WATER_OPEN_TRADE:
		counterWATER++;
		break;
	    default:
		break;
	    }

	}
	assertEquals(6, counterFOREST);
	assertEquals(5, counterMESA);
	assertEquals(6, counterPLAINS);
	assertEquals(5, counterMOUNTAIN);
	assertEquals(6, counterWHEAT);
	assertEquals(2, counterDESERT);
	assertEquals(22, counterWATER);

	Tile tile = map.getTile(0, -4);
	assertEquals(TileType.WATER, tile.getType());
	Tile tile2 = map.getTile(1, -3);
	assertEquals(TileType.WATER, tile2.getType());
	Tile tile3 = map.getTile(2, -3);
	assertEquals(TileType.WATER, tile3.getType());
	Tile tile4 = map.getTile(3, -2);
	assertEquals(TileType.WATER, tile4.getType());
	Tile tile5 = map.getTile(4, -2);
	assertEquals(TileType.WATER, tile5.getType());
	Tile tile6 = map.getTile(4, -1);
	assertEquals(TileType.WATER, tile6.getType());
	Tile tile7 = map.getTile(4, 0);
	assertEquals(TileType.WATER, tile7.getType());
	Tile tile8 = map.getTile(4, 1);
	assertEquals(TileType.WATER, tile8.getType());
	Tile tile9 = map.getTile(3, 2);
	assertEquals(TileType.WATER, tile9.getType());
	Tile tile10 = map.getTile(2, 2);
	assertEquals(TileType.WATER, tile10.getType());
	Tile tile11 = map.getTile(1, 3);
	assertEquals(TileType.WATER, tile11.getType());
	Tile tile12 = map.getTile(0, 3);
	assertEquals(TileType.WATER, tile12.getType());
	Tile tile13 = map.getTile(-1, 3);
	assertEquals(TileType.WATER, tile13.getType());
	Tile tile14 = map.getTile(-2, 2);
	assertEquals(TileType.WATER, tile14.getType());
	Tile tile15 = map.getTile(-3, 2);
	assertEquals(TileType.WATER, tile15.getType());
	Tile tile16 = map.getTile(-4, 1);
	assertEquals(TileType.WATER, tile16.getType());
	Tile tile17 = map.getTile(-4, 0);
	assertEquals(TileType.WATER, tile17.getType());
	Tile tile18 = map.getTile(-4, -1);
	assertEquals(TileType.WATER, tile18.getType());
	Tile tile19 = map.getTile(-4, -2);
	assertEquals(TileType.WATER, tile19.getType());
	Tile tile20 = map.getTile(-3, -2);
	assertEquals(TileType.WATER, tile20.getType());
	Tile tile21 = map.getTile(-2, -3);
	assertEquals(TileType.WATER, tile21.getType());
	Tile tile22 = map.getTile(-1, -3);
	assertEquals(TileType.WATER, tile22.getType());

    }

    // map.getTiles() #
    // kijken of de desert op goede plek staat MOET NOG GEFIXED WORDEN WACHT OP
    // TIM!
    // kijken of er 6forest,6...,6... 5mesa, 5mountain #
    // kijken of buitenrand helemaal uit water bestaat #

    @Test
    public void correctStreets() {

	// alle relatieve tiles van de evengetalcentrale tile
	Tile landtileNorth = map.getTile(new Coordinate(0, -3));
	Tile landtileSouth = map.getTile(new Coordinate(0, -1));
	Tile landtileCenter = map.getTile(new Coordinate(0, -2));
	Tile landtileNE = map.getTile(new Coordinate(1, -2));
	Tile landtileNW = map.getTile(new Coordinate(-1, -2));
	Tile landtileSE = map.getTile(new Coordinate(1, -1));
	Tile landtileSW = map.getTile(new Coordinate(-1, -1));

	// Tile testTile = map.getTile(new Coordinate(0, -3));

	// assertNotNull(testTile.getStreet(Direction.NORTH));

	// de verschillende tiles met elkaar vergelijken
	assertNotNull(landtileCenter.getStreet(Direction.NORTH));
	assertNotNull(landtileCenter.getStreet(Direction.NORTH_EAST));
	assertNotNull(landtileCenter.getStreet(Direction.NORTH_WEST));
	assertNotNull(landtileCenter.getStreet(Direction.SOUTH));
	assertNotNull(landtileCenter.getStreet(Direction.SOUTH_EAST));
	assertNotNull(landtileCenter.getStreet(Direction.SOUTH_WEST));

	assertEquals(landtileNorth.getStreet(Direction.SOUTH), landtileCenter.getStreet(Direction.NORTH));

	// evengetal tiles hier getest
	assertEquals(landtileNE.getStreet(Direction.SOUTH_WEST), landtileCenter.getStreet(Direction.NORTH_EAST));
	assertEquals(landtileNW.getStreet(Direction.SOUTH_EAST), landtileCenter.getStreet(Direction.NORTH_WEST));
	assertEquals(landtileSE.getStreet(Direction.NORTH_WEST), landtileCenter.getStreet(Direction.SOUTH_EAST));
	assertEquals(landtileSW.getStreet(Direction.NORTH_EAST), landtileCenter.getStreet(Direction.SOUTH_WEST));
	assertEquals(landtileSouth.getStreet(Direction.NORTH), landtileCenter.getStreet(Direction.SOUTH));

	landtileNorth = map.getTile(new Coordinate(-1, -2));
	landtileSouth = map.getTile(new Coordinate(-1, 0));
	landtileCenter = map.getTile(new Coordinate(-1, -1));
	landtileNE = map.getTile(new Coordinate(0, -2));
	landtileNW = map.getTile(new Coordinate(-2, -2));
	landtileSE = map.getTile(new Coordinate(0, -1));
	landtileSW = map.getTile(new Coordinate(-2, -1));

	// niet evengetal tiles worden hier getest

	assertEquals(landtileNE.getStreet(Direction.SOUTH_WEST), landtileCenter.getStreet(Direction.NORTH_EAST));
	assertEquals(landtileNorth.getStreet(Direction.SOUTH), landtileCenter.getStreet(Direction.NORTH));
	assertEquals(landtileNW.getStreet(Direction.SOUTH_EAST), landtileCenter.getStreet(Direction.NORTH_WEST));
	assertEquals(landtileSE.getStreet(Direction.NORTH_WEST), landtileCenter.getStreet(Direction.SOUTH_EAST));
	assertEquals(landtileSW.getStreet(Direction.NORTH_EAST), landtileCenter.getStreet(Direction.SOUTH_WEST));
	assertEquals(landtileSouth.getStreet(Direction.NORTH), landtileCenter.getStreet(Direction.SOUTH));

	landtileNorth = map.getTile(new Coordinate(3, 0));
	landtileSouth = map.getTile(new Coordinate(3, 2));
	landtileCenter = map.getTile(new Coordinate(3, 1));
	landtileNE = map.getTile(new Coordinate(4, 0));
	landtileNW = map.getTile(new Coordinate(2, -0));
	landtileSE = map.getTile(new Coordinate(4, 1));
	landtileSW = map.getTile(new Coordinate(2, 1));

	assertEquals(landtileNE.getStreet(Direction.SOUTH_WEST), landtileCenter.getStreet(Direction.NORTH_EAST));
	assertEquals(landtileNorth.getStreet(Direction.SOUTH), landtileCenter.getStreet(Direction.NORTH));
	assertEquals(landtileNW.getStreet(Direction.SOUTH_EAST), landtileCenter.getStreet(Direction.NORTH_WEST));
	assertEquals(landtileSE.getStreet(Direction.NORTH_WEST), landtileCenter.getStreet(Direction.SOUTH_EAST));
	assertEquals(landtileSW.getStreet(Direction.NORTH_EAST), landtileCenter.getStreet(Direction.SOUTH_WEST));
	assertEquals(landtileSouth.getStreet(Direction.NORTH), landtileCenter.getStreet(Direction.SOUTH));

	// voor elke street kijken of het 2 tiles heeft

	for (Street street : map.getAllStreets()) {
	    int tilesConnected = 0;
	    for (Tile tile : street.getConnectedTiles()) {
		if (tile != null) {
		    tilesConnected++;
		}
	    }
	    assertEquals(2, tilesConnected);
	}
    }

    // Kijken of elke landtegel 6 straten heeft #
    // map.getAllStreets() #
    // Kijk of Tile x met als buur tile y dezelfde street delen #

    @Test
    public void correctBuilding() {

	// tile.isValidPlace(Point point) om te kijken of de plek wel geschikt
	// 1) bij een evengetal tile

	Tile evengetalTile = map.getTile(new Coordinate(0, -2));

	assertTrue(evengetalTile.isValidPlace(Point.NORTH_EAST));
	assertTrue(evengetalTile.isValidPlace(Point.EAST));
	assertTrue(evengetalTile.isValidPlace(Point.SOUTH_EAST));
	assertTrue(evengetalTile.isValidPlace(Point.NORTH_WEST));
	assertTrue(evengetalTile.isValidPlace(Point.WEST));
	assertTrue(evengetalTile.isValidPlace(Point.SOUTH_WEST));

	// 2) bij een onevengetal tile

	Tile OnevengetalTile = map.getTile(new Coordinate(0, -2));

	assertTrue(OnevengetalTile.isValidPlace(Point.NORTH_EAST));
	assertTrue(OnevengetalTile.isValidPlace(Point.EAST));
	assertTrue(OnevengetalTile.isValidPlace(Point.SOUTH_EAST));
	assertTrue(OnevengetalTile.isValidPlace(Point.NORTH_WEST));
	assertTrue(OnevengetalTile.isValidPlace(Point.WEST));
	assertTrue(OnevengetalTile.isValidPlace(Point.SOUTH_WEST));

	Tile landtileEven1 = map.getTile(new Coordinate(0, -3));
	Tile landtileEven2 = map.getTile(new Coordinate(0, -1));
	Tile landtileEven3 = map.getTile(new Coordinate(0, -2));
	Tile landtileOnEven1 = map.getTile(new Coordinate(1, -2));
	Tile landtileOnEven2 = map.getTile(new Coordinate(-1, -2));
	Tile landtileOnEven3 = map.getTile(new Coordinate(1, -1));
	Tile landtileOnEven4 = map.getTile(new Coordinate(-1, -1));
	// Kijken of elke landtegel 6 buildings heeft

	// kijken of elke tile van de evengetallen landtiles een building heeft
	// #

	assertNotNull(landtileEven1.getBuilding(Point.NORTH_EAST));
	assertNotNull(landtileEven1.getBuilding(Point.NORTH_WEST));
	assertNotNull(landtileEven1.getBuilding(Point.SOUTH_EAST));
	assertNotNull(landtileEven1.getBuilding(Point.SOUTH_WEST));
	assertNotNull(landtileEven1.getBuilding(Point.WEST));
	assertNotNull(landtileEven1.getBuilding(Point.EAST));

	assertNotNull(landtileEven2.getBuilding(Point.NORTH_EAST));
	assertNotNull(landtileEven2.getBuilding(Point.NORTH_WEST));
	assertNotNull(landtileEven2.getBuilding(Point.SOUTH_EAST));
	assertNotNull(landtileEven2.getBuilding(Point.SOUTH_WEST));
	assertNotNull(landtileEven2.getBuilding(Point.WEST));
	assertNotNull(landtileEven2.getBuilding(Point.EAST));

	assertNotNull(landtileEven3.getBuilding(Point.NORTH_EAST));
	assertNotNull(landtileEven3.getBuilding(Point.NORTH_WEST));
	assertNotNull(landtileEven3.getBuilding(Point.SOUTH_EAST));
	assertNotNull(landtileEven3.getBuilding(Point.SOUTH_WEST));
	assertNotNull(landtileEven3.getBuilding(Point.WEST));
	assertNotNull(landtileEven3.getBuilding(Point.EAST));

	// kijken of elke tile van de onvengetallen landtiles een building heeft

	assertNotNull(landtileOnEven1.getBuilding(Point.NORTH_EAST));
	assertNotNull(landtileOnEven1.getBuilding(Point.NORTH_WEST));
	assertNotNull(landtileOnEven1.getBuilding(Point.SOUTH_EAST));
	assertNotNull(landtileOnEven1.getBuilding(Point.SOUTH_WEST));
	assertNotNull(landtileOnEven1.getBuilding(Point.WEST));
	assertNotNull(landtileOnEven1.getBuilding(Point.EAST));

	assertNotNull(landtileOnEven2.getBuilding(Point.NORTH_EAST));
	assertNotNull(landtileOnEven2.getBuilding(Point.NORTH_WEST));
	assertNotNull(landtileOnEven2.getBuilding(Point.SOUTH_EAST));
	assertNotNull(landtileOnEven2.getBuilding(Point.SOUTH_WEST));
	assertNotNull(landtileOnEven2.getBuilding(Point.WEST));
	assertNotNull(landtileOnEven2.getBuilding(Point.EAST));

	assertNotNull(landtileOnEven3.getBuilding(Point.NORTH_EAST));
	assertNotNull(landtileOnEven3.getBuilding(Point.NORTH_WEST));
	assertNotNull(landtileOnEven3.getBuilding(Point.SOUTH_EAST));
	assertNotNull(landtileOnEven3.getBuilding(Point.SOUTH_WEST));
	assertNotNull(landtileOnEven3.getBuilding(Point.WEST));
	assertNotNull(landtileOnEven3.getBuilding(Point.EAST));

	assertNotNull(landtileOnEven4.getBuilding(Point.NORTH_EAST));
	assertNotNull(landtileOnEven4.getBuilding(Point.NORTH_WEST));
	assertNotNull(landtileOnEven4.getBuilding(Point.SOUTH_EAST));
	assertNotNull(landtileOnEven4.getBuilding(Point.SOUTH_WEST));
	assertNotNull(landtileOnEven4.getBuilding(Point.WEST));
	assertNotNull(landtileOnEven4.getBuilding(Point.EAST));

	// map.getAllBuidlings()

	// Kijk of Tile x met als buur tile y dezelfde building delen #
	Tile landtileCentral = map.getTile(new Coordinate(0, -2));
	Tile landtileNorth = map.getTile(new Coordinate(0, -3));
	Tile landtileSouth = map.getTile(new Coordinate(0, -1));
	Tile landtileNE = map.getTile(new Coordinate(1, -2));
	Tile landtileNW = map.getTile(new Coordinate(-1, -2));
	Tile landtileSE = map.getTile(new Coordinate(1, -1));
	Tile landtileSW = map.getTile(new Coordinate(-1, -1));

	assertEquals(landtileCentral.getBuilding(Point.NORTH_EAST), landtileNorth.getBuilding(Point.SOUTH_EAST));
	assertEquals(landtileCentral.getBuilding(Point.NORTH_EAST), landtileNE.getBuilding(Point.WEST));
	assertEquals(landtileCentral.getBuilding(Point.EAST), landtileNE.getBuilding(Point.SOUTH_WEST));
	assertEquals(landtileCentral.getBuilding(Point.EAST), landtileSE.getBuilding(Point.NORTH_WEST));
	assertEquals(landtileCentral.getBuilding(Point.SOUTH_EAST), landtileSE.getBuilding(Point.WEST));
	assertEquals(landtileCentral.getBuilding(Point.SOUTH_EAST), landtileSouth.getBuilding(Point.NORTH_EAST));
	assertEquals(landtileCentral.getBuilding(Point.SOUTH_WEST), landtileSouth.getBuilding(Point.NORTH_WEST));
	assertEquals(landtileCentral.getBuilding(Point.SOUTH_WEST), landtileSW.getBuilding(Point.EAST));
	assertEquals(landtileCentral.getBuilding(Point.WEST), landtileSW.getBuilding(Point.NORTH_EAST));
	assertEquals(landtileCentral.getBuilding(Point.WEST), landtileNW.getBuilding(Point.SOUTH_EAST));
	assertEquals(landtileCentral.getBuilding(Point.NORTH_WEST), landtileNW.getBuilding(Point.EAST));
	assertEquals(landtileCentral.getBuilding(Point.NORTH_WEST), landtileNorth.getBuilding(Point.SOUTH_WEST));

	// Building met coordinaat en kijken of de 3 tiles eromheen kloppen

	Tile buildingTile1 = map.getTile(new Coordinate(0, -2));
	Tile buildingTile2 = map.getTile(new Coordinate(0, -1));
	Tile buildingTile3 = map.getTile(new Coordinate(-1, -1));

	assertEquals(buildingTile1.getBuilding(Point.SOUTH_WEST), buildingTile2.getBuilding(Point.NORTH_WEST));
	assertEquals(buildingTile3.getBuilding(Point.EAST), buildingTile2.getBuilding(Point.NORTH_WEST));
	assertEquals(buildingTile2.getBuilding(Point.NORTH_WEST), buildingTile1.getBuilding(Point.SOUTH_WEST));
	assertEquals(buildingTile1.getBuilding(Point.SOUTH_WEST), buildingTile3.getBuilding(Point.EAST));

	// Op een desert moet standaard een rover en op een normale tile niet #
	// tile.hasRover() #

	TileLand desertTile1 = (TileLand) map.getTile(new Coordinate(0, -2));
	TileLand desertTile2 = (TileLand) map.getTile(new Coordinate(0, 1));
	TileLand normalTile = (TileLand) map.getTile(new Coordinate(-1, -1));
	TileLand normalTile2 = (TileLand) map.getTile(new Coordinate(-2, 0));
	TileLand normalTile3 = (TileLand) map.getTile(new Coordinate(-3, -1));
	TileLand normalTile4 = (TileLand) map.getTile(new Coordinate(3, -1));
	TileLand normalTile5 = (TileLand) map.getTile(new Coordinate(2, 1));

	assertTrue(desertTile1.hasRover());
	assertTrue(desertTile2.hasRover());
	assertTrue(!normalTile.hasRover());
	assertTrue(!normalTile2.hasRover());
	assertTrue(!normalTile3.hasRover());
	assertTrue(!normalTile4.hasRover());
	assertTrue(!normalTile5.hasRover());

    }

}
