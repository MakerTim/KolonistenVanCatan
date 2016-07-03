package nl.groep4.kvc.server.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Trade;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.server.util.serilize.BuildingAdapter;
import nl.groep4.kvc.server.util.serilize.PlayerAdapter;
import nl.groep4.kvc.server.util.serilize.StreetAdapter;
import nl.groep4.kvc.server.util.serilize.TileAdapter;
import nl.groep4.kvc.server.util.serilize.TradeAdapter;

public abstract class SaveLoadHelper {

    protected static final Gson GSON = new GsonBuilder().registerTypeHierarchyAdapter(Player.class, new PlayerAdapter())
	    .registerTypeHierarchyAdapter(Tile.class, new TileAdapter())
	    .registerTypeHierarchyAdapter(Trade.class, new TradeAdapter())
	    .registerTypeHierarchyAdapter(Street.class, new StreetAdapter())
	    .registerTypeHierarchyAdapter(Building.class, new BuildingAdapter()).setPrettyPrinting().create();
}
