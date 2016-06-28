package nl.groep4.kvc.client.view.pane;

import java.io.BufferedReader;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import jdk.nashorn.api.scripting.URLReader;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.KvCText;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.scene.SceneMap;

/**
 * Pane for the rules of the game.
 * 
 * @author Lisa
 * @version 1.2
 */
public class RulesPane implements PaneHolder {
    private KvCText rules = new KvCText(TranslationManager.translate("rules.text.rules"));
    private SceneMap view;
    private MenuButton back = new MenuButton(425, 500, TranslationManager.translate("rules.button.back"));

    /**
     * Sets up the Rulespane.
     * 
     * @param view
     *            View of the map.
     */
    public RulesPane(SceneMap view) {
	this.view = view;

    }

    @Override
    public Pane getPane() {
	StackPane rulepane = new StackPane();
	ScrollPane scrollpane = new ScrollPane();

	VBox vbox = new VBox();
	VBox lines = new VBox();
	HBox hbox = new HBox();

	back.setFont(ViewMaster.FONT);

	scrollpane.setHbarPolicy(ScrollBarPolicy.NEVER);
	scrollpane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	scrollpane.setMinWidth(500);
	scrollpane.setMaxWidth(600);
	scrollpane.setMinHeight(300);
	scrollpane.setMaxHeight(360);
	scrollpane.setContent(lines);

	vbox.setAlignment(Pos.CENTER);
	vbox.setPadding(new Insets(40, 80, 40, 80));
	lines.setAlignment(Pos.CENTER_LEFT);
	hbox.setAlignment(Pos.CENTER);

	BufferedReader fileReader = new BufferedReader(new URLReader(TranslationManager.class
		.getResource("/assets/Spelregels-" + TranslationManager.getCurrentLanguage() + ".txt")));
	try {
	    Scanner scanner = new Scanner(fileReader);
	    while (scanner.hasNextLine()) {
		String line = scanner.nextLine() + "\n";
		Text text = new Text(line);
		text.setFill(Color.WHITE);
		lines.getChildren().add(text);

	    }
	    scanner.close();
	} catch (Exception e) {
	    e.getStackTrace();
	}

	Node background = SceneUtil.getGamePane();

	hbox.getChildren().add(back);
	vbox.getChildren().addAll(rules, scrollpane, hbox);
	rulepane.getChildren().addAll(background, vbox);
	rulepane.getStylesheets().add("/assets/stylesheet.css");

	back.registerClick(() -> onBackClick());

	return rulepane;
    }

    private void onBackClick() {
	view.closeOverlay();
    }

    @Override
    public void updateTranslation() {
	rules.setText(TranslationManager.translate("rules.text.rules"));
	back.updateText(TranslationManager.translate("rules.button.back"));
    }

}
