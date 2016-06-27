package nl.groep4.kvc.server.factory;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.map.TileType;

/**
 * All the tiles in the game will be put in a List and can't be modified after
 * this.
 * 
 * @author Tim
 * 
 * @version 1.2
 */
public class TileFactory {

    /**
     * Gives an array with an unmodifiable List in it of all the tiles in the
     * game.
     * 
     * @return All tiletypes that are needed in the game.
     */
    public static List<TileType> getNeeded() {
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
	return landTiles;
    }

    /**
     * Gives an array with an unmodifiable List in it of all the numbers for the
     * tiles in the game.
     * 
     * @return All numbers that will be added to the game.
     */
    public static List<Integer> getNumbers() {
	List<Integer> numbers = new ArrayList<>();
	for (int i = 2; i < 7; i++) {
	    numbers.add(i);
	    numbers.add(i);
	}
	for (int i = 8; i < 13; i++) {
	    numbers.add(i);
	    numbers.add(i);
	}
	for (int i = 3; i < 7; i++) {
	    numbers.add(i);
	}
	for (int i = 8; i < 12; i++) {
	    numbers.add(i);
	}
	return numbers;
    }
}
