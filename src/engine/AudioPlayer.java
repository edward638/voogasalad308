package engine;


import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlayer {
	private static MediaPlayer mediaPlayer;
	
	public AudioPlayer(String audioFile) {
		Media media = new Media(new File(audioFile).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}
}
