package nl.groep4.kvc.client.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import nl.groep4.kvc.client.controller.ClientRefrence;

/**
 * The confirm messages are being created here
 * 
 * version: 1.0
 * 
 * @author Talbi
 *
 */

public class ConfirmDialog {

    /**
     * This is a confirmationbox that needs confirmation from the player.
     * 
     * @param key
     *            The choice that the player makes.
     * @return true
     */
    public static boolean confirm(String key) {
	if (ClientRefrence.isConfirmModus()) {
	    Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle(TranslationManager.translate("dialog.title"));
	    alert.setHeaderText(TranslationManager.translate("dialog." + key + ".desc"));
	    alert.setContentText(TranslationManager.translate("dialog.confirm"));

	    Optional<ButtonType> result = alert.showAndWait();
	    return result.get() == ButtonType.OK;
	}
	return true;
    }

}
