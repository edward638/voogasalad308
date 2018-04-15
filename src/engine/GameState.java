package engine;

import java.util.ArrayList;
import java.util.List;

public class GameState{
	private List<GameElement> elements;
	private double gameSpeed;
	private double gameTime;
	
	//protected DisplayState displayState;
	
	public GameState() {
		//Talk to game data about reading info from file
		gameSpeed = 1;
		gameTime = 0;	
		elements = new ArrayList<>();
		//displayState = new DisplayState();
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
		//displayState.addNewElement(gameElement);
	}
	
	public void removeGameElement(GameElement gameElement) {
		elements.remove(gameElement);
		//displayState.removeElement(gameElement);
	}
	
	public List<GameElement> getElements() {
		return elements;
	}

}
