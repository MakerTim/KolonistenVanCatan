package nl.groep4.kvc.client.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.FloatControl.Type;

public class SoundUtil {

	private static float volume = 0F;

	public static void playError() {
		playSound("sound/no.wav");
	}

	public static Clip playSound(String soundName) {
		Clip clip = null;
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(SoundUtil.class.getClassLoader().getResourceAsStream(soundName));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			FloatControl volume = (FloatControl) clip.getControl(Type.MASTER_GAIN);
			volume.setValue(-10F + SoundUtil.volume);
			clip.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return clip;
	}
}
