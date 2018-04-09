package authoring;

import javafx.scene.image.Image;

public class TestMakeGameScene {

	public TestMakeGameScene() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		
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
	}

}
