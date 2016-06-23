package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateMap;

public class ServerCardController {
    private ServerKolonistenVanCatan controller;

    public ServerCardController(ServerKolonistenVanCatan serverKolonistenVanCatan) {
	this.controller = serverKolonistenVanCatan;
    }

    public void useCard(Player from, Card card) {
	try {
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
	    from.useCard(card);
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
	controller.updateCards();
    }

    public void useInvention(Player who, Resource resource) {
	try {
	    who.giveResource(resource, 2);
	    who.getUpdateable(UpdateMap.class).closeOverlay();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
	controller.updateResources();
    }

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
