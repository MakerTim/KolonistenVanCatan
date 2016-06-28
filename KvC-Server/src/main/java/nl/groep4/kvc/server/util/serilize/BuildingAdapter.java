package nl.groep4.kvc.server.util.serilize;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.server.model.map.ServerBuilding;

public class BuildingAdapter extends InterfaceAdapter<Building> {

    @Override
    public JsonElement serialize(Building building, Type interfaceType, JsonSerializationContext context) {
	JsonObject obj = new JsonObject();
	obj.add("type", context.serialize(building.getBuildingType(), BuildingType.class));
	obj.add("owner", context.serialize(building.getOwner(), Player.class));
	obj.add("location", context.serialize(building.getPosition(), Coordinate.class));
	return obj;
    }

    @Override
    public Building deserialize(JsonElement elem, Type interfaceType, JsonDeserializationContext context)
	    throws JsonParseException {
	if (elem.isJsonObject()) {
	    JsonObject obj = (JsonObject) elem;
	    Building building = new ServerBuilding(context.deserialize(obj.get("location"), Coordinate.class));
	    building.setOwner(context.deserialize(obj.get("owner"), Player.class));
	    return building;
	}
	return null;
    }
}