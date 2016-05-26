package nl.groep4.kvc.common;

import java.rmi.Remote;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Color;

public interface Lobby extends Remote{
	
	public List<Player> getConnectedPlayers();
	
	public void startSpel();
	
	public void loadSafe();
	
	public void setColor(Player pl, Color color);
	
	public void update();
	
}
