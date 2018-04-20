package engine.behaviors;

import engine.GameElement;

public class Killer extends Behavior{
	private Double damagePower;
	
	public Killer(GameElement ge, Double damagePower) {
		super(ge);
		this.damagePower = damagePower;
	}
	
	public Double getDamagePower() {
		return damagePower;
	}
	
}
