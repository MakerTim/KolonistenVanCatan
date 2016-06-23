package nl.groep4.kvc.common.interfaces;

import nl.groep4.kvc.common.enumeration.VictoryCardType;

/**
 * Retrieves victory card type.
 * 
 * @author Tim
 * @version 1.0
 */
public interface VictoryCard extends Card {

    /**
     * Gets victory type.
     * 
     * @return Victory card type.
     */
    public VictoryCardType getVictoryType();

}
