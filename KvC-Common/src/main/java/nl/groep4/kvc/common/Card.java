package nl.groep4.kvc.common;

import java.rmi.Remote;

import nl.groep4.kvc.common.enumeration.CardType;

/**
 * Retrieves card type and displays to player
 * 
 * @version 1.0
 * @author Tim
 **/
public interface Card extends Remote {

    public CardType getType();

    public void use(Player player);

}
