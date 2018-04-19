package engine.behaviors;

import engine.GameElement;
import engine.events.gameevents.AddGameElementEvent;

public class AddsGameElement extends Behavior{
	private GameElement creator;
	private GameElement elementToCreate;
	public AddsGameElement(GameElement ge, GameElement elementToCreate) {
		super(ge);
		
		creator = ge;
		this.elementToCreate = elementToCreate;
	}
	public void addGameElement() {
		MandatoryBehavior mbcreator = (MandatoryBehavior) creator.getBehavior(MandatoryBehavior.class);
		MandatoryBehavior mbcreated = (MandatoryBehavior) elementToCreate.getBehavior(MandatoryBehavior.class);
		mbcreated.setPosition(mbcreator.getX(), mbcreator.getY());
		System.out.println("Creator " + creator.getIdentifier());
		System.out.println("Element to create " + elementToCreate.getIdentifier());
		System.out.println("Created position " + mbcreated.getPosition());
		getParent().addGameEvent(new AddGameElementEvent(elementToCreate));
	}
}