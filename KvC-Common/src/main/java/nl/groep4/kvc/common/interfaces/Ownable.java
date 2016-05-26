package nl.groep4.kvc.common.interfaces;

import nl.groep4.kvc.common.Player;

public interface Ownable {

	public Player getOwner();

	public void setOwner(Player player);

}
