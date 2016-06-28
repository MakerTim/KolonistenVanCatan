package nl.groep4.kvc.server.util.serilize;

import java.lang.reflect.Type;
import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.server.ServerStarter;

public class PlayerAdapter extends InterfaceAdapter<Player> {

    @Override
    public JsonElement serialize(Player pl, Type interfaceType, JsonSerializationContext context) {
	try {
	    JsonObject jo = new JsonObject();
	    jo.add("color", context.serialize(pl.getColor(), Color.class));
	    jo.add("cards", context.serialize(pl.getCards()));
	    jo.add("resources", context.serialize(pl.getResources()));
	    jo.addProperty("villagesToBuild", pl.getRemainingVillages());
	    jo.addProperty("citysToBuild", pl.getRemainingCitys());
	    jo.addProperty("streetsToBuild", pl.getRemainingStreets());
	    jo.addProperty("score", pl.getPoints());
	    jo.addProperty("hasLongestRoad", pl.hasLongestRoad());
	    jo.addProperty("hasMostRidders", pl.hasMostKnights());
	    return jo;
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	    return new JsonObject();
	}
    }

    @Override
    public Player deserialize(JsonElement elem, Type interfaceType, JsonDeserializationContext context)
	    throws JsonParseException, PlayerNotFoundException {
	JsonObject obj = (JsonObject) elem;
	Color color = context.deserialize(obj.get("color"), Color.class);
	Player player = null;
	try {
	    for (Player pl : ServerStarter.getLobby().getPlayers()) {
		if (pl.getColor().equals(color)) {
		    player = pl;
		    break;
		}
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
	if (player == null) {
	    throw new PlayerNotFoundException(color);
	}
	try {
	    player.setCards(context.deserialize(obj.get("cards"), List.class));

	    Map<String, Double> gsonResources = context.deserialize(obj.get("resources"), EnumMap.class);
	    Map<Resource, Integer> resources = new HashMap<>();
	    for (Entry<String, Double> gsonEntry : gsonResources.entrySet()) {
		resources.put(Resource.valueOf(gsonEntry.getKey()), gsonEntry.getValue().intValue());
	    }

	    player.setResources(new EnumMap<>(resources));
	    player.setVillagesToBuild(obj.get("villagesToBuild").getAsInt());
	    player.setCitysToBuild(obj.get("citysToBuild").getAsInt());
	    player.setStreetsToBuild(obj.get("streetsToBuild").getAsInt());
	    player.setPoints(obj.get("score").getAsInt());
	    player.setHasLongestRoad(obj.get("hasLongestRoad").getAsBoolean());
	    player.setMostRidder(obj.get("hasMostRidders").getAsBoolean());
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
	return player;
    }
}