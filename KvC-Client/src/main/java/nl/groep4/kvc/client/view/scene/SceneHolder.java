package nl.groep4.kvc.client.view.scene;

import javafx.scene.Scene;
import nl.groep4.kvc.client.controller.Controller;

/**
 * Interface for making code have some guideline.
 * 
 * @author Tim
 * @version 1.1
 **/
public interface SceneHolder {

    /**
     * Gets scene.
     * 
     */
    public Scene getScene();

    /**
     * Update the configuration of sceneHolder.
     * 
     */
    public void updateConfig();

    /**
     * Registers controller.
     * 
     */
    public void registerController(Controller controller);
}
