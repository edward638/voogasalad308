package authoring;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GameSceneMemento implements GameSceneToCareTaker, GameSceneToOriginator {

	private List<GameObject> gameObjects;
	private List<SceneBackgroundImageSerializable> serializables;
	
	public GameSceneMemento(List<GameObject> gameObjects, List<SceneBackgroundImageSerializable> serializables) {
		gameObjects = new ArrayList<>();
		for (GameObject go: gameObjects) {
			System.out.println(go);
			GameObject copy = new GameObject(go);
			this.gameObjects.add(copy);
		}
		
		this.serializables = serializables;
	}
	
	@Override
	public List<GameObject> getGameObjects() {
		// TODO Auto-generated method stub
		return gameObjects;
	}

	@Override
	public List<SceneBackgroundImageSerializable> getSerializables() {
		// TODO Auto-generated method stub
		return serializables;
	}

}
