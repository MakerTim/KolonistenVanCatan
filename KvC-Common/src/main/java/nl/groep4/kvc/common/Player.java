package nl.groep4.kvc.common;

import java.io.Serializable;
import java.rmi.Remote;

import nl.groep4.kvc.common.enumeration.Color;

public interface Player extends Remote, Serializable {

    public String getUsername();

    public Color getColor();

    public void setColor(Color color);

}
