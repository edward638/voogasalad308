package authoring;

public class EventResponse {
	
	private String myName;
	private Behavior myBehavior;

	public EventResponse() {
		
	}
	
	public String getName() {
		return myName;
	}
	
	public Behavior getBehavior() {
		return myBehavior;
	}
	
	public void setName(String name) {
		myName = name;
	}
	
	public void setBehavior(Behavior behave) {
		myBehavior = behave;
	}
	
}
