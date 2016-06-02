package nl.groep4.kvc.client.view.scene;

import java.rmi.RemoteException;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import nl.groep4.kvc.client.util.SceneUtil;

public class SceneMap implements SceneHolder {

    @Override
    public Scene getScene() throws RemoteException {
	/* Build layer for the design */
	Pane boardPane = new Pane();

	boardPane.getChildren().addAll(SceneUtil.getBoardBackground(), SceneUtil.getBoard());

	Scene scene = new Scene(boardPane);
	SceneUtil.fadeIn(SceneUtil.getBoardBackground(), SceneUtil.getBoard());
	return scene;
    }

}
