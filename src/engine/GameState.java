package engine;

import java.util.ArrayList;
import java.util.List;

public class GameState{
	private List<GameElement> elements;
	private double gameSpeed;
	private double gameTime;
	
	public GameState() {
		//Talk to game data about reading info from file
		gameSpeed = 1;
		gameTime = 0;	
		elements = new ArrayList<>();
	}

	public void incrementGameTime(double timeElapsed) {
		gameTime+=timeElapsed;
	}
	
	public double getGameTime() {
		return gameTime;
	}
	
	protected double getGameSpeed() {
		return gameSpeed;
	}

	public void addGameElement(GameElement gameElement) {
		elements.add(gameElement);
	}
	
	public void removeGameElement(GameElement gameElement) {
		elements.remove(gameElement);
	}
	
	public List<GameElement> getElements() {
		return elements;
	}

}
