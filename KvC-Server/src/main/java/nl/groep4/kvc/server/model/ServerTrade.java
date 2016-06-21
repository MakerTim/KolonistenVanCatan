package nl.groep4.kvc.server.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Trade;

public class ServerTrade implements Trade {

    private static final long serialVersionUID = -19111999L;

    private Player player;
    private EnumMap<Resource, Integer> request;
    private EnumMap<Resource, Integer> reward;
    private UUID key = UUID.randomUUID();

    public ServerTrade(Player player, Map<Resource, Integer> request, Map<Resource, Integer> reward) {
	this.player = player;
	this.request = new EnumMap<>(request);
	this.reward = new EnumMap<>(reward);
    }

    @Override
    public Player getPlayer() {
	return player;
    }

    @Override
    public EnumMap<Resource, Integer> getRequest() {
	return request;
    }

    @Override
    public EnumMap<Resource, Integer> getReward() {
	return reward;
    }

    @Override
    public UUID getID() {
	return key;
    }

}
