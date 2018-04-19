package engine.actions;
import java.util.List;

import engine.GameElement;
import engine.events.elementevents.ElementEvent;
import engine.events.gameevents.GameEvent;

@FunctionalInterface
public interface Action {
	public void act(ElementEvent e, GameElement ge);
}
