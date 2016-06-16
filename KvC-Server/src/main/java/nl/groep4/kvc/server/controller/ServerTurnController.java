package nl.groep4.kvc.server.controller;

import nl.groep4.kvc.common.enumeration.TurnState;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateMap;

public class ServerTurnController {

    private KolonistenVanCatan controller;

    public ServerTurnController(KolonistenVanCatan serverKolonistenVanCatan) {
	this.controller = serverKolonistenVanCatan;
    }

    public void onTurn() {
	try {
	    System.out.println(controller.getPlayersOrded().get(0).getUsername() + "'s turn is now.");
	    {
		UpdateMap view = controller.getPlayersOrded().get(0).getUpdateable(UpdateMap.class);
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

}
