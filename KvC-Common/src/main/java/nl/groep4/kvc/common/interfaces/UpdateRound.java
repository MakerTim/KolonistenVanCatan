package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.enumeration.TurnState;

public interface UpdateRound {

    public void updateRound(int round) throws RemoteException;

    public void updateTurn(Player who, TurnState what) throws RemoteException;
}
