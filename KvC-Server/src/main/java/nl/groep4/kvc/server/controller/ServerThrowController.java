package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.TurnState;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Throw;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.common.util.Scheduler;
import nl.groep4.kvc.server.model.ServerThrow;

/**
 * Gets throw and updates throw.
 * 
 * @author Tim
 * @version 1.0
 */
public class ServerThrowController {

    private ServerKolonistenVanCatan controller;

    private Throw lastThrow;

    /**
     * Sets lastThrow into new ServerThrow.
     * 
     * @param serverKolonistenVanCatan
     *            Current controller.
     */
    public ServerThrowController(ServerKolonistenVanCatan serverKolonistenVanCatan) {
	this.controller = serverKolonistenVanCatan;
	try {
	    lastThrow = new ServerThrow();
	    lastThrow.throwDice();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Gets throw.
     * 
     * @return The last throw.
     */
    public Throw getThrow() {
	return lastThrow;
    }

    /**
     * Updates throw.
     */
    public void updateThrow() {
	List<Runnable> runs = new ArrayList<>();
	for (Player player : controller.getPlayers()) {
	    runs.add(() -> {
		try {
		    player.getUpdateable(UpdateMap.class).updateDices(lastThrow.getDiceLeft(),
			    lastThrow.getDiceRight());
		    Scheduler.runAsyncLater(() -> controller.updateState(TurnState.WAITING), 2000);
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		}
	    });
	}
	Scheduler.runAsyncdSync(runs);
	Scheduler.runAsyncLater(() -> {
	    try {
		if (lastThrow.isBanditThrow()) {
		    controller.moveBanditModus();
		} else {
		    controller.distribute();
		}
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	});
    }
}
