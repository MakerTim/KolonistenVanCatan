package nl.groep4.kvc.client.view.pane;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ScorePane implements PaneHolder {

    StackPane scorePane;
    VBox players;
    VBox content;
    ImageView banner;
    Text title;

    @Override
    public Pane getPane() {
	scorePane = new StackPane();
	players = new VBox();
	content = new VBox();
	title = new Text();

	content.getChildren().addAll(title, players);

	scorePane.getChildren().addAll(getBanner(), content);
	return scorePane;
    }

    public void clickOpen() {
	scorePane.getChildren().addAll(getBanner(), content);
    }

    public void clickClose() {
	scorePane.getChildren().removeAll(getBanner(), content);
    }

    public Node getBanner() {
	banner = new ImageView("img/game/banner.png");
	banner.setFitHeight(482);
	banner.setFitWidth(200);
	return banner;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

    }
}
