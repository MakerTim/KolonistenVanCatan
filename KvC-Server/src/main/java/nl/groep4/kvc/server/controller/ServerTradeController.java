package nl.groep4.kvc.server.controller;

import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.interfaces.Trade;

public class ServerTradeController {

    private ServerKolonistenVanCatan controller;

    private List<Trade> trades = new ArrayList<>();

    public ServerTradeController(ServerKolonistenVanCatan serverKolonistenVanCatan) {
	this.controller = serverKolonistenVanCatan;
    }

    public List<Trade> getTrades() {
	return trades;
    }

    public void addTrade() {

    }

    // TODO: Add trade
    // TODO: Handle trade
    // TODO: remove/validate trade

}
