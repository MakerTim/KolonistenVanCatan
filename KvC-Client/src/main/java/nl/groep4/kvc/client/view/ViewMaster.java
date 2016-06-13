package nl.groep4.kvc.client.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import nl.groep4.kvc.client.controller.LoginController;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.view.elements.SettingsButton;
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

    private static final int GAME_WIDHT = 1366;
    private static final int GAME_HEIGHT = 768;

    /**
     * Gives the font type "Impact" with a size of 22
     */
    public static final Font FONT = new Font("Impact", 22);
    public static final Font TITLE_FONT = new Font("Impact", 40);
    private static Stage stage;
    private static SceneHolder lastScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
	stage = primaryStage;
	System.out.println("Playing ThemeSong");
	SoundUtil.playThemesong();

	System.out.println("Starting LoginScreen");
	SceneLogin view = new SceneLogin();
	view.registerController(new LoginController(view));
	setScene(view);
	primaryStage.setTitle("Kolonisten van Catan: Online");
	primaryStage.setResizable(false);
	primaryStage.setOnCloseRequest(onClose -> {
	    System.exit(0);
	});
	System.out.println("Showing LoginScreen");
	stage.getIcons().add(new Image("img/etc/cursor.png"));
	Rectangle2D screen = Screen.getPrimary().getBounds();

	primaryStage.setX(-2 + (screen.getMaxX() / 2) - (GAME_WIDHT / 2));
	primaryStage.setY((screen.getMaxY() / 2) - (GAME_HEIGHT / 2));
	primaryStage.show();
    }

    /**
     * Sets scene and sets the cursor image
     * 
     * @param scene
     *            scene instance
     */
    public static void setScene(SceneHolder scene) {
	Platform.runLater(() -> {
	    Scene theScene = scene.getScene();
	    lastScene = scene;
	    theScene.setCursor(new ImageCursor(new Image("img/etc/cursor.png")));
	    stage.setScene(theScene);
	});
    }

    /**
     * Update given settings from user
     */
    public static void updateConfig() {
	SettingsButton.updateConfig();
	lastScene.updateConfig();
    }
}
