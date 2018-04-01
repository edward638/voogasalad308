package engine.behaviors;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import engine.GameElement;

public abstract class Behavior {

	private GameElement parent;
	
	
	public Behavior(GameElement ge) {
		parent = ge;
	}
	
	
	public Map<String, Object> reportProperties() {
		Map <String, Object> returnValues = new HashMap<>();
		
		Class<?> objClass = this.getClass();
	    Field[] fields = objClass.getDeclaredFields();
	    for(Field field : fields) {
	    		field.setAccessible(true);
	        String name = field.getName();
	        Object value;
			try {
				value = field.get(this);
				returnValues.put(name,  value);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return returnValues;
	}
	
	protected GameElement getParent() {
		return parent;
	}
}
