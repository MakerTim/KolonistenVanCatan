package nl.groep4.kvc.server.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.groep4.kvc.common.map.TileType;

public class TileFactory {

    private static final List<TileType> LAND_TILES;

    static {
	List<TileType> landTiles = new ArrayList<>();
	landTiles.add(TileType.DESERT);
	landTiles.add(TileType.DESERT);
	for (int i = 0; i < 6; i++) {
	    landTiles.add(TileType.WHEAT);
	    landTiles.add(TileType.PLAINS);
	    landTiles.add(TileType.FOREST);
	}
	for (int i = 0; i < 5; i++) {
	    landTiles.add(TileType.MESA);
	    landTiles.add(TileType.MOUNTAIN);
	}
	LAND_TILES = Collections.unmodifiableList(landTiles);
    }

    public static List<TileType> getNeeded() {
	return new ArrayList<>(LAND_TILES);
    }

}
