package nl.groep4.kvc.client.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundUtil {

	public static void playSound(String soundName) {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(SoundUtil.class.getClassLoader().getResourceAsStream(soundName));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
