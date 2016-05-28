package nl.groep4.kvc.common;

import java.rmi.Remote;

import nl.groep4.kvc.common.enumeration.Color;

public interface Player extends Remote {

	public String getUsername();

	public Color getColor();

	public void setColor(Color color);

}
