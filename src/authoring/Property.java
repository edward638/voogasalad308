package authoring;

public class Property {

	private Object myValue;
	private String myName;
	private Class myValueType;
	
	public Property(String name, Object value) {
		myName = name;
		setValue(value);
	}
	
	//sets the value of the property
	public void setValue(Object value) {
//		if (value instanceof myValueType) {
			myValue = value;
//		}
//		else { 
//			new Error("value entered is not the correct type");
//		}
	}
	
	//returns the value of the property
	public Object getValue() {
		return myValue;
	}
	
	//returns the name of the property
	public String getName() {
		return myName;
	}
	
	//returns the name of the property
	public String toString() {
		return myName + " = " + myValue;
	}
	
}
