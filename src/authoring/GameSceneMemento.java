package authoring;

import java.util.List;

public class GameSceneMemento implements GameSceneToCareTaker, GameSceneToOriginator {

	private List<GameObject> gameObjects;
	private List<SceneBackgroundImageSerializable> serializables;
	
	public GameSceneMemento(List<GameObject> gameObjects, List<SceneBackgroundImageSerializable> serializables) {
		this.gameObjects = gameObjects;
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
