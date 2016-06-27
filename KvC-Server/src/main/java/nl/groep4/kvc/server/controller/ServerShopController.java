package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;
import java.util.Map.Entry;
import java.util.Set;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.server.model.ServerCardHolder;
import nl.groep4.kvc.server.model.ServerCosts;

/**
 * Checks if player has too much resources, takes resources and buys type of
 * buildings.
 * 
 * @author Tim
 * @version 1.0
 */
public class ServerShopController {

    private ServerKolonistenVanCatan controller;
    private ServerCardHolder holder = new ServerCardHolder();

    /**
     * Controller for the ServerShop.
     * 
     * @param serverKolonistenVanCatan
     *            Server address.
     */
    public ServerShopController(ServerKolonistenVanCatan serverKolonistenVanCatan) {
	this.controller = serverKolonistenVanCatan;
    }

    private boolean hasAllResources(Set<Entry<Resource, Integer>> set) {
	boolean hasAllResources = true;
	try {
	    for (Entry<Resource, Integer> resource : set) {
		if (controller.getTurn().getResourceAmount(resource.getKey()) < resource.getValue()) {
		    hasAllResources = false;
		}
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	    return false;
	}
	return hasAllResources;
    }

    private void takeResources(Set<Entry<Resource, Integer>> set) {
	try {
	    for (Entry<Resource, Integer> resource : set) {
		controller.getTurn().takeResource(resource.getKey(), resource.getValue());
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
	controller.updateResources();
    }

    /**
     * Buys the street.
     */
    public void buyStreet() {
	try {
	    Player who = controller.getTurn();
	    if (!controller.mapController.getValidStreetLocations().isEmpty()) {
		if (hasAllResources(ServerCosts.STREET_COSTS.entrySet())) {
		    takeResources(ServerCosts.STREET_COSTS.entrySet());
		    controller.buildStreetModus(1);
		} else {
		    who.getUpdateable(UpdateMap.class).popup("noresources");
		}
	    } else {
		who.getUpdateable(UpdateMap.class).popup("noplace");
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Buys the villages.
     */
    public void buyVillage() {
	try {
	    Player who = controller.getTurn();
	    if (!controller.mapController.getValidBuildingLocations(BuildingType.VILLAGE).isEmpty()) {
		if (hasAllResources(ServerCosts.VILLAGE_COSTS.entrySet())) {
		    takeResources(ServerCosts.VILLAGE_COSTS.entrySet());
		    controller.buildVillageModus(1);
		} else {
		    who.getUpdateable(UpdateMap.class).popup("noresources");
		}
	    } else {
		who.getUpdateable(UpdateMap.class).popup("noplace");
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Buys city.
     */
    public void buyCity() {
	try {
	    Player who = controller.getTurn();
	    if (!controller.mapController.getValidBuildingLocations(BuildingType.CITY).isEmpty()) {
		if (hasAllResources(ServerCosts.CITY_COSTS.entrySet())) {
		    takeResources(ServerCosts.CITY_COSTS.entrySet());
		    controller.buildCityModus(1);
		} else {
		    who.getUpdateable(UpdateMap.class).popup("noresources");
		}
	    } else {
		who.getUpdateable(UpdateMap.class).popup("noplace");
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Buys card.
     */
    public void buyCard() {
	try {
	    Player who = controller.getTurn();
	    if (holder.hasCards()) {
		if (hasAllResources(ServerCosts.DEVELOPMENT_CARD_COSTS.entrySet())) {
		    takeResources(ServerCosts.DEVELOPMENT_CARD_COSTS.entrySet());
		    who.addCard(holder.drawCard());
		    UpdateMap view = who.getUpdateable(UpdateMap.class);
		    view.closeOverlay();
		    controller.updateCards();
		    ;
		} else {
		    who.getUpdateable(UpdateMap.class).popup("noresources");
		}
	    } else {
		who.getUpdateable(UpdateMap.class).popup("nocards");
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }
}
