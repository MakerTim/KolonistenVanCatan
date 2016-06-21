package nl.groep4.kvc.client.view.pane;

import java.io.BufferedReader;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import jdk.nashorn.api.scripting.URLReader;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.KvCText;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.scene.SceneMap;

/**
 * Pane for the rules of the game
 * 
 * @author Lisa
 *
 */
public class RulesPane implements PaneHolder {
    private KvCText rules = new KvCText(TranslationManager.translate("rules.text.rules"));
    private SceneMap view;
    private MenuButton back;

    /**
     * Sets up the Rulespane
     * 
     * @param view
     */
    public RulesPane(SceneMap view) {
	this.view = view;

    }

    @Override
    public Pane getPane() {
	StackPane rulepane = new StackPane();

	TextArea area = new TextArea();

	VBox vbox = new VBox();
	HBox hbox = new HBox();

	vbox.setAlignment(Pos.CENTER);
	vbox.setPadding(new Insets(40, 80, 40, 80));

	back = new MenuButton(425, 500, TranslationManager.translate("rules.button.back"));
	back.setFont(ViewMaster.FONT);

	area.setStyle("-fx-text-fill: white;");
	area.setPrefRowCount(18);
	area.setMaxWidth(600);
	area.setEditable(false);
	area.setOnKeyPressed(key -> key.consume());

	Node background = SceneUtil.getGamePane();

	hbox.setAlignment(Pos.CENTER);

	hbox.getChildren().add(back);
	vbox.getChildren().addAll(rules, area, hbox);
	rulepane.getChildren().addAll(background, vbox);

	BufferedReader fileReader = new BufferedReader(new URLReader(TranslationManager.class
		.getResource("/assets/Spelregels-" + TranslationManager.getCurrentLanguage() + ".txt")));
	try {
	    Scanner scanner = new Scanner(fileReader);
	    while (scanner.hasNextLine()) {
		String line = scanner.nextLine() + "\n";
		area.appendText(line);
	    }
	    scanner.close();
	} catch (Exception e) {
	    e.getStackTrace();
	}

	rulepane.getStylesheets().add("/assets/stylesheet.css");

	back.registerClick(() -> {
	    try {
		view.closeOverlay();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	});

	return rulepane;
    }

    @Override
    public void updateTranslation() {
	rules.setText(TranslationManager.translate("rules.text.rules"));
	back.updateText(TranslationManager.translate("rules.button.back"));
    }

}
