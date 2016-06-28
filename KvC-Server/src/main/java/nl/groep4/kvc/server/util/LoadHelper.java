package nl.groep4.kvc.server.util;

import java.rmi.RemoteException;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.GameState;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.server.controller.ServerKolonistenVanCatan;
import nl.groep4.kvc.server.model.ServerThrow;
import nl.groep4.kvc.server.model.map.ServerMap;

public class LoadHelper extends SaveLoadHelper {

    public static ServerKolonistenVanCatan loadFromSave(String safeFile) throws RemoteException, JsonSyntaxException {
	ServerKolonistenVanCatan skvc = new ServerKolonistenVanCatan(null);
	JsonObject obj = new JsonParser().parse(safeFile).getAsJsonObject();

	List<Player> playersSave = GSON.fromJson(obj.get("players"), List.class);
	skvc.setPlayers(playersSave);
	skvc.setMap(GSON.fromJson(obj.get("map"), ServerMap.class));
	// FIXME: Update streets / buildings with tiles!

	for (Tile tile : skvc.getMap().getTiles()) {
	    fixStreets(skvc.getMap(), tile);
	    fixBuildings(skvc.getMap(), tile);
	}

	skvc.setEndScore(obj.get("endScore").getAsInt());
	skvc.setRound(obj.get("round").getAsInt());
	skvc.setTurn(obj.get("turn").getAsInt());
	skvc.setThrow(GSON.fromJson(obj.get("lastThrow"), ServerThrow.class));
	skvc.setState(GSON.fromJson(obj.get("state"), GameState.class));

	return skvc;
    }

    private static void fixBuildings(Map map, Tile tile) {
	Building[] buildings = new Building[Point.values().length];
	for (Point point : Point.values()) {
	    Coordinate location = point.addTo(tile.getPosition());
	    Building building = findBuilding(map, location);
	    if (building == null) {
		continue;
	    }
	    building.registerTile(tile);
	    buildings[point.ordinal()] = building;
	}
	tile.setupBuilding(buildings);
    }

    private static void fixStreets(Map map, Tile tile) {
	Street[] streets = new Street[Direction.values().length];
	for (Direction direction : Direction.values()) {
	    Coordinate location = tile.getPosition().add(direction.offset(tile.getPosition()).subtract(2));
	    Street street = findStreet(map, location);
	    if (street == null) {
		continue;
	    }
	    street.registerTile(tile);
	    streets[direction.ordinal()] = street;
	}
	tile.setupStreets(streets);
    }

    private static Street findStreet(Map map, Coordinate position) {
	return map.getAllStreets().stream().filter(street -> street.getPosition().equals(position)).findFirst()
		.orElse(null);
    }

    private static Building findBuilding(Map map, Coordinate position) {
	return map.getAllBuildings().stream().filter(builing -> builing.getPosition().equals(position)).findFirst()
		.orElse(null);
    }
}
