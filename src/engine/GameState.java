package engine;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import engine.events.elementevents.CollisionEvent;
import engine.events.elementevents.ElementEvent;
import engine.events.gameevents.GameEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Shape;

public class GameState implements Iterable<GameElement>{
	private Set<GameElement> elements;
	private double gameSpeed;
	private double gameTime;
	
	public GameState() {
		//Talk to game data about reading info from file
		gameSpeed = 1;
		gameTime = 0;
		
		
		elements = null;
	}

	public void incrementgameTime(double timeElapsed) {
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
	
	public List<Map<String, Integer>> getDisplayStates() {
		return null;
		
	}
	
	public Set<GameElement> getGameElements() { 
		return elements;
	}
	

	
	public List<GameEvent> updateElements(ElementEvent elementEvent) {
		List<GameEvent> gameEvents = elements.parallelStream()
				.map(c -> c.processEvent(elementEvent))
				.flatMap(List::stream)
				.collect(Collectors.toList());
		return null;
	}

	@Override
	public Iterator<GameElement> iterator() {
		return elements.iterator();
	}

}
