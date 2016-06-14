package nl.groep4.kvc.client.view.pane;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.elements.SettingsButton;

public class OptionPane implements PaneHolder {

    // TODO: OptionPane needs work
    private MenuButton save;
    private MenuButton pause;
    private MenuButton rules;
    private MenuButton exit;
    private MenuButton back;

    @Override
    public Pane getPane() {
	StackPane layers = new StackPane();
	VBox buttons = new VBox(8);
	save = new MenuButton(TranslationManager.translate("game.menu.save"));
	pause = new MenuButton(TranslationManager.translate("game.menu.pause"));
	rules = new MenuButton(TranslationManager.translate("game.menu.rules"));
	exit = new MenuButton(TranslationManager.translate("game.menu.exit"));
	back = new MenuButton(TranslationManager.translate("game.menu.back"));

	save.setOnMouseClicked(klick -> onSaveClick());
	pause.setOnMouseClicked(klick -> onPauseClick());
	rules.setOnMouseClicked(klick -> onRulesClick());
	exit.setOnMouseClicked(klick -> onExitClick());
	back.setOnMouseClicked(klick -> onBackClick());

	buttons.getChildren().addAll(SettingsButton.getButton(null, 0, 0), save, pause, rules, exit, back);
	layers.getChildren().addAll(SceneUtil.getSettingsPane(), buttons);
	return layers;
    }

    private void onExitClick() {
    }

    private void onBackClick() {
    }

    private void onRulesClick() {
    }

    private void onPauseClick() {
    }

    private void onSaveClick() {
    }

    @Override
    public void updateTranslation() {
	save.updateText(TranslationManager.translate("game.menu.save"));
	pause.updateText(TranslationManager.translate("game.menu.pause"));
	rules.updateText(TranslationManager.translate("game.menu.rules"));
	exit.updateText(TranslationManager.translate("game.menu.exit"));
	back.updateText(TranslationManager.translate("game.menu.back"));
    }

}
