package nl.groep4.kvc.server.model;

import java.util.EnumMap;
import java.util.HashMap;

import nl.groep4.kvc.common.enumeration.Resource;

public class ServerCosts {

    public static final EnumMap<Resource, Integer> STREET_COSTS;
    public static final EnumMap<Resource, Integer> VILLAGE_COSTS;
    public static final EnumMap<Resource, Integer> CITY_COSTS;
    public static final EnumMap<Resource, Integer> DEVELOPMENT_CARD_COSTS;

    static {
	HashMap<Resource, Integer> streetCosts = new HashMap<>();
	streetCosts.put(Resource.WOOD, 1);
	streetCosts.put(Resource.BRICK, 1);
	STREET_COSTS = new EnumMap<>(streetCosts);
	HashMap<Resource, Integer> villageCosts = new HashMap<>();
	villageCosts.put(Resource.WOOD, 1);
	villageCosts.put(Resource.BRICK, 1);
	villageCosts.put(Resource.WHEAT, 1);
	villageCosts.put(Resource.WOOL, 1);
	VILLAGE_COSTS = new EnumMap<>(villageCosts);
	HashMap<Resource, Integer> cityCosts = new HashMap<>();
	cityCosts.put(Resource.WHEAT, 2);
	cityCosts.put(Resource.ORE, 3);
	CITY_COSTS = new EnumMap<>(cityCosts);
	HashMap<Resource, Integer> developmentCardCosts = new HashMap<>();
	developmentCardCosts.put(Resource.WHEAT, 1);
	developmentCardCosts.put(Resource.WOOL, 1);
	developmentCardCosts.put(Resource.ORE, 1);
	DEVELOPMENT_CARD_COSTS = new EnumMap<>(developmentCardCosts);
    }

}
