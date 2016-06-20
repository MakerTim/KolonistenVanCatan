package nl.groep4.kvc.client.debug;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import nl.groep4.kvc.client.controller.ClientRefrence;
import nl.groep4.kvc.client.controller.MapController;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.CardType;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.enumeration.TurnState;
import nl.groep4.kvc.common.enumeration.VictoryCardType;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Trade;
import nl.groep4.kvc.common.interfaces.VictoryCard;
import nl.groep4.kvc.common.util.Scheduler;

public class TestMapController extends MapController {

    public TestMapController(SceneMap view) {
	super(null, view);
	Scheduler.runAsyncLater(() -> {
	    List<Trade> trades = new ArrayList<>();
	    trades.add(new Trade() {
		private static final long serialVersionUID = 1L;

		@Override
		public Player getPlayer() {
		    return ClientRefrence.getThePlayer();
		}

		@Override
		public EnumMap<Resource, Integer> getRequest() {
		    java.util.Map<Resource, Integer> request = new HashMap<>();
		    request.put(Resource.WOOL, 10);
		    return new EnumMap<Resource, Integer>(request);
		}

		@Override
		public EnumMap<Resource, Integer> getReward() {
		    java.util.Map<Resource, Integer> request = new HashMap<>();
		    request.put(Resource.BRICK, 1);
		    return new EnumMap<Resource, Integer>(request);
		}
	    });
	    try {
		view.updateTrades(trades);
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}, 3000L);
	Scheduler.runAsyncLater(() -> {
	    try {
		view.updateRound(25);
		view.updateScore(ClientRefrence.getThePlayer(), 69);
		view.updateTurn(ClientRefrence.getThePlayer(), TurnState.WAITING);
		view.updateConfig();
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}, 3000L);
	Scheduler.runAsyncLater(() -> {
	    try {
		java.util.Map<Resource, Integer> resources = new HashMap<>();
		resources.put(Resource.WOOL, 10);
		view.updateStreetCosts(new EnumMap<>(resources));
		resources = new HashMap<>();
		resources.put(Resource.WOOD, 10);
		view.updateCityCosts(new EnumMap<>(resources));
		resources = new HashMap<>();
		resources.put(Resource.WHEAT, 10);
		view.updateVillageCosts(new EnumMap<>(resources));
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}, 3000L);
	Scheduler.runAsyncLater(() -> {
	    try {
		java.util.Map<Resource, Integer> resources = new HashMap<>();
		for (Resource resource : Resource.values()) {
		    resources.put(resource, new Random().nextInt(100));
		}
		view.updateStock(ClientRefrence.getThePlayer(), new EnumMap<>(resources));
		List<Card> cards = new ArrayList<>();
		for (CardType card : CardType.values()) {
		    for (VictoryCardType victory : VictoryCardType.values()) {
			if (card == CardType.VICTORY) {
			    cards.add(new VictoryCard() {
				private static final long serialVersionUID = 1L;

				@Override
				public CardType getType() {
				    return card;
				}

				@Override
				public VictoryCardType getVictoryType() {
				    return victory;
				}
			    });
			} else {
			    cards.add(new Card() {
				private static final long serialVersionUID = 1L;

				@Override
				public CardType getType() {
				    return card;
				}
			    });
			}
		    }
		}
		view.updateStock(ClientRefrence.getThePlayer(), cards);
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}, 3000L);
    }
}
