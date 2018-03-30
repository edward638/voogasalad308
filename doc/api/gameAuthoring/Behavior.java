//has 3 things: a list of properties, which any behavior of that type will have
//for instance: movement will have x velocity, y velocity
//within this, you'll have actions that the property uses 
//if someone says a gameobject has movement behavior, it'll automatically add an xvelocity and yvelocity,
//as well as a list of actions associated with that behavior that the user can choose
public interface Behavior {
	
	public void setProperties(List<Property> properties) //adds properties 
	
	
}
