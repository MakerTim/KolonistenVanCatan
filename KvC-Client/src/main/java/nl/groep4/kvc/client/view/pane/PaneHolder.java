package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;

import javafx.scene.layout.Pane;

/**
 * Interface for making code have some guidline
 * 
 * @version 1.0
 * @author Tim
 **/
public interface PaneHolder {

    public Pane getPane() throws RemoteException;

}
