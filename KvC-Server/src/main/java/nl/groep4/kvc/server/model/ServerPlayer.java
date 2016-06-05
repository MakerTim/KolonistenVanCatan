package nl.groep4.kvc.server.model;

import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Updatable;

public class ServerPlayer implements Player {

    private static final long serialVersionUID = -133766669323041996L;

    private String username;
    private Updatable<?> updatable;

    public ServerPlayer(String username) {
	this.username = username;
    }

    @Override
    public String getUsername() {
	return username;
    }

    @Override
    public void registerUpdateable(Updatable<?> updatable) {
	this.updatable = updatable;
    }

    @Override
    public Updatable<?> getUpdateable() {
	return updatable;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Player) {
	    Player other = (Player) obj;
	    return other.getUsername().equals(getUsername());
	}
	return super.equals(obj);
    }
}
