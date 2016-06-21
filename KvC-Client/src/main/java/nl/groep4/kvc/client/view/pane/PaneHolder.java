package nl.groep4.kvc.client.view.pane;

import javafx.scene.layout.Pane;

/**
 * Interface for making code have some guideline
 * 
 * @version 1.0
 * @author Tim
 **/
public interface PaneHolder {

    /**
     * gets pane
     */
    public Pane getPane();

    /**
     * Update the translation of the pane
     */
    public void updateTranslation();
}
