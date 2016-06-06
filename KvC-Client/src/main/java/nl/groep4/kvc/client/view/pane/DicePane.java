package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.controller.ThrowController;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.common.interfaces.Throw;
import nl.groep4.kvc.common.interfaces.UpdateDice;

public class DicePane implements PaneHolder, UpdateDice {
    StackPane dicePane;
    Text clickToThrow;
    Text firstDice;
    Text secondDice;
    MenuButton throwDice;
    HBox dices;
    VBox things;

    @Override
    public Pane getPane() {
	dices = new HBox();
	things = new VBox(20);

	dicePane = new StackPane();

	clickToThrow = new Text(TranslationManager.translate("map.throwdice.clickToThrow"));
	firstDice = new Text("*");
	secondDice = new Text("*");
	throwDice = new MenuButton(0, 0, TranslationManager.translate("map.throwdice.throw"));
	throwDice.registerClick(() -> throwDice());
	throwDice.setFont(ViewMaster.FONT);

	firstDice.setFont(ViewMaster.FONT);
	secondDice.setFont(ViewMaster.FONT);
	clickToThrow.setFont(ViewMaster.FONT);

	dices.getChildren().addAll(firstDice, secondDice);
	things.getChildren().addAll(clickToThrow, dices, throwDice);

	things.setAlignment(Pos.CENTER);
	dices.setAlignment(Pos.CENTER);
	dicePane.getChildren().addAll(SceneUtil.getLobbyForeground(), things);
	SceneUtil.fadeIn(SceneUtil.getLobbyForeground(), things);
	return dicePane;
    }

    @Override
    public void updateTranslation() {
	clickToThrow.setText(TranslationManager.translate("map.throwdice.clickToThrow"));
	throwDice.updateText(TranslationManager.translate("map.throwdice.throw"));
    }

    @Override
    public void setModel(Throw model) throws RemoteException {
	firstDice.setText(Integer.toString(model.getDiceLeft()));
	secondDice.setText(Integer.toString(model.getDiceRight()));
    }

    @Override
    public void throwDice() {
	ThrowController.throwDice();
    }

    @Override
    public void updateDices(int dice1, int dice2) {
	firstDice.setText(Integer.toString(dice1));
	secondDice.setText(Integer.toString(dice2));
    }
}
