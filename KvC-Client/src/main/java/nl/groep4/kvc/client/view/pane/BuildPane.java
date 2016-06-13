package nl.groep4.kvc.client.view.pane;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class BuildPane extends Application implements PaneHolder {

    @Override
    public void start(Stage primaryStage) throws Exception {
	StackPane Build = new StackPane();
	Build.setMinSize(50, 50);

	Label title = new Label();

	Button Button1 = new Button("Build");
	Group root = new Group();

	Build.getChildren().add(Button1);
	root.getChildren().add(Build);

	primaryStage.show();

    }

    @Override
    public Pane getPane() {
	return null;
    }

    @Override
    public void updateTranslation() {

    }
}
