package nl.groep4.kvc.common.interfaces;

import nl.groep4.kvc.common.map.Map;

public interface KolonistenVanCatan {
    /**
     * 
     * @return gets current round
     */
    public int getRound();

    public void nextRound();

    public Map getMap();

}
