package nl.groep4.kvc.client.view.scene;

import javafx.scene.Scene;
import nl.groep4.kvc.client.controller.Controller;

/**
 * Interface for making code have some guideline
 * 
 * @version 1.0
 * @author Tim
 **/
public interface SceneHolder {

    /**
     * gets scene
     */
    public Scene getScene();

    /**
     * Update the configuration of sceneHolder
     */
    public void updateConfig();

    /**
     * registers controller
     */
    public void registerController(Controller controller);
}
