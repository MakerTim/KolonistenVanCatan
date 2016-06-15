package nl.groep4.kvc.client.view.pane;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.groep4.kvc.client.util.TranslationManager;

public class CreditsPane extends Application implements PaneHolder {
    Text credits;
    Text cred1;
    Text cred2;
    Text cred3;
    Text cred4;
    Text cred5;
    Text cred6;

    @Override
    public void start(Stage primaryStage) throws Exception {
	// TODO Auto-generated method stub

	credits = new Text(TranslationManager.translate("credits.text.credits"));
	cred1 = new Text();
    }

    @Override
    public Pane getPane() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

    }

}
