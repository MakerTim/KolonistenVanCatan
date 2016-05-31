package nl.groep4.kvc.client.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundUtil {

    private static float volume = 0F;
    private static Clip teamsongKvC;

    public static void playError() {
	playSound("sound/no.wav");
    }

    public static void playThemesong() {
	stopThemesong();
	teamsongKvC = playSound("sound/themesongKvC.wav");
	teamsongKvC.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void stopThemesong() {
	if (themesongIsPlaying()) {
	    teamsongKvC.stop();
	    teamsongKvC.flush();
	    teamsongKvC = null;
	}
    }

    public static Clip playSound(String soundName) {
	Clip clip = null;
	try {
	    AudioInputStream audioInputStream = AudioSystem
		    .getAudioInputStream(SoundUtil.class.getClassLoader().getResourceAsStream(soundName));
	    clip = AudioSystem.getClip();
	    clip.open(audioInputStream);
	    FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	    // clip.loop(x);
	    // BooleanControl muteControl = (BooleanControl)
	    // clip.getControl(BooleanControl.Type.MUTE);
	    // clip.stop();
	    volume.setValue(-10F + SoundUtil.volume);
	    clip.start();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return clip;
    }

    public static boolean themesongIsPlaying() {
	return teamsongKvC != null;
    }

}
