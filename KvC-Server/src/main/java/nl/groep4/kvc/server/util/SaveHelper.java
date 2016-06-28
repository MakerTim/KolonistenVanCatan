package nl.groep4.kvc.server.util;

import com.google.gson.JsonObject;

import nl.groep4.kvc.common.enumeration.GameState;
import nl.groep4.kvc.server.controller.ServerKolonistenVanCatan;
import nl.groep4.kvc.server.model.ServerThrow;
import nl.groep4.kvc.server.model.map.ServerMap;

public class SaveHelper extends SaveLoadHelper {

    public static String toSaveFile(ServerKolonistenVanCatan kvc) {
	JsonObject server = new JsonObject();

	server.add("players", GSON.toJsonTree(kvc.getPlayers()));
	server.add("map", GSON.toJsonTree(kvc.getMap(), ServerMap.class));
	server.addProperty("endScore", kvc.getEndscore());
	server.addProperty("round", kvc.getRound());
	server.addProperty("turn", kvc.getTurnNumber());
	server.add("lastThrow", GSON.toJsonTree(kvc.getLastThrow(), ServerThrow.class));
	server.add("state", GSON.toJsonTree(kvc.getState(), GameState.class));

	return GSON.toJson(server);
    }

}
