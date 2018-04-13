package authoring;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Maddie Wilkinson
 *
 */
public class BehaviorFactory {

	private static final String BEHAVIORS_LOCATION = "engine.behaviors.";

	public BehaviorFactory() {

	}

	public Behavior makeBehavior(String className) {
		try {
			Class<?> clazz = Class.forName(BEHAVIORS_LOCATION + className);
			Set<Property> properties = makeProperties(clazz);
			return new Behavior(className, properties);
		} catch (ClassNotFoundException e) {
			new Error("Invalid Behavior");
			System.out.println("Invalid Behavior");
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
