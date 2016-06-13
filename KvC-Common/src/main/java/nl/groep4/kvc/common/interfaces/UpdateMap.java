package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.map.Map;

public interface UpdateMap extends Updatable<Map>, UpdateStock {

    public void closeOverlay() throws RemoteException;

}
