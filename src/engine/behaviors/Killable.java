package engine.behaviors;

import engine.GameElement;
import engine.events.gameevents.RemoveGameElementEvent;

public class Killable extends Behavior{
	private Double health;
	private Double decayRate;
	private Double lifeHealth;

	public Killable(GameElement ge, Double health, Double decayRate, Double lifeHealth) {
		super(ge);
		this.health = health;
		this.decayRate = decayRate;
		this.lifeHealth = lifeHealth;
	}
	
	public Killable(GameElement ge, Double health, Double decayRate) {
		this(ge, health, decayRate, 10.0);
	}
	
	public Killable(GameElement ge, Double health) {
		this(ge, health, 0.0);
	}
	
	public Killable(GameElement ge) {
		this(ge, 30.0, 0.0);
	}
	
	public boolean reduceHealth(Double health) {
		this.health -= health;
		return !isAlive();
	}
	
	public void loseLife() {
		this.health -= lifeHealth;
	}
	
	public boolean decayHealth(Double time) {
		this.health -= time*decayRate;
		return !isAlive();
	}
	
	public Double getHealth() {
		return this.health;
	}
	
	public boolean isAlive() {
		if (health < 0) {
			getParent().addGameEvent(new RemoveGameElementEvent(getParent()));
		}
		return (this.health>=0);
	}
	
}
