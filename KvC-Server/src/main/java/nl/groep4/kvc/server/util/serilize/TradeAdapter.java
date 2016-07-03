package nl.groep4.kvc.server.util.serilize;

import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Trade;
import nl.groep4.kvc.server.model.ServerTrade;

public class TradeAdapter extends InterfaceAdapter<Trade> {

    @Override
    public JsonElement serialize(Trade trade, Type interfaceType, JsonSerializationContext context) {
	JsonObject obj = new JsonObject();
	obj.add("player", context.serialize(trade.getPlayer(), Player.class));
	obj.add("request", context.serialize(trade.getRequest(), Map.class));
	obj.add("reward", context.serialize(trade.getReward(), Map.class));
	return obj;
    }

    @Override
    public Trade deserialize(JsonElement elem, Type interfaceType, JsonDeserializationContext context)
	    throws JsonParseException {
	if (elem.isJsonObject()) {
	    JsonObject obj = elem.getAsJsonObject();
	    Player player = context.deserialize(obj.get("player"), Player.class);
	    Map<String, Double> requestGson = context.deserialize(obj.get("request"), Map.class);
	    Map<String, Double> rewardGson = context.deserialize(obj.get("reward"), Map.class);
	    Map<Resource, Integer> requests = new HashMap<>();
	    Map<Resource, Integer> rewards = new HashMap<>();
	    for (Entry<String, Double> request : requestGson.entrySet()) {
		requests.put(Resource.valueOf(request.getKey()), request.getValue().intValue());
	    }
	    for (Entry<String, Double> reward : rewardGson.entrySet()) {
		rewards.put(Resource.valueOf(reward.getKey()), reward.getValue().intValue());
	    }
	    return new ServerTrade(player, new EnumMap<>(requests), new EnumMap<>(rewards));
	}
	return null;
    }
}