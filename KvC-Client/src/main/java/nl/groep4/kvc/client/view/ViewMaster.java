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
import nl.groep4.kvc.client.ClientStarter;
import nl.groep4.kvc.client.controller.LoginController;
import nl.groep4.kvc.client.debug.TestMapViewSetup;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.view.elements.SettingsButton;
import nl.groep4.kvc.client.view.scene.SceneHolder;
import nl.groep4.kvc.client.view.scene.SceneLogin;
import nl.groep4.kvc.common.util.CollectionUtil;

/**
 * The application itself
 * 
 * @version 1.0
 * @author Tim
 * @see SceneLogin main screen
 **/
public class ViewMaster extends Application {

    public static final int GAME_WIDHT = 1366;
    public static final int GAME_HEIGHT = 768;

    /**
     * Gives the font type "Impact" with a size of 22
     */
    public static final Font FONT = new Font("Impact", 22);
    public static final Font TITLE_FONT = new Font("Impact", 65);
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
	if (ClientStarter.args.length > 0) {
	    if (CollectionUtil.contains(ClientStarter.args, "--mapstarter")) {
		TestMapViewSetup.setup();
	    }
	} else {
	    setScene(view);
	}
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
	    setSceneSynced(scene);
	});
    }

    /**
     * sets sceneSynced
     * 
     * @param scene
     *            current scene
     */
    public static void setSceneSynced(SceneHolder scene) {
	Scene theScene = scene.getScene();
	lastScene = scene;
	theScene.setCursor(new ImageCursor(new Image("img/etc/cursor.png")));
	stage.setScene(theScene);
    }

    /**
     * gets lastScene
     * 
     * @return previous scene
     */
    public static SceneHolder getLastScene() {
	return lastScene;
    }

    /**
     * Update given settings from user
     */
    public static void updateConfig() {
	SettingsButton.updateConfig();
	lastScene.updateConfig();
    }
}
