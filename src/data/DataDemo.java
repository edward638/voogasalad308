package data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import gamePlayer.Score;

public class DataDemo {

	public static void main(String[] args) throws IOException {

		String descriptionImage = "./data/images/rcd.jpg";
		File imageFile = new File(descriptionImage);
		BufferedImage image = ImageIO.read(imageFile);
		
//		GameInitializer gameInitializer = new GameInitializer("Demo308");
//		
//		GameSaver gameSaver = new GameSaver("Demo308");
//		gameSaver.addDescription("Demo308Game", "This is for demo purposes", image);
//		
//		ModelGameState model = new ModelGameState();
//		GameState testState = model.getState();
//		
//		gameSaver.saveGameState(testState);
		
//		ImageManager im = new ImageManager("default");
//		im.getAllImages();

		String gamepath = "Demo308";
		
		ScoreSaver ss = new ScoreSaver(gamepath);
		
		List<Score> scores = new ArrayList<>();
		
		scores.add(new Score("August", 200));
		scores.add(new Score("Eddie", 100));
		scores.add(new Score("Calvin", 300));
		scores.add(new Score("Jeff", 211));
		scores.add(new Score("Martin", 330));
		scores.add(new Score("Trishul", 110));
		scores.add(new Score("Maddie", 540));
		scores.add(new Score("Summer", 130));
		
		ss.saveScores(scores);
		System.out.println("lol fuck");
		
		List<Score> readScores = ss.loadSavedScores();
		for (Score s : readScores) {
			System.out.println(s);
		}
	}
}
