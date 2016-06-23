package nl.groep4.kvc.client.view.pane;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
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
import nl.groep4.kvc.common.interfaces.NotCloseable;
import nl.groep4.kvc.common.interfaces.UpdateDice;
import nl.groep4.kvc.common.util.Scheduler;

/**
 * Pane that gets automatically displayed and rolls the dice when clicked on a
 * button
 * 
 * @author Tim
 * @author Luc
 * @version 1.0
 */
public class DicePane implements PaneHolder, UpdateDice, NotCloseable {

    private Text throwLabel;
    private Text leftDice;
    private Text rightDice;
    private MenuButton throwButton;
    private HBox dices;

    private SceneMap view;
    private boolean isMyTurn;
    private boolean hasNumber;

    /**
     * Determines whose turn it is and displays button
     * 
     * @param view
     *            view to display
     * @param isMyTurn
     *            the turn for the given player
     */
    public DicePane(SceneMap view, boolean isMyTurn) {
	this.view = view;
	this.isMyTurn = isMyTurn;
    }

    @Override
    public Pane getPane() {
	StackPane layers = new StackPane();
	VBox lines = new VBox(20);
	HBox hboxbutton = new HBox();
	dices = new HBox(100);

	throwLabel = new KvCText();
	if (isMyTurn) {
	    throwLabel.setText(TranslationManager.translate("map.throwdice.clickToThrow"));
	} else {
	    throwLabel.setText(TranslationManager.translate("map.throwdice.clicktothrow.other"));
	}
	leftDice = new KvCText();
	rightDice = new KvCText();

	leftDice.setFont(ViewMaster.TITLE_FONT);
	rightDice.setFont(ViewMaster.TITLE_FONT);

	dices.getChildren().addAll(leftDice, rightDice);
	lines.getChildren().addAll(throwLabel, dices);
	if (isMyTurn) {
	    throwButton = new MenuButton(0, 0, TranslationManager.translate("map.throwdice.throw"));
	    throwButton.registerClick(() -> throwDice());
	    throwButton.setFont(ViewMaster.FONT);
	    hboxbutton.getChildren().add(throwButton);
	    lines.getChildren().add(hboxbutton);
	}

	hboxbutton.setAlignment(Pos.CENTER);
	lines.setAlignment(Pos.CENTER);
	dices.setAlignment(Pos.CENTER);
	layers.getChildren().addAll(SceneUtil.getGamePane(), lines);
	SceneUtil.fadeIn(SceneUtil.getGamePane(), lines);
	Scheduler.runAsync(() -> {
	    do {
		Platform.runLater(() -> {
		    leftDice.setText(Integer.toString(1 + (int) (Math.random() * 6)));
		    rightDice.setText(Integer.toString(1 + (int) (Math.random() * 6)));
		});
		try {
		    Thread.sleep(100L);
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    } while (!hasNumber);
	});
	return layers;
    }

    @Override
    public void updateTranslation() {
	if (isMyTurn) {
	    throwLabel.setText(TranslationManager.translate("map.throwdice.clickToThrow"));
	} else {
	    throwLabel.setText(TranslationManager.translate("map.throwdice.clicktothrow.other"));
	}
	if (throwButton != null) {
	    throwButton.updateText(TranslationManager.translate("map.throwdice.throw"));
	}
    }

    /**
     * calls the throwDice method and disables the throw button
     */
    public void throwDice() {
	if (isMyTurn) {
	    isMyTurn = false;
	    view.getController().throwDice();
	    throwButton.setDisabled();
	}
    }

    @Override
    public void updateDices(int dice1, int dice2) {
	hasNumber = true;
	Scheduler.runSyncLater(() -> {
	    leftDice.setText(Integer.toString(dice1));
	    rightDice.setText(Integer.toString(dice2));
	    Scheduler.runSyncLater(() -> {
		dices.getChildren().clear();
		Text text = new KvCText(Integer.toString(dice1 + dice2));
		text.setFont(ViewMaster.TITLE_FONT);
		dices.getChildren().add(text);
		Scheduler.runSyncLater(() -> {
		    view.closeOverlay();
		}, 5000L);
	    }, 2500L);
	}, 200L);
    }
}
