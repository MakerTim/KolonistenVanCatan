package nl.groep4.kvc.common.interfaces;

import java.io.Serializable;

import nl.groep4.kvc.common.enumeration.CardType;

/**
 * Retrieves card type and displays to player
 * 
 * @version 1.0
 * @author Tim
 **/
public interface Card extends Serializable {
    /**
     * @return Gets card type
     */
    public CardType getType();

}
