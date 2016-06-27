package nl.groep4.kvc.client.view.pane;

import javafx.scene.layout.Pane;

/**
 * Interface for making code have some guideline.
 * 
 * @author Tim
 * @version 1.1
 * 
 **/
public interface PaneHolder {

    /**
     * Gets pane.
     * 
     */
    public Pane getPane();

    /**
     * Update the translation of the pane.
     * 
     */
    public void updateTranslation();
}
