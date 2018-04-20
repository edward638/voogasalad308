package engine;

import java.util.ArrayList;
import java.util.List;

public class AudioManager {
	List<AudioPlayer> audioPlayers;
	private double volume;
	
	public AudioManager(double volume) {
		audioPlayers = new ArrayList<>();
		this.volume = volume;
	}
	
	public AudioPlayer newAudioPlayer(String mediaFile) {
		AudioPlayer audioPlayer = new AudioPlayer(mediaFile, volume);
		audioPlayers.add(audioPlayer);
		return audioPlayer;
	}
	
	public void setVolume(double newVolume) {
		this.volume = newVolume;
		for (AudioPlayer a : audioPlayers) {
			a.setVolume(newVolume);
		}
	}
}
