package nl.groep4.kvc.server.model;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Updatable;

public class ServerPlayer implements Player {

    private String username;
    private Updatable<?> updatable;
    private Color color;

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
	    try {
		return other.getUsername().equals(getUsername());
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	}
	return super.equals(obj);
    }

    @Override
    public Color getColor() throws RemoteException {
	return color;
    }

    @Override
    public void setColor(Color color) throws RemoteException {
	this.color = color;
    }
}
