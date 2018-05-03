package data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javafx.scene.media.Media;

public class AudioManager {

    private static final String BACKSLASH = "/";
    private static final String AUDIO = "audio/";
    private String gameLocation;
    private String gameAudioLocation;
    private static final String baseLocation = "./data/gamedata/games/";
	
	public AudioManager(String gameName) {
		gameLocation = baseLocation + gameName + BACKSLASH;
		gameAudioLocation = gameLocation + AUDIO;
	}
	
	public void storeAudio(File audioFile) {
		try {
			System.out.println("storeAudio AudioManager: " + gameAudioLocation + audioFile.getName());
			Files.copy(audioFile.toPath(), 
					(new File(gameAudioLocation + audioFile.getName())).toPath(), 
					StandardCopyOption.REPLACE_EXISTING);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
	
	public Media getAudioMedia(String audioName) {
		Media media = new Media(new File(gameAudioLocation+audioName+".mp3").toURI().toString());
		return media;
	}
	
}
