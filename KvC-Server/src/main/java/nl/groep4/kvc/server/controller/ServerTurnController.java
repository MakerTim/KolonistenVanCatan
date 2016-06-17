package nl.groep4.kvc.server.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.enumeration.SelectState;
import nl.groep4.kvc.common.enumeration.TurnState;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.util.CollectionUtil;

public class ServerTurnController {

    private KolonistenVanCatan controller;

    public ServerTurnController(KolonistenVanCatan serverKolonistenVanCatan) {
	this.controller = serverKolonistenVanCatan;
    }

    public void initTurnStreet(Building building) {
	try {
	    System.out.printf("Initial turn for %s\n", controller.getPlayersOrded().get(0).getUsername());
	    Set<Street> availbleStreets = new HashSet<>();
	    Tile[] tiles = building.getConnectedTiles();
	    for (Tile tile : tiles) {
		for (Point point : Point.values()) {
		    if (building.equals(tile.getBuilding(point))) {
			availbleStreets
				.add(tile.getStreet(CollectionUtil.getInRange(Direction.values(), point.ordinal())));
			availbleStreets.add(
				tile.getStreet(CollectionUtil.getInRange(Direction.values(), point.ordinal() + 1)));
		    }
		}
	    }
	    for (Player pl : controller.getPlayers()) {
		pl.getUpdateable(UpdateMap.class).updateTurn(controller.getTurn(), TurnState.BUILDING_STREET);
	    }
	    UpdateMap view = controller.getTurn().getUpdateable(UpdateMap.class);
	    view.highlightStreets(availbleStreets);
	    view.setSelectable(SelectState.STREET);
	    controller.getTurn().addRemainingStreets(1);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void initTurnBuilding() {
	try {
	    Set<Building> availbleBuidlings = new HashSet<>();
	    for (Building building : controller.getMap().getAllBuildings()) {
		availbleBuidlings.add(building);
	    }
	    for (Player pl : controller.getPlayers()) {
		pl.getUpdateable(UpdateMap.class).updateTurn(controller.getTurn(), TurnState.BUILDING_BUILDING);
	    }
	    UpdateMap view = controller.getTurn().getUpdateable(UpdateMap.class);
	    view.highlightBuildings(availbleBuidlings, BuildingType.VILLAGE);
	    view.setSelectable(SelectState.BUILDING);
	    controller.getTurn().addRemainingBuidlings(1);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void onTurn() {
	try {
	    System.out.println(controller.getTurn().getUsername() + "'s turn is now.");
	    {
		UpdateMap view = controller.getTurn().getUpdateable(UpdateMap.class);
		view.openDicePane(true);
	    }
	    for (int i = 1; i < controller.getPlayersOrded().size(); i++) {
		try {
		    UpdateMap view = controller.getPlayersOrded().get(i).getUpdateable(UpdateMap.class);
		    view.openDicePane(false);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    }
	    for (Player pl : controller.getPlayers()) {
		try {
		    UpdateMap view = pl.getUpdateable(UpdateMap.class);
		    view.updateRound(controller.getRound());
		    view.updateTurn(controller.getTurn(), TurnState.THROWING_DICE);
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

    public void fixButtons() {
	try {
	    controller.getTurn().getUpdateable(UpdateMap.class).unblockActions();
	    List<Player> orderd = controller.getPlayersOrded();
	    for (int i = 1; i < orderd.size(); i++) {
		orderd.get(i).getUpdateable(UpdateMap.class).blockActions();
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

}
