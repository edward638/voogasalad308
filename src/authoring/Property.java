package authoring;

public class Property {

	//add Class instance variable to check that myValue is an instance of that class
	private Object myValue;
	private String myName;
	
	public Property(String name, Object value) {
		myName = name;
		setValue(value);
	}
	
	//sets the value of the property
	//make sure object is the correct class
	public void setValue(Object value) {
		System.out.println(value.getClass());
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
