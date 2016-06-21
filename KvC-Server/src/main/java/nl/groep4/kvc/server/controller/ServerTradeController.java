package nl.groep4.kvc.server.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Trade;
import nl.groep4.kvc.server.model.ServerTrade;

public class ServerTradeController {

    private ServerKolonistenVanCatan controller;

    private List<Trade> trades = new ArrayList<>();

    public ServerTradeController(ServerKolonistenVanCatan serverKolonistenVanCatan) {
	this.controller = serverKolonistenVanCatan;
    }

    public List<Trade> getTrades() {
	return trades;
    }

    public void addTrade(Player player, Map<Resource, Integer> request, Map<Resource, Integer> reward) {
	trades.add(new ServerTrade(player, request, reward));
	validateTrades();
	controller.updateTrades();
    }

    public void onTrade(Trade trade, Player with) {
	// TODO: Handle trade
	if (with == null) {
	    // Trade with bank
	} else {

	}
    }

    public void validateTrades() {
	// TODO: validate all trades
    }

    public void removeTrade(UUID key) {
	Iterator<Trade> tradesIT = trades.iterator();
	while (tradesIT.hasNext()) {
	    Trade trade = tradesIT.next();
	    if (trade.getID().equals(key)) {
		tradesIT.remove();
	    }
	}
    }
}
