package nl.groep4.kvc.server.util.serilize;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.server.model.map.ServerStreet;

public class StreetAdapter extends InterfaceAdapter<Street> {

    @Override
    public JsonElement serialize(Street street, Type interfaceType, JsonSerializationContext context) {
	JsonObject obj = new JsonObject();
	obj.add("location", context.serialize(street.getPosition(), Coordinate.class));
	obj.add("owner", context.serialize(street.getOwner(), Player.class));
	return obj;
    }

    @Override
    public Street deserialize(JsonElement elem, Type interfaceType, JsonDeserializationContext context)
	    throws JsonParseException {
	if (elem.isJsonObject()) {
	    JsonObject obj = (JsonObject) elem;
	    Street street = new ServerStreet(context.deserialize(obj.get("location"), Coordinate.class));
	    street.setOwner(context.deserialize(obj.get("owner"), Player.class));
	    return street;
	}
	return null;
    }
}