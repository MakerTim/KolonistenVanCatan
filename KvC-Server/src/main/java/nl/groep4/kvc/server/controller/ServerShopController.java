package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;
import java.util.Map.Entry;

import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.server.model.ServerCosts;

public class ServerShopController {

    private ServerKolonistenVanCatan controller;

    public ServerShopController(ServerKolonistenVanCatan serverKolonistenVanCatan) {
	this.controller = serverKolonistenVanCatan;
    }

    public void buyStreet() {
	boolean hasAllResources = true;
	Player who = controller.getTurn();
	try {
	    for (Entry<Resource, Integer> resource : ServerCosts.STREET_COSTS.entrySet()) {
		if (who.getResourceAmount(resource.getKey()) < resource.getValue()) {
		    hasAllResources = false;
		}
	    }
	    if (hasAllResources) {
		for (Entry<Resource, Integer> resource : ServerCosts.STREET_COSTS.entrySet()) {
		    who.takeResource(resource.getKey(), resource.getValue());
		}
		who.addRemainingStreets(1);
		controller.highlightStreet(who);
	    } else {
		who.getUpdateable(UpdateMap.class).popup("noresources");
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    public void buyVillage() {
	// TODO: buy village
    }

    public void buyCity() {
	// TODO: buy city
    }

}
