package nl.groep4.kvc.common.interfaces;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.UUID;

import nl.groep4.kvc.common.enumeration.Resource;

/**
 * Interface of everything a Trade can do
 * 
 * @author Tim
 * @version 1.0
 */
public interface Trade extends Serializable {

    /**
     * Gets a unique ID
     * 
     * @return a unique ID
     */
    public UUID getID();

    /**
     * Gets the player who wants to trade
     * 
     * @return the player who wants to trade
     */
    public Player getPlayer();

    /**
     * Gets a map of the request of the trade
     * 
     * @return the request of the trade
     */
    public EnumMap<Resource, Integer> getRequest();

    /**
     * Gets the map of the reward of the trade
     * 
     * @return the reward of the trade
     */
    public EnumMap<Resource, Integer> getReward();

}
