package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.KvCText;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.TurnState;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateRound;

public class PausePane implements PaneHolder, UpdateRound {

    private MenuButton continueButton;
    private Text pause;

    private SceneMap sceneMap;
    private boolean isMyTurn;

    public PausePane(SceneMap sceneMap, boolean ismyturn) {
	this.sceneMap = sceneMap;
	this.isMyTurn = ismyturn;
    }

    @Override
    public Pane getPane() {

	StackPane pausepane = new StackPane();
	VBox pausebox = new VBox();

	if (isMyTurn) {
	    continueButton = new MenuButton(425, 500, TranslationManager.translate("pause.button.continue"));
	    continueButton.setFont(ViewMaster.FONT);
	    continueButton.registerClick(() -> setPause());
	    pausebox.getChildren().add(continueButton);
	}
	pause = new KvCText(TranslationManager.translate("pause.label.pause"));

	pause.setFont(ViewMaster.TITLE_FONT);

	pausebox.setAlignment(Pos.CENTER);

	Node background = SceneUtil.getGamePane();

	pausebox.getChildren().add(0, pause);
	pausepane.getChildren().addAll(background, pausebox);

	return pausepane;
    }

    @Override
    public void updateTranslation() {
	if (continueButton != null) {
	    continueButton.updateText(TranslationManager.translate("pause.label.pause"));
	}
	if (pause != null) {
	    pause.setText(TranslationManager.translate("pause.label.pause"));
	}
    }

    public void setPause() {
	if (isMyTurn) {
	    isMyTurn = false;
	    sceneMap.getController().setPause();
	    continueButton.setDisabled();
	}
    }

    @Override
    public void updateRound(int round) throws RemoteException {
    }

    @Override
    public void updateTurn(Player who, TurnState what) throws RemoteException {
    }
}