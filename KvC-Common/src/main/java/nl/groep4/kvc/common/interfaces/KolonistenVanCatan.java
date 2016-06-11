package nl.groep4.kvc.common.interfaces;

import java.util.List;

import nl.groep4.kvc.common.map.Map;

public interface KolonistenVanCatan {
    /**
     * 
     * @return gets current round
     */
    public int getRound();

    public void nextRound();

    public Map getMap();

    public List<Player> getPlayers();

}
