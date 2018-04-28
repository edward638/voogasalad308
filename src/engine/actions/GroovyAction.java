package engine.actions;

import authoring.EventResponse;
import engine.GameElement;
import engine.events.elementevents.CollisionEvent;
import engine.events.elementevents.ElementEvent;
import engine.groovy.GroovyExecutor;

public class GroovyAction implements Action {
	
	private GroovyExecutor executor;
	private String content;
	
	public GroovyAction() {
		executor = new GroovyExecutor();
	}
	
	public GroovyAction(String executionContent) {
		this();
		content = executionContent;
	}
	
	public String getContent() {
		return content;
	}
	
	@Override
	public void act(ElementEvent e, GameElement ge) {
		executor.addToMap(ge.getIdentifier(), ge);
		if (e instanceof CollisionEvent) {
			CollisionEvent ce = (CollisionEvent) e;
			executor.addToMap(ce.getOtherElement(ge).getIdentifier(), ce.getOtherElement(ge));
		}
		executor.execute(content);
	}

	public void setContent(String newContent) {
		content = newContent;
	}
	
	public GroovyAction clone() {
		GroovyAction er = new GroovyAction();
		er.setContent(this.getContent());
		return er;
	}
}
