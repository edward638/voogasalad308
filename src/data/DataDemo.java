package data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.GameState;
import engine.tests.ModelGameState;
import javafx.scene.image.Image;

public class DataDemo {

	public static void main(String[] args) throws IOException {

		String descriptionImage = "./data/images/rcd.jpg";
		File imageFile = new File(descriptionImage);
		BufferedImage image = ImageIO.read(imageFile);
		
		GameInitializer gameInitializer = new GameInitializer("Demo308");
//		
//		GameSaver gameSaver = new GameSaver("Demo308");
//		gameSaver.addDescription("Demo308Game", "This is for demo purposes", image);
//		
//		ModelGameState model = new ModelGameState();
//		GameState testState = model.getState();
//		
//		gameSaver.saveGameState(testState);
//	
	}
}
