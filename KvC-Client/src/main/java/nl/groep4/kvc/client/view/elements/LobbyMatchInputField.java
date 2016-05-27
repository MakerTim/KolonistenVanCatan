package nl.groep4.kvc.client.view.elements;

import nl.groep4.kvc.client.util.SoundUtil;

public class LobbyMatchInputField extends LobbyInputField {

	public LobbyMatchInputField(String regex) {
		super();
		registerCheck(regex);
	}

	public LobbyMatchInputField(int xPos, int yPos, String text, String regex) {
		super(xPos, yPos, text);
		registerCheck(regex);
	}

	private void registerCheck(String regex) {
		focusedProperty().addListener(change -> {
			if (!isFocused() && !getText().matches(regex) && !getText().isEmpty()) {
				requestFocus();
				SoundUtil.playError();
			}
		});
	}

}
