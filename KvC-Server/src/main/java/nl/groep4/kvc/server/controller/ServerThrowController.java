package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Throw;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.common.util.Scheduler;
import nl.groep4.kvc.server.model.ServerThrow;

public class ServerThrowController {

    private KolonistenVanCatan controller;

    private Throw lastThrow;

    public ServerThrowController(KolonistenVanCatan serverKolonistenVanCatan) {
	this.controller = serverKolonistenVanCatan;
	try {
	    lastThrow = (Throw) UnicastRemoteObject.exportObject(new ServerThrow(), 0);
	    lastThrow.throwDice();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    public Throw getThrow() {
	return lastThrow;
    }

    public void updateThrow() {
	try {
	    List<Runnable> runs = new ArrayList<>();
	    for (Player player : controller.getPlayers()) {
		runs.add(() -> {
		    try {
			player.getUpdateable(UpdateMap.class).updateDices(lastThrow.getDiceLeft(),
				lastThrow.getDiceRight());
		    } catch (RemoteException ex) {
			ex.printStackTrace();
		    }
		});
	    }
	    Scheduler.runAsyncdSync(runs);
	    Scheduler.runAsyncLater(() -> {
		try {
		    controller.distrube();
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		}
	    }, 1700L);
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

}
