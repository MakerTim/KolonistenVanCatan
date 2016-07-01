package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Trade;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.server.model.ServerTrade;

/**
 * Adds trades, removes trades and updates trades.
 * 
 * @author Tim
 * @version 1.0
 */
public class ServerTradeController {

    private ServerKolonistenVanCatan controller;

    /**
     * Controls server.
     * 
     * @param serverKolonistenVanCatan
     *            Server value.
     */
    public ServerTradeController(ServerKolonistenVanCatan serverKolonistenVanCatan) {
	this.controller = serverKolonistenVanCatan;
    }

    /**
     * Adds trades with player name.
     * 
     * @param player
     *            Name of player that offered the trade.
     * @param request
     *            The resources the player requests.
     * @param reward
     *            The resources the player wants in return.
     */
    public void addTrade(Player player, Map<Resource, Integer> request, Map<Resource, Integer> reward) {
	try {
	    System.out.printf("Added new trade! %s [\t%s : %s]\n", player.getUsername(), request.toString(),
		    reward.toString());
	    controller.getTrades().add(new ServerTrade(player, request, reward));
	    validateTrades();
	    controller.updateTrades();
	    player.getUpdateable(UpdateMap.class).closeOverlay();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	} catch (NullPointerException ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Trades the resources with one another and makes sure players can't trade
     * with themselves.
     * 
     * @param trade
     *            The trade which includes id.
     * @param player
     *            The player from which the trade is.
     */
    public void onTrade(Trade trade, Player player) {
	try {
	    if (trade == null) {
		try {
		    player.getUpdateable().popup("notrade");
		    controller.updateTrades();
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		}
		return;
	    }
	    if (trade.getPlayer().equals(player)) {
		try {
		    player.getUpdateable().popup("tradeself");
		    removeTrade(trade.getID());
		    controller.updateTrades();
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		}
		return;
	    }
	    if (hasAllResources(trade.getPlayer(), trade)) {
		if (player == null) {
		    System.out.printf("Bank traded with %s\n", "", trade.getPlayer().getUsername());
		    for (Entry<Resource, Integer> reward : trade.getReward().entrySet()) {
			trade.getPlayer().takeResource(reward.getKey(), reward.getValue());
		    }
		    for (Entry<Resource, Integer> requested : trade.getRequest().entrySet()) {
			trade.getPlayer().giveResource(requested.getKey(), requested.getValue());
		    }
		} else {
		    System.out.printf("Player %s traded with %s\n", player.getUsername(),
			    trade.getPlayer().getUsername());
		    if (hasAllResources(player, trade)) {
			for (Entry<Resource, Integer> reward : trade.getReward().entrySet()) {
			    trade.getPlayer().takeResource(reward.getKey(), reward.getValue());
			    player.giveResource(reward.getKey(), reward.getValue());
			}
			for (Entry<Resource, Integer> requested : trade.getRequest().entrySet()) {
			    player.takeResource(requested.getKey(), requested.getValue());
			    trade.getPlayer().giveResource(requested.getKey(), requested.getValue());
			}
		    } else {
			player.getUpdateable().popup("noresources");
		    }
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

    /**
     * Checks if player has the resource to make a trade.
     * 
     * @param trade
     *            The trade which includes id.
     * @return true When player has the resources in stock.
     */
    public boolean hasAllResources(Player pl, Trade trade) {
	for (Entry<Resource, Integer> reward : trade.getReward().entrySet()) {
	    try {
		if (!pl.hasResource(reward.getKey(), reward.getValue())) {
		    return false;
		}
	    } catch (RemoteException e) {
		e.printStackTrace();
		return false;
	    }
	}
	return true;
    }

    /**
     * Validates trades. Checks when it's a bank trade, then it automatically
     * trades 4:1.
     */
    public void validateTrades() {
	for (Trade trade : new ArrayList<>(controller.getTrades())) {
	    if (!hasAllResources(trade.getPlayer(), trade)) {
		removeTrade(trade.getID());
		continue;
	    }
	    boolean isBankTrade = true;
	    int i = 0;
	    for (Entry<Resource, Integer> reward : trade.getReward().entrySet()) {
		if (reward.getValue() == 0) {
		    continue;
		}
		if (reward.getValue() != 4) {
		    isBankTrade = false;
		    break;
		}
		i += reward.getValue();
	    }
	    for (Entry<Resource, Integer> request : trade.getRequest().entrySet()) {
		if (request.getValue() == 0) {
		    continue;
		}
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

    /**
     * Removes the trade.
     * 
     * @param key
     *            The id of the trade.
     */
    public void removeTrade(UUID key) {
	Iterator<Trade> tradesIT = controller.getTrades().iterator();
	while (tradesIT.hasNext()) {
	    Trade trade = tradesIT.next();
	    if (trade.getID().equals(key)) {
		tradesIT.remove();
	    }
	}
	validateTrades();
    }

    /**
     * Gets the trade.
     * 
     * @param tradeKey
     *            The id of the trade.
     * @return The trades.
     */
    public Trade getTrade(UUID tradeKey) {
	return controller.getTrades().stream().filter(trade -> trade.getID().equals(tradeKey)).findFirst().orElse(null);
    }
}
