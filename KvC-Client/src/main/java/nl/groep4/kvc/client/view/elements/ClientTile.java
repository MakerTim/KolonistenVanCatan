package nl.groep4.kvc.client.view.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.map.TileLand;
import nl.groep4.kvc.common.map.TileResource;
import nl.groep4.kvc.common.map.TileSea;

public class ClientTile extends StackPane {

    private Tile tile;
    private Coordinate coord;

    private ImageView image;
    private ImageView fiche;
    private Text number;

    public ClientTile(Coordinate coord) {
	this.coord = coord;
	image = new ImageView();
	fiche = new ImageView("img/tiles/fiche.png");
	number = new Text();

	image.setFitWidth(1 * SceneMap.scale);
	image.setFitHeight(0.86 * SceneMap.scale);
	fiche.setFitWidth(0.35 * SceneMap.scale);
	fiche.setFitHeight(0.35 * SceneMap.scale);
	number.setFont(new Font("Impact", (0.2 * SceneMap.scale)));
	number.setStroke(Color.BLACK);
	number.setStrokeWidth(0.01 * SceneMap.scale);
	number.setFill(Color.WHITE);

	renderTile();
	getChildren().addAll(image, fiche, number);
    }

    public void setTile(Tile tile) {
	this.tile = tile;
	renderTile();
    }

    public Coordinate getPosition() {
	return this.coord;
    }

    private void renderTile() {
	if (tile == null) {
	    image.setImage(new Image("img/tiles/tile_null.png"));
	    return;
	}
	image.setImage(new Image("img/tiles/tile_" + tile.getType() + ".png"));
	if (tile instanceof TileLand) {
	    TileLand landTile = (TileLand) tile;
	    if (landTile.hasRover()) {
		fiche.setImage(new Image("img/tiles/fiche_rover.png"));
		number.setText("");
	    } else {
		fiche.setImage(new Image("img/tiles/fiche.png"));
		if (landTile instanceof TileResource) {
		    number.setText(Integer.toString(((TileResource) landTile).getNumber()));
		} else {
		    number.setText("");
		}
	    }
	} else if (tile instanceof TileSea) {
	    fiche.setImage(null);
	}
    }

}
