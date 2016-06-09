package nl.groep4.kvc.client.view.elements;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.map.TileLand;
import nl.groep4.kvc.common.map.TileResource;
import nl.groep4.kvc.common.map.TileSea;
import nl.groep4.kvc.common.util.CollectionUtil;

public class ClientTile extends StackPane {

    private static final Map<String, Image> CACHE = new HashMap<>();

    private Tile tile;
    private Line[] lines = new Line[6];
    private Coordinate coord;

    private ImageView image;
    private ImageView fiche;
    private Text number;

    public ClientTile(Coordinate coord) {
	this.coord = coord;
	image = new ImageView();
	fiche = new ImageView("img/tiles/fiche.png");
	number = new Text();
	Pane linePane = new Pane();

	image.setFitWidth(1 * SceneMap.scale);
	image.setFitHeight(0.86 * SceneMap.scale);
	fiche.setFitWidth(0.35 * SceneMap.scale);
	fiche.setFitHeight(0.35 * SceneMap.scale);
	number.setFont(new Font("Impact", (0.2 * SceneMap.scale)));
	number.setStroke(Color.BLACK);
	number.setStrokeWidth(0.01 * SceneMap.scale);
	number.setFill(Color.WHITE);

	// Offset fix for fxpanes?
	double xFix = 0.5;
	double yFix = 0.5;
	double offset = 1.05;
	for (Direction direction : Direction.values()) {
	    int i = direction.ordinal();
	    Coordinate a = CollectionUtil.getInRange(Point.values(), i).realOffset().multiply(offset);
	    Coordinate b = CollectionUtil.getInRange(Point.values(), i + 1).realOffset().multiply(offset);
	    Line line = new Line(a.getX() * SceneMap.scale, a.getY() * SceneMap.scale, b.getX() * SceneMap.scale,
		    b.getY() * SceneMap.scale);
	    line.setStroke(new Color(1, 0, 0, 1));
	    line.setStrokeWidth(10);
	    lines[i] = line;
	    linePane.getChildren().add(line);
	}
	linePane.setTranslateX(xFix * SceneMap.scale * 1.20);
	linePane.setTranslateY(yFix * SceneMap.scale * 1.08);
	renderTile();
	getChildren().addAll(image, fiche, number, linePane);
    }

    public void setTile(Tile tile) {
	this.tile = tile;
	renderTile();
    }

    public Coordinate getPosition() {
	return this.coord;
    }

    private Image cacheImage(String url) {
	if (!CACHE.containsKey(url)) {
	    CACHE.put(url, new Image(url));
	}
	return CACHE.get(url);
    }

    private void renderTile() {
	if (tile == null) {
	    image.setImage(cacheImage("img/tiles/tile_null.png"));
	    return;
	}
	image.setImage(cacheImage("img/tiles/tile_" + tile.getType() + ".png"));
	if (tile instanceof TileLand) {
	    TileLand landTile = (TileLand) tile;
	    if (landTile.hasRover()) {
		fiche.setImage(cacheImage("img/tiles/fiche_rover.png"));
		number.setText("");
	    } else {
		fiche.setImage(cacheImage("img/tiles/fiche.png"));
		if (landTile instanceof TileResource) {
		    number.setText(Integer.toString(((TileResource) landTile).getNumber()));
		} else {
		    number.setText("");
		}
	    }
	} else if (tile instanceof TileSea) {
	    fiche.setImage(null);
	}
	for (Direction direction : Direction.values()) {
	    Street street = tile.getStreet(direction);
	    Line line = lines[direction.ordinal()];
	    if (street != null && street.getOwner() != null) {
		try {
		    line.setFill(street.getOwner().getColor().getColor());
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    } else {
		line.setFill(new Color(0, 0, 0, 0));
	    }
	}
    }

}
