package authoring;

import java.util.HashSet;
import java.util.Set;

public class Behavior {
	
	private Set<Property> myProperties;
	private String myName;
	
	public Behavior() {
		
	}

	public Behavior (String name, Set<Property> properties) {
		myName = name;
		myProperties = new HashSet<Property>();
		myProperties = properties;
	}
	
	//adds properties
	public void addProperty(Property property)  {
		myProperties.add(property);
	}
	
	//gets properties
	public Set<Property> getProperties()  {
		return myProperties;
	}
	
	//returns the name of the property
	public String getName() {
		return myName;
	}
	
}
