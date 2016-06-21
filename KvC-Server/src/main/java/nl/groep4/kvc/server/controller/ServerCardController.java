package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.Player;

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
		// TODO: pak 2 grondstoffen = new pane
		break;
	    case KNIGHT:
		controller.moveBanditModus();
		break;
	    case MONOPOLY:
		// TODO: pak grondstof van iedereen af = new pane
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
}
