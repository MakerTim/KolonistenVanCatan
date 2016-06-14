package nl.groep4.kvc.client.view.pane;

import java.io.BufferedReader;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.nashorn.api.scripting.URLReader;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;

public class RulesPane extends Application implements PaneHolder {
    Text rules;

    @Override
    public void start(Stage primaryStage) throws Exception {
	// TODO Auto-generated method stub

	StackPane rulepane = new StackPane();
	VBox vbox = new VBox();
	vbox.setPadding(new Insets(150, 40, 0, 40));

	rules = new Text(TranslationManager.translate("rule.text.rules"));
	rules.setFont(ViewMaster.FONT);
	rules.setFill(Color.WHITE);
	rules.setStroke(Color.BLACK);

	TextArea area = new TextArea();
	area.setStyle("-fx-text-fill: white;");
	area.setPrefRowCount(22);

	Node background = SceneUtil.getGamePane();

	vbox.getChildren().addAll(rules, area);
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

	Scene scene = new Scene(rulepane, 700, 800);
	scene.getStylesheets().add("/assets/stylesheet.css");
	primaryStage.setScene(scene);
	primaryStage.show();

	// textarea
	// transparant
	// scrolbar, een borders
    }

    @Override
    public Pane getPane() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

    }

}
