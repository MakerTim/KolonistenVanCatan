package nl.groep4.kvc.server.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.GameState;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.enumeration.SelectState;
import nl.groep4.kvc.common.enumeration.TurnState;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.map.TileResource;
import nl.groep4.kvc.common.util.CollectionUtil;
import nl.groep4.kvc.common.util.Scheduler;

public class ServerTurnController {

    private ServerKolonistenVanCatan controller;

    public ServerTurnController(ServerKolonistenVanCatan serverKolonistenVanCatan) {
	this.controller = serverKolonistenVanCatan;
    }

    // TODO: Highlight streets method
    // TODO: Highlight buildings method
    // TODO: buy x
    // TODO: buy card
    // TODO: use card
    // TODO: trading
    // TODO: Rover verplaatsen
    // TODO: Punten berekenen
    // TODO: Pause serversided - warning if niet aan beurt

    public void initTurnStreet(Building building) {
	try {
	    if (controller.getState() != GameState.INIT) {
		return;
	    }
	    Player pl = controller.getTurn();
	    System.out.printf("Initial turn for %s\n", pl.getUsername());
	    for (Tile tile : building.getConnectedTiles()) {
		if (tile instanceof TileResource) {
		    TileResource resource = (TileResource) tile;
		    pl.giveResource(resource.getResource());
		}
	    }
	    Set<Street> availbleStreets = new HashSet<>();
	    for (Tile tile : building.getConnectedTiles()) {
		for (Point point : Point.values()) {
		    if (building.equals(tile.getBuilding(point))) {
			availbleStreets
				.add(tile.getStreet(CollectionUtil.getInRange(Direction.values(), point.ordinal())));
			availbleStreets.add(
				tile.getStreet(CollectionUtil.getInRange(Direction.values(), point.ordinal() + 1)));
		    }
		}
	    }
	    List<Runnable> runs = new ArrayList<>();
	    for (Player player : controller.getPlayers()) {
		runs.add(() -> {
		    try {
			UpdateMap view = player.getUpdateable(UpdateMap.class);
			view.updateTurn(pl, TurnState.BUILDING_STREET);
			view.updateStock(pl, pl.getResources());
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		});
	    }
	    Scheduler.runAsyncdSync(runs);
	    UpdateMap view = controller.getTurn().getUpdateable(UpdateMap.class);
	    controller.highlightStreets(pl, availbleStreets);
	    view.setSelectable(SelectState.STREET);
	    pl.addRemainingStreets(1);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void initTurnBuilding() {
	try {
	    if (controller.getState() != GameState.INIT) {
		return;
	    }
	    List<Runnable> runs = new ArrayList<>();
	    for (Player pl : controller.getPlayers()) {
		runs.add(() -> {
		    try {
			pl.getUpdateable(UpdateMap.class).updateTurn(controller.getTurn(), TurnState.BUILDING_BUILDING);
		    } catch (Exception ex) {
			ex.printStackTrace();
		    }
		});
	    }
	    Scheduler.runAsyncdSync(runs);
	    UpdateMap view = controller.getTurn().getUpdateable(UpdateMap.class);
	    controller.highlightBuildings(controller.getTurn(), BuildingType.VILLAGE);
	    view.setSelectable(SelectState.BUILDING);
	    controller.getTurn().addRemainingBuidlings(1);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void onTurn() {
	try {
	    if (controller.getState() != GameState.IN_GAME) {
		return;
	    }
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
	    List<Runnable> runs = new ArrayList<>();
	    for (Player player : orderd) {
		runs.add(() -> {
		    try {
			player.getUpdateable(UpdateMap.class).blockActions();
		    } catch (Exception ex) {
			ex.printStackTrace();
		    }
		});
	    }
	    Scheduler.runAsyncdSync(runs);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

}
