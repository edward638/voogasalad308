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
	
	public void reduceHealth(Double health) {
		this.health -= health;
		if (health < 0) {
			getParent().addGameEvent(new RemoveGameElementEvent(getParent()));
		}
	}
	
	public void loseLife() {
		reduceHealth(lifeHealth);
	}
	
	public void decayHealth(Double time) {
		reduceHealth(time*decayRate);
	}
	
	public Double getHealth() {
		return this.health;
	}
	
	public boolean isAlive() {
		return (this.health>=0);
	}
	
}
