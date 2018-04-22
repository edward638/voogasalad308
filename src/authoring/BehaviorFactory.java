package authoring;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Maddie Wilkinson
 *
 */
public class BehaviorFactory {

	public BehaviorFactory() {

	}

	public AuthBehavior makeBehavior(String behaviorName) {
		try {
			Class<?> clazz = Class.forName(behaviorName);
			Set<Property> properties = makeProperties(clazz);
			return new AuthBehavior(behaviorName, properties);
		} catch (ClassNotFoundException e) {
			new Error("Invalid Behavior: " + className);
			System.out.println("Invalid Behavior: " + className);
			e.printStackTrace();
		}
		return null;
	}

	private Set<Property> makeProperties(Class<?> clazz){
		List<Field> fields = getValidFields(clazz);
		Set<Property> properties = new HashSet<>();
		for(Field f : fields) {
			Property prop = new Property(f.getName(), f.getType());
			properties.add(prop);
		}
		return properties;
	}

	private List<Field> getValidFields(Class<?> clazz) {
		Field[] allFields = clazz.getDeclaredFields();
		ArrayList<Field> validFields = new ArrayList<>();
		for(Field f : allFields) {
			String[] modifiers = Modifier.toString(f.getModifiers()).split(" ");
			if(modifiers[0].equals("private")) {
				validFields.add(f);
			}
		}
		return validFields;
	}

}
