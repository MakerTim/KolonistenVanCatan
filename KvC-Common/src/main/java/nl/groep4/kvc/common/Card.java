package nl.groep4.kvc.common;

import java.rmi.Remote;

import nl.groep4.kvc.common.enumeration.CardType;

public interface Card extends Remote {

    public CardType getType();

    public void use(Player player);

}
