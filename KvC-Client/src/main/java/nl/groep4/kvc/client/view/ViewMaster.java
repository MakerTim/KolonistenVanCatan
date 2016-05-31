package nl.groep4.kvc.client.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.groep4.kvc.client.util.SoundUtil;

/**
 * The application itself
 * 
 * @version 1.0
 * @author Tim
 * @see SceneLogin main screen
 **/
public class ViewMaster extends Application {

    public static final Font FONT = new Font("Impact", 22);
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
	stage = primaryStage;
	System.out.println("Starting lobby");

	SoundUtil.playThemesong();
	System.out.println("Playing themesong");
	primaryStage.setScene(new SceneLogin().getScene());
	primaryStage.setTitle("Kolonisten van Catan: Online");
	primaryStage.setResizable(false);
	primaryStage.show();
	System.out.println("Showing lobby");
    }

    public static void setScene(Scene scene) {
	stage.setScene(scene);
    }
}
