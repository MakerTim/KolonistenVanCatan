package nl.groep4.kvc.client.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.view.scene.SceneLogin;

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
	stage.getIcons().add(new Image("img/etc/cursor.png"));
	SoundUtil.playThemesong();
	System.out.println("Playing themesong");
	setScene(new SceneLogin().getScene());
	primaryStage.setTitle("Kolonisten van Catan: Online");
	primaryStage.setResizable(false);
	primaryStage.setOnCloseRequest(onClose -> {
	    System.exit(0);
	});
	primaryStage.show();
	System.out.println("Showing lobby");
    }

    public static void setScene(Scene scene) {
	Platform.runLater(() -> {
	    scene.setCursor(new ImageCursor(new Image("img/etc/cursor.png")));
	    stage.setScene(scene);
	});
    }
}
