package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;

public interface Dice extends Remote {
    public void throwDice();

    public int getValue();
}
