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
    ImageView closedBanner;
    Text title;

    @Override
    public Pane getPane() {
	scorePane = new StackPane();
	players = new VBox();
	content = new VBox();
	title = new Text();

	content.getChildren().addAll(title, players);

	scorePane.getChildren().addAll(getClosedBanner());

	scorePane.setOnMouseEntered(e -> clickOpen());
	scorePane.setOnMouseExited(e -> clickClose());
	return scorePane;
    }

    public void clickOpen() {
	scorePane.getChildren().addAll(getBanner(), content);
	scorePane.getChildren().remove(closedBanner);
    }

    public void clickClose() {
	scorePane.getChildren().add(getClosedBanner());
	scorePane.getChildren().removeAll(banner, content);
    }

    public Node getBanner() {
	banner = new ImageView("img/game/banner.png");
	banner.setFitHeight(390);
	banner.setFitWidth(160);
	return banner;
    }

    public Node getClosedBanner() {
	closedBanner = new ImageView("img/game/banner_closed.png");
	closedBanner.setFitHeight(200);
	closedBanner.setFitWidth(160);
	return closedBanner;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

    }
}
