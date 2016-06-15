package nl.groep4.kvc.client.view.pane;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.scene.SceneMap;

public class CreditsPane extends Application implements PaneHolder {
    Text credits;
    Text cred1;
    Text cred2;
    Text cred3;
    Text cred4;
    Text cred5;
    Text cred6;

    public CreditsPane(SceneMap sceneMap) {
	// TODO Auto-generated constructor stub
    }

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
