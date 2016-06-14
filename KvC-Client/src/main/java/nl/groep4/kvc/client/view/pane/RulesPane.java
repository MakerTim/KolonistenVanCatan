package nl.groep4.kvc.client.view.pane;

import java.io.BufferedReader;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import jdk.nashorn.api.scripting.URLReader;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.scene.SceneMap;

public class RulesPane implements PaneHolder {
    Text rules;
    SceneMap view;
    MenuButton back;

    public RulesPane(SceneMap view) {
	this.view = view;

    }
    // view.closeOverlay();

    public Pane getPane() {
	// TODO Auto-generated method stub

	StackPane rulepane = new StackPane();

	VBox vbox = new VBox();
	vbox.setAlignment(Pos.CENTER);
	vbox.setPadding(new Insets(30, 40, 0, 40));

	back = new MenuButton(425, 500, TranslationManager.translate("rules.button.back"));
	back.setFont(ViewMaster.FONT);

	rules = new Text(TranslationManager.translate("rule.text.rules"));
	rules.setFont(ViewMaster.FONT);
	rules.setFill(Color.WHITE);
	rules.setStroke(Color.BLACK);

	TextArea area = new TextArea();
	area.setStyle("-fx-text-fill: white;");
	area.setPrefRowCount(21);

	Node background = SceneUtil.getGamePane();

	vbox.getChildren().addAll(rules, area, back);
	rulepane.getChildren().addAll(background, vbox);

	BufferedReader fileReader = new BufferedReader(
		new URLReader(TranslationManager.class.getResource("/assets/Spelregels.txt")));
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
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	});

	return rulepane;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

    }

}
