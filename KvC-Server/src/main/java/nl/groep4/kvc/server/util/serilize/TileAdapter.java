package nl.groep4.kvc.server.util.serilize;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.map.TileLand;
import nl.groep4.kvc.common.map.TileResource;
import nl.groep4.kvc.common.map.TileType;
import nl.groep4.kvc.server.model.map.ServerTileDesert;
import nl.groep4.kvc.server.model.map.ServerTileResource;
import nl.groep4.kvc.server.model.map.ServerTileSea;

public class TileAdapter extends InterfaceAdapter<Tile> {

    @Override
    public JsonElement serialize(Tile tile, Type interfaceType, JsonSerializationContext context) {
	JsonObject obj = new JsonObject();
	obj.addProperty("class", tile.getClass().getSimpleName());
	obj.add("position", context.serialize(tile.getPosition(), Coordinate.class));
	obj.add("type", context.serialize(tile.getType(), TileType.class));
	if (tile instanceof TileLand) {
	    TileLand tileLand = (TileLand) tile;
	    obj.addProperty("hasRover", tileLand.hasRover());
	}
	if (tile instanceof TileResource) {
	    TileResource tileResource = (TileResource) tile;
	    obj.addProperty("number", tileResource.getNumber());
	}
	return obj;
    }

    @Override
    public Tile deserialize(JsonElement elem, Type interfaceType, JsonDeserializationContext context)
	    throws JsonParseException {
	if (elem.isJsonObject()) {
	    JsonObject obj = (JsonObject) elem;
	    Coordinate coord = context.deserialize(obj.get("position"), Coordinate.class);
	    TileLand land = null;
	    switch (obj.get("class").getAsString()) {
	    case "ServerTileSea":
		return new ServerTileSea(coord);
	    case "ServerTileResource":
		land = new ServerTileResource(context.deserialize(obj.get("type"), TileType.class),
			obj.get("number").getAsInt(), coord);
	    case "ServerTileDesert":
		land = new ServerTileDesert(coord);
	    }
	    if (obj.get("hasRover").getAsBoolean()) {
		land.placeRover();
	    } else {
		land.removeRover();
	    }
	    return land;
	}
	return null;
    }
}