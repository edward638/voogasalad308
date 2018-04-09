package engine.events.elementevents;

import java.awt.Point;

public class MouseInputEvent extends ElementEvent {
	
	Point mouseLocation;
	
	
	public MouseInputEvent(Point p) {
		this.mouseLocation = p;
	}
	
	public MouseInputEvent(double x, double y) {
		this.mouseLocation.setLocation(x, y);
	}
	
	public double getMouseX() {
		return mouseLocation.getX();
	}
	
	public double getMouseY() {
		return mouseLocation.getY();
	}

	public Point getMouseLocation() {
		return mouseLocation;
	}
	
	@Override
	public String toString() {
		return "Mouse Input Event: " + "xpos(" + getMouseX() + ") " + "ypos(" + getMouseY() + " ) location clicked";
	}
	
}
