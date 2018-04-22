package engine;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlayer {
	private static MediaPlayer mediaPlayer;
	
	public AudioPlayer(String audioFile, double volume) {
		Media media = new Media(new File(audioFile).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		setVolume(volume);
		mediaPlayer.play();
	}
	
	public void pause() {
		mediaPlayer.pause();
	}
	
	public void stop() {
		mediaPlayer.stop();
	}
	
	protected void setVolume(double volume) {
		mediaPlayer.setVolume(volume);
	}
}
