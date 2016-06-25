package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateMap;

/**
 * Uses all type of cards and executes them.
 * 
 * @author Tim
 * @version 1.0
 */
public class ServerCardController {
    private ServerKolonistenVanCatan controller;

    /**
     * Gets current controller.
     * 
     * @param serverKolonistenVanCatan
     *            Current controller.
     */
    public ServerCardController(ServerKolonistenVanCatan serverKolonistenVanCatan) {
	this.controller = serverKolonistenVanCatan;
    }

    /**
     * Uses selected card.
     * 
     * @param from
     *            From whom the card is.
     * @param card
     *            Type of card that will be used.
     */
    public void useCard(Player from, Card card) {
	try {
	    if (!from.useCard(card)) {
		System.out.printf("Player %s used a card that he shouldn have.\n", from.getUsername());
		return;
	    }
	    switch (card.getType()) {
	    case FREE_STREETS:
		controller.buildStreetModus(3);
		break;
	    case INVENTION:
		from.getUpdateable(UpdateMap.class).openInventionPane();
		break;
	    case KNIGHT:
		controller.moveBanditModus();
		break;
	    case MONOPOLY:
		from.getUpdateable(UpdateMap.class).openMonopolyPane();
		break;
	    case VICTORY:
		return;
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
	controller.updateCards();
    }

    /**
     * Uses invention.
     * 
     * @param who
     *            Player to give resources.
     * @param resource
     *            Resource to give.
     */
    public void useInvention(Player who, Resource resource) {
	try {
	    who.giveResource(resource, 2);
	    who.getUpdateable(UpdateMap.class).closeOverlay();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
	controller.updateResources();
    }

    /**
     * When this carded is pulled player gets one type of resource of all
     * players.
     * 
     * @param who
     *            Player who gets the resources.
     * @param resource
     *            Selected type of resource.
     */
    public void targetMonopoly(Player who, Resource resource) {
	for (Player pl : controller.getPlayers()) {
	    try {
		int am = pl.getResourceAmount(resource);
		pl.takeResource(resource, am);
		who.giveResource(resource, am);
		who.getUpdateable(UpdateMap.class).closeOverlay();
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}
	controller.updateResources();
    }
}
