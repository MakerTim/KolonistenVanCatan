package nl.groep4.kvc.client.view.elements;

import javafx.scene.paint.Color;
import nl.groep4.kvc.client.util.SoundUtil;

public class LobbyFilterdInputField extends LobbyInputField {

	public LobbyFilterdInputField(String regex) {
		super();
		registerCheck(regex);
	}

	public LobbyFilterdInputField(int xPos, int yPos, String text, String regex) {
		super(xPos, yPos, text);
		registerCheck(regex);
	}

	private void registerCheck(String regex) {
		setOnKeyTyped(key -> {
			if (!key.getCharacter().equals("\t") && !key.getCharacter().matches(regex)) {
				key.consume();
				setBorderColor(Color.RED);
				SoundUtil.playError();
			} else {
				setBorderColor(Color.BLACK);
			}
		});
	}

}
