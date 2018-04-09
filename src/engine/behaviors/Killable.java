package engine.behaviors;

import engine.GameElement;

public class Killable extends Behavior{
	private Double health;
	private Double decayRate;
	
	public Killable(GameElement ge, Double health, Double decayRate) {
		super(ge);
		this.health = health;
		this.decayRate = decayRate;
	}
	
	public Killable(GameElement ge, Double health) {
		this(ge, health, 0.0);
	}
	
	public Killable(GameElement ge) {
		this(ge, 1.0, 0.0);
	}
	
	public boolean reduceHealth(Double health) {
		this.health -= health;
		return !isAlive();
	}
	
	public boolean decayHealth(Double time) {
		this.health -= time*decayRate;
		return !isAlive();
	}
	
	public Double getHealth() {
		return this.health;
	}
	
	public boolean isAlive() {
		return (this.health>=0);
	}
	
}
