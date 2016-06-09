package nl.groep4.kvc.client.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.view.scene.SceneHolder;
import nl.groep4.kvc.client.view.scene.SceneLogin;

/**
 * The application itself
 * 
 * @version 1.0
 * @author Tim
 * @see SceneLogin main screen
 **/
public class ViewMaster extends Application {

    /**
     * Gives the font type "Impact" with a size of 22
     */
    public static final Font FONT = new Font("Impact", 22);
    private static Stage stage;
    private static SceneHolder lastScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
	stage = primaryStage;
	System.out.println("Playing ThemeSong");
	SoundUtil.playThemesong();

	System.out.println("Starting LoginScreen");
	setScene(new SceneLogin());
	primaryStage.setTitle("Kolonisten van Catan: Online");
	primaryStage.setResizable(false);
	primaryStage.setOnCloseRequest(onClose -> {
	    System.exit(0);
	});
	System.out.println("Showing LoginScreen");
	stage.getIcons().add(new Image("img/etc/cursor.png"));
	primaryStage.show();
    }

    /**
     * Sets scene and sets the cursor image
     * 
     * @param scene
     *            scene instance
     */
    public static void setScene(SceneHolder scene) {
	Scene theScene = scene.getScene();
	Platform.runLater(() -> {
	    lastScene = scene;
	    theScene.setCursor(new ImageCursor(new Image("img/etc/cursor.png")));
	    stage.setScene(theScene);
	});
    }

    /**
     * Update given settings from user
     */
    public static void updateConfig() {
	lastScene.updateConfig();
    }
}
