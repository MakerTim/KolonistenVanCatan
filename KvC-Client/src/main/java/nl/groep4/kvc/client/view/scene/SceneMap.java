package nl.groep4.kvc.client.view.scene;

import java.rmi.RemoteException;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;

public class SceneMap implements SceneHolder {

    @Override
    public Scene getScene() throws RemoteException {
	/* Build layer for the design */
	Pane boardPane = new Pane();
	MenuButton buildButton = new MenuButton(1150, 600, "Build");
	buildButton.setFont(ViewMaster.FONT);
	MenuButton tradeButton = new MenuButton(1150, 650, "Trade");
	tradeButton.setFont(ViewMaster.FONT);
	MenuButton buyButton = new MenuButton(1150, 700, "Buy");
	buyButton.setFont(ViewMaster.FONT);

	boardPane.getChildren().addAll(SceneUtil.getBoardBackground(), SceneUtil.getBoard(), buildButton, tradeButton,
		buyButton);

	Scene scene = new Scene(boardPane);
	SceneUtil.fadeIn(SceneUtil.getBoardBackground(), SceneUtil.getBoard(), buildButton, tradeButton, buyButton);
	return scene;
    }

}
