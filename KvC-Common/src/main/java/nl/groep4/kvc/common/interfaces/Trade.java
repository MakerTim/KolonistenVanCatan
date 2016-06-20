package nl.groep4.kvc.common.interfaces;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.UUID;

import nl.groep4.kvc.common.enumeration.Resource;

public interface Trade extends Serializable {

    public UUID getID();

    public Player getPlayer();

    public EnumMap<Resource, Integer> getRequest();

    public EnumMap<Resource, Integer> getReward();

}
