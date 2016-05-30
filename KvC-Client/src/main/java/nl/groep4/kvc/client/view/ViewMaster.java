package nl.groep4.kvc.client.view;

import javafx.application.Application;
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

    @Override
    public void start(Stage primaryStage) throws Exception {
	System.out.println("Starting lobby");

	primaryStage.setScene(new SceneLogin(primaryStage).getScene());
	primaryStage.setTitle("Kolonisten van Catan: Online");
	primaryStage.setResizable(false);
	primaryStage.show();
	System.out.println("Showing lobby");
    }

}
