package nl.groep4.kvc.common.interfaces;

import com.sun.istack.internal.Nullable;

import nl.groep4.kvc.common.Player;

public interface Ownable {

	@Nullable
	public Player getOwner();

	public void setOwner(Player player);

}
