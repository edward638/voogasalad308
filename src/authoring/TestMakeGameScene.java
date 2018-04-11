package authoring;

import java.io.IOException;
import java.util.ArrayList;

import data.*;
import engine.tests.ModelGameState;
import javafx.scene.image.Image;

public class TestMakeGameScene {

	public TestMakeGameScene() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws IOException {
		
//		GameScene gameScene = new GameScene("Level 1");
//		BehaviorFactory bf = new BehaviorFactory();
//		GameObject go1 = new GameObject(bf.makeBehavior("BasicGameElement"));
//		go1.getBehavior("BasicGameElement").getProperty("xPos").setValue(new Double(2));
//		go1.getBehavior("BasicGameElement").getProperty("yPos").setValue(new Double(7));
//		go1.getBehavior("BasicGameElement").getProperty("elementName").setValue("this is my name");
//
////		GameObject go2 = new GameObject(bf.makeBehavior("BasicGameElement"));
////		go2.addBehavior(bf.makeBehavior("Movable"));
//		System.out.println(go1.getBehaviors());
////		System.out.println(go2.getBehaviors());
//
//		gameScene.addObject(go1);
//		
//		ArrayList<GameScene> gameList = new ArrayList<>();
//		gameList.add(gameScene);
//		
//		GameInitializer gameInitializer = new GameInitializer("gouttham");
//		GameSaver gameSaver = new GameSaver("gouttham");
//		gameSaver.addDescription("HELLO", "THIS IS A TEST GAME.", null);
//		gameSaver.gameAuthorToXML(gameList);
//		
//		GameLoader gameLoader = new GameLoader("gouttham");
//		GameScene pls = gameLoader.getGameScenes().get(0);
//		System.out.println(pls.getMyElements().get(0).getBehaviors());
		
		ModelGameState model = new ModelGameState();
		GameInitializer i = new GameInitializer("test");
		GameSaver saver = new GameSaver("test");
		saver.saveGameState(model.getState());
		
		
		
	}

}
