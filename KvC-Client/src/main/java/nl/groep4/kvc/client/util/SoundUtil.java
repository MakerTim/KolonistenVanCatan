package nl.groep4.kvc.client.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.FloatExpression;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ReadOnlyFloatWrapper;

/**
 * Controls the theme-song which is playing in the lobby.
 * 
 * @author Tim
 * @version 1.0
 */
public class SoundUtil {

    private static final float VOLUME_LEVEL = 30F;
    private static FloatExpression volume = new ReadOnlyFloatWrapper(0F);
    private static Clip teamsongKvC;
    private static Clip playMusic;

    /**
     * Sound that will be played when user clicks on the button "End turn"
     */
    public static void playNextTurn() {
	playSound("sound/newturn.wav");
    }

    /**
     * Music file to play if an error occurs
     */
    public static void playError() {
	playSound("sound/no.wav");
    }

    /**
     * Music file to play when in lobby
     */
    public static void playThemesong() {
	stopThemesong();
	teamsongKvC = playSound("sound/themesongKvC.wav");
	if (teamsongKvC != null) {
	    teamsongKvC.loop(Clip.LOOP_CONTINUOUSLY);
	}
    }

    /**
     * Music file to play when in game
     */
    public static void playGamesong() {
	stopThemesong();
	playMusic = playSound("sound/playMusic.wav");
	if (playMusic != null) {
	    playMusic.loop(Clip.LOOP_CONTINUOUSLY);
	}
    }

    /**
     * Stops the music
     */
    public static void stopThemesong() {
	if (themesongIsPlaying()) {
	    teamsongKvC.stop();
	    teamsongKvC.flush();
	    teamsongKvC = null;
	}
    }

    /**
     * Controls the sound volume
     * 
     * @param soundName
     * @return the clip that is played
     */
    public static Clip playSound(String soundName) {
	try {
	    AudioInputStream audioInputStream = AudioSystem
		    .getAudioInputStream(SoundUtil.class.getClassLoader().getResource(soundName));
	    Clip clip = AudioSystem.getClip();
	    clip.open(audioInputStream);
	    FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	    volume.setValue(-10F + SoundUtil.volume.get());
	    InvalidationListener iListener = change -> {
		if (clip.isActive()) {
		    volume.setValue(-10F + SoundUtil.volume.get());
		}
	    };
	    SoundUtil.volume.addListener(iListener);
	    clip.addLineListener(event -> {
		if (event.getType() == LineEvent.Type.STOP) {
		    event.getLine().close();
		} else if (event.getType() == LineEvent.Type.CLOSE) {
		    SoundUtil.volume.removeListener(iListener);
		}
	    });
	    clip.start();
	    return clip;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return null;
    }

    /**
     * TeamsongKvC is playing
     * 
     * @return teamsongKvC not equal to null
     */
    public static boolean themesongIsPlaying() {
	return teamsongKvC != null;
    }

    /**
     * Determines volume level
     * 
     * @return gets volume level
     */
    public static float getVolumeLevel() {
	return SoundUtil.volume.get() / VOLUME_LEVEL;
    }

    /**
     * sets volume level value
     * 
     * @param volume
     *            The volume of the sound
     */
    public static void setVolume(float volume) {
	((FloatProperty) SoundUtil.volume).setValue(volume * VOLUME_LEVEL);
    }

}
