package engine;


import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class AudioPlayer {
	public AudioPlayer(String audioFile) {
		Media media = new Media(new File(audioFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}
}
