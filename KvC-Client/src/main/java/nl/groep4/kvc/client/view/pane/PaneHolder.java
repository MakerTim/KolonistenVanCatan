package nl.groep4.kvc.client.view.pane;

import javafx.scene.layout.Pane;

/**
 * Interface for making code have some guideline.
 * 
 * @author Tim
 * @version 1.2
 * 
 **/
public interface PaneHolder {

    /**
     * Gets the Pane.
     * 
     * @return Pane
     */
    public Pane getPane();

    /**
     * Update the translation of the pane.
     */
    public void updateConfig();
}
