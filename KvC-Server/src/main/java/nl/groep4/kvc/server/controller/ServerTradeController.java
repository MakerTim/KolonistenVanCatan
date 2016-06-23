package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Trade;
import nl.groep4.kvc.common.interfaces.UpdateMap;
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
	try {
	    System.out.printf("Added new trade! %s [\t%s : %s]\n", player.getUsername(), request.toString(),
		    reward.toString());
	    trades.add(new ServerTrade(player, request, reward));
	    validateTrades();
	    controller.updateTrades();
	    player.getUpdateable(UpdateMap.class).closeOverlay();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    public void onTrade(Trade trade, Player player) {
	try {
	    if (trade == null) {
		try {
		    player.getUpdateable().popup("notrade");
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		}
	    }
	    if (player == null) {
		for (Entry<Resource, Integer> reward : trade.getReward().entrySet()) {
		    trade.getPlayer().takeResource(reward.getKey(), reward.getValue());
		}
		for (Entry<Resource, Integer> requested : trade.getRequest().entrySet()) {
		    trade.getPlayer().giveResource(requested.getKey(), requested.getValue());
		}
	    } else {
		for (Entry<Resource, Integer> reward : trade.getReward().entrySet()) {
		    trade.getPlayer().takeResource(reward.getKey(), reward.getValue());
		    player.giveResource(reward.getKey(), reward.getValue());
		}
		for (Entry<Resource, Integer> requested : trade.getRequest().entrySet()) {
		    player.takeResource(requested.getKey(), requested.getValue());
		    trade.getPlayer().giveResource(requested.getKey(), requested.getValue());
		}
	    }
	    removeTrade(trade.getID());
	    validateTrades();
	    controller.updateResources();
	    controller.updateTrades();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void validateTrades() {
	for (Trade trade : new ArrayList<>(trades)) {
	    boolean validTrade = true;
	    for (Entry<Resource, Integer> reward : trade.getReward().entrySet()) {
		try {
		    if (!trade.getPlayer().hasResource(reward.getKey(), reward.getValue())) {
			validTrade = false;
		    }
		} catch (RemoteException e) {
		    e.printStackTrace();
		    validTrade = false;
		}
	    }
	    if (!validTrade) {
		removeTrade(trade.getID());
		continue;
	    }
	    boolean isBankTrade = true;
	    int i = 0;
	    for (Entry<Resource, Integer> reward : trade.getReward().entrySet()) {
		if (reward.getValue() != 4) {
		    isBankTrade = false;
		    break;
		}
		i += reward.getValue();
	    }
	    for (Entry<Resource, Integer> request : trade.getRequest().entrySet()) {
		if (request.getValue() != 1) {
		    isBankTrade = false;
		    break;
		}
		i -= request.getValue() * 4;
	    }
	    if (isBankTrade && i == 0) {
		onTrade(trade, null);
	    }
	}
    }

    public void removeTrade(UUID key) {
	Iterator<Trade> tradesIT = trades.iterator();
	while (tradesIT.hasNext()) {
	    Trade trade = tradesIT.next();
	    if (trade.getID().equals(key)) {
		tradesIT.remove();
	    }
	}
	validateTrades();
    }

    public Trade getTrade(UUID tradeKey) {
	return trades.stream().filter(trade -> trade.getID().equals(tradeKey)).findFirst().orElse(null);
    }
}
