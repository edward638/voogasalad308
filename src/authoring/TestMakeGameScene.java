package authoring;

import java.io.IOException;
import java.util.ArrayList;

import data.*;
import javafx.scene.image.Image;

public class TestMakeGameScene {

	public TestMakeGameScene() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws IOException {
		
		GameScene gameScene = new GameScene("Level 1");
		BehaviorFactory bf = new BehaviorFactory();
		GameObject go1 = new GameObject(bf.makeBehavior("BasicGameElement"));
		go1.getBehavior("BasicGameElement").getProperty("xPos").setValue(new Double(2));
		go1.getBehavior("BasicGameElement").getProperty("yPos").setValue(new Double(7));
		go1.getBehavior("BasicGameElement").getProperty("elementName").setValue("this is my name");

//		GameObject go2 = new GameObject(bf.makeBehavior("BasicGameElement"));
//		go2.addBehavior(bf.makeBehavior("Movable"));
		System.out.println(go1.getBehaviors());
//		System.out.println(go2.getBehaviors());

		gameScene.addObject(go1);
		
		ArrayList<GameScene> gameList = new ArrayList<>();
		gameList.add(gameScene);
		
		GameInitializer gameInitializer = new GameInitializer("Yolo");
		GameSaver gameSaver = new GameSaver("Yolo");
		gameSaver.addDescription("YOLOTHEGAME", "THIS IS A TEST GAME.", null);
		gameSaver.gameAuthorToXML(gameList);
		
		GameLoader gameLoader = new GameLoader("Yolo");
		GameScene pls = gameLoader.getGameScenes().get(0);
		System.out.println(pls.getMyElements().get(0).getBehaviors());
		
		
		
	}

}
