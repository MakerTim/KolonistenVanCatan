package nl.groep4.kvc.server.controller;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.map.TileResource;
import nl.groep4.kvc.common.util.Scheduler;

public class ServerMapController {

    private ServerKolonistenVanCatan controller;

    public ServerMapController(ServerKolonistenVanCatan serverKolonistenVanCatan) {
	this.controller = serverKolonistenVanCatan;
    }

    public void distribute() {
	try {
	    System.out.printf("Giving players resources for tiles with number '%d'\n",
		    controller.getLastThrow().getValue());
	    for (Tile tile : controller.getMap().getTiles()) {
		if (tile instanceof TileResource) {
		    TileResource tileResource = (TileResource) tile;
		    if (!tileResource.hasRover() && tileResource.getNumber() == controller.getLastThrow().getValue()) {
			Resource resource = tileResource.getResource();
			for (Building building : tile.getBuildings()) {
			    if (building != null && building.getOwner() != null) {
				Player pl = building.getOwner();
				switch (building.getBuildingType()) {
				case CITY:
				    pl.giveResource(resource);
				case VILLAGE:
				    pl.giveResource(resource);
				case EMPTY:
				    break;
				}
			    }
			}
		    }
		}
	    }
	    List<Runnable> runs = new ArrayList<>();
	    for (Player pl : controller.getPlayers()) {
		runs.add(() -> {
		    for (Player player : controller.getPlayers()) {
			try {
			    pl.getUpdateable(UpdateMap.class).updateStock(player, player.getResources());
			} catch (Exception ex) {
			    ex.printStackTrace();
			}
		    }
		});
	    }
	    Scheduler.runAsyncdSync(runs);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
}
