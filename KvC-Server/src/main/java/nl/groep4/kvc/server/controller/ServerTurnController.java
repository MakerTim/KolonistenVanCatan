package nl.groep4.kvc.server.controller;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.TurnState;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.common.map.Street;

public class ServerTurnController {

    private KolonistenVanCatan controller;

    public ServerTurnController(KolonistenVanCatan serverKolonistenVanCatan) {
	this.controller = serverKolonistenVanCatan;
    }

    public void initTurn() {
	try {
	    System.out.printf("Initial turn for %s", controller.getPlayersOrded().get(0).getUsername());
	    List<Street> availbleStreets = new ArrayList<>();
	    for (Street street : controller.getMap().getAllStreets()) {
		if (street.getOwner() == null) {
		    availbleStreets.add(street);
		}
	    }
	    controller.getTurn().getUpdateable(UpdateMap.class).highlightStreets(availbleStreets);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void onTurn() {
	try {
	    System.out.println(controller.getTurn().getUsername() + "'s turn is now.");
	    {
		UpdateMap view = controller.getTurn().getUpdateable(UpdateMap.class);
		view.unblockActions();
		view.openDicePane(true);
	    }
	    for (int i = 1; i < controller.getPlayersOrded().size(); i++) {
		try {
		    UpdateMap view = controller.getPlayersOrded().get(i).getUpdateable(UpdateMap.class);
		    view.blockActions();
		    view.openDicePane(false);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    }
	    for (Player pl : controller.getPlayers()) {
		try {
		    UpdateMap view = pl.getUpdateable(UpdateMap.class);
		    view.updateRound(controller.getRound());
		    view.updateTurn(controller.getPlayersOrded().get(0), TurnState.THROWING_DICE);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void endGame() {
	// TODO: ENDGAME?
    }

}
