package nl.groep4.kvc.client.view.pane;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.elements.SettingsButton;
import nl.groep4.kvc.client.view.scene.SceneMap;

/**
 * Pane that displays all settings which can be changed.
 * 
 * @author Tim
 * @version 1.2
 */
public class OptionPane implements PaneHolder {

    private SceneMap map;

    private Text title;
    private MenuButton save;
    private MenuButton pause;
    private MenuButton rules;
    private MenuButton credits;
    private MenuButton exit;
    private MenuButton back;

    /**
     * Gives current SceneMap.
     * 
     * @param map
     *            Current map settings.
     */
    public OptionPane(SceneMap map) {
	this.map = map;
    }

    @Override
    public Pane getPane() {
	StackPane layers = new StackPane();
	HBox hboxsetting = new HBox();
	HBox hboxsave = new HBox();
	HBox hboxpause = new HBox();
	HBox hboxrules = new HBox();
	HBox hboxcredits = new HBox();
	HBox hboxexit = new HBox();
	HBox hboxback = new HBox();
	VBox buttons = new VBox(8);
	buttons.setAlignment(Pos.CENTER);
	hboxsetting.setAlignment(Pos.CENTER);
	hboxsave.setAlignment(Pos.CENTER);
	hboxpause.setAlignment(Pos.CENTER);
	hboxrules.setAlignment(Pos.CENTER);
	hboxcredits.setAlignment(Pos.CENTER);
	hboxexit.setAlignment(Pos.CENTER);
	hboxback.setAlignment(Pos.CENTER);
	title = new Text(TranslationManager.translate("game.menu.title"));
	save = new MenuButton(TranslationManager.translate("game.menu.save"));
	pause = new MenuButton(TranslationManager.translate("game.menu.pause"));
	rules = new MenuButton(TranslationManager.translate("game.menu.rules"));
	credits = new MenuButton(TranslationManager.translate("game.menu.credits"));
	exit = new MenuButton(TranslationManager.translate("game.menu.exit"));
	back = new MenuButton(TranslationManager.translate("game.menu.back"));
	title.setFont(ViewMaster.TITLE_FONT);
	title.setFill(Color.WHITE);
	save.setFont(ViewMaster.FONT);
	pause.setFont(ViewMaster.FONT);
	rules.setFont(ViewMaster.FONT);
	credits.setFont(ViewMaster.FONT);
	exit.setFont(ViewMaster.FONT);
	back.setFont(ViewMaster.FONT);

	save.setOnMouseClicked(klick -> onSaveClick());
	pause.setOnMouseClicked(klick -> onPauseClick());
	rules.setOnMouseClicked(klick -> onRulesClick());
	credits.setOnMouseClicked(klick -> onCreditsClick());
	exit.setOnMouseClicked(klick -> onExitClick());
	back.setOnMouseClicked(klick -> onBackClick());

	hboxsetting.getChildren().add(SettingsButton.getButton(map, 0, 0));
	hboxsave.getChildren().add(save);
	hboxpause.getChildren().add(pause);
	hboxrules.getChildren().add(rules);
	hboxcredits.getChildren().add(credits);
	hboxexit.getChildren().add(exit);
	hboxback.getChildren().add(back);
	buttons.getChildren().addAll(title, hboxsetting, hboxsave, hboxpause, hboxrules, hboxcredits, hboxexit,
		hboxback);
	layers.getChildren().addAll(SceneUtil.getSettingsPane(), buttons);
	return layers;
    }

    private void onExitClick() {
	System.exit(0);
    }

    private void onCreditsClick() {
	map.openCreditsPane();
    }

    private void onBackClick() {
	map.closeOverlay();
    }

    private void onRulesClick() {
	map.openRulesPane();
    }

    private void onPauseClick() {
	map.getController().setPause();
    }

    private void onSaveClick() {
	map.openSavePane();
    }

    @Override
    public void updateConfig() {
	title.setText(TranslationManager.translate("game.menu.title"));
	save.updateText(TranslationManager.translate("game.menu.save"));
	pause.updateText(TranslationManager.translate("game.menu.pause"));
	rules.updateText(TranslationManager.translate("game.menu.rules"));
	credits.updateText(TranslationManager.translate("game.menu.credits"));
	exit.updateText(TranslationManager.translate("game.menu.exit"));
	back.updateText(TranslationManager.translate("game.menu.back"));
    }

}
