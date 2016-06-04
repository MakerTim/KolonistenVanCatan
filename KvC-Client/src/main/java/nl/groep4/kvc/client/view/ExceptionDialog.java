package nl.groep4.kvc.client.view;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import nl.groep4.kvc.client.util.TranslationManager;

/**
 * 
 * ExceptionDialog.
 * 
 * Displays an exception stack trace in a panel south of the main dialog area.
 * 
 * @author Original Oliver Watkins
 * @author Changed by Tim
 */
public class ExceptionDialog {

    public static void warning(String key) {
	warning(TranslationManager.translate(key + ".title"), TranslationManager.translate(key + ".explanation"),
		TranslationManager.translate(key + ".details"));
    }

    public static void warning(String title, String header, String message) {
	Platform.runLater(() -> {
	    Alert alert = new Alert(AlertType.WARNING);
	    alert.setTitle(title);
	    alert.setHeaderText(header);
	    alert.setContentText(message);
	    alert.showAndWait();
	});
    }

    public static void error(Throwable thr) {
	Platform.runLater(() -> {
	    Alert alert = new Alert(AlertType.ERROR);
	    alert.setTitle("An error occurred");
	    alert.setHeaderText(thr.getClass().getSimpleName());
	    alert.setContentText(thr.getMessage());

	    /* Create expandable Exception. */
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    thr.printStackTrace(pw);
	    String exceptionText = sw.toString();

	    Label label = new Label("The exception stacktrace was:\nPlz send this to Groep4");

	    TextArea textArea = new TextArea(exceptionText);
	    textArea.setEditable(false);
	    textArea.setWrapText(true);

	    textArea.setMaxWidth(Double.MAX_VALUE);
	    textArea.setMaxHeight(Double.MAX_VALUE);
	    GridPane.setVgrow(textArea, Priority.ALWAYS);
	    GridPane.setHgrow(textArea, Priority.ALWAYS);

	    GridPane expContent = new GridPane();
	    expContent.setMaxWidth(Double.MAX_VALUE);
	    expContent.add(label, 0, 0);
	    expContent.add(textArea, 0, 1);

	    /* Set expandable Exception into the dialog pane. */
	    alert.getDialogPane().setExpandableContent(expContent);

	    alert.showAndWait();
	});
    }

}
