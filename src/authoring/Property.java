package authoring;

public class Property {

	private Object myValue;
	private String myName;
	
	public Property(String name, Object value) {
		myName = name;
		myValue = value;
	}
	
	//sets the value of the property
	public void setValue(Object value) {
		myValue = value;
	}
	
	//returns the value of the property
	public Object getValue() {
		return myValue;
	}
	
	//returns the name of the property
	public String getName() {
		return myName;
	}
	
}
