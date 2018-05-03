package engine.audio;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yashas Manjunatha
 * Manages all the AudioPlayer objects in the Engine. Also provides public methods to allow
 * the Player to set the volume of the audio and close all audio players on the close of the Engine.
 */
public class AudioManager {
	List<AudioPlayer> audioPlayers;
	private double volume;
	
	/**
	 * Instantiates the AudioManager. Called when the Engine is instantiated.
	 * @param volume Initial volume for all new AudioPlayer objects. (double between 0 and 1)
	 */
	public AudioManager(double volume) {
		audioPlayers = new ArrayList<>();
		this.volume = volume;
	}
	
	/**
	 * Instantiates a new AudioPlayer and starts playing the audio.
	 * @param mediaFile Path to the Media File to Play
	 * @return The AudioPlayer object createds
	 */
	public AudioPlayer newAudioPlayer(String mediaFile) {
		AudioPlayer audioPlayer = new AudioPlayer(mediaFile, volume);
		audioPlayers.add(audioPlayer);
		return audioPlayer;
	}
	
	/**
	 * This method sets a new volume for all the AudioPlayer objects.
	 * @param newVolume New Volume to Set (double between 0 and 1)
	 */
	public void setVolume(double newVolume) {
		this.volume = newVolume;
		for (AudioPlayer a : audioPlayers) {
			a.setVolume(newVolume);
		}
	}
	
	/**
	 * This method stops all AudioPlayer objects.
	 */
	public void stop() {
		for (AudioPlayer a : audioPlayers) {
			a.stop();
		}
	}
	
	/**
	 * This method pauses all AudioPlayer objects.
	 */
	public void pause() {
		for (AudioPlayer a : audioPlayers) {
			a.pause();
		}
	}
	
	/**
	 * This method resumes playing all AudioPlayer objects.
	 */
	public void play() {
		for (AudioPlayer a : audioPlayers) {
			a.play();
		}
	}
}
