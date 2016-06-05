package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;

import nl.groep4.kvc.common.enumeration.CardType;

/**
 * Retrieves card type and displays to player
 * 
 * @version 1.0
 * @author Tim
 **/
public interface Card extends Remote {
    /**
     * 
     * @return Gets card type
     */
    public CardType getType();

}
