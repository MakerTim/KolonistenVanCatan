package nl.groep4.kvc.client.view.scene;

import java.rmi.RemoteException;

import javafx.scene.Scene;

/**
 * Interface for making code have some guidline
 * 
 * @version 1.0
 * @author Tim
 **/
public interface SceneHolder {

    public Scene getScene() throws RemoteException;

}
