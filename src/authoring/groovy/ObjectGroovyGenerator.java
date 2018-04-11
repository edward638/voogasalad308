package authoring.groovy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import authoring.Behavior;
import authoring.GameObject;

public class ObjectGroovyGenerator {

	private GroovyCommandFactory factory;
	
	public ObjectGroovyGenerator() {
		factory = new GroovyCommandFactory();
	}
	
	/*
	 * THESE METHODS SHOULD BE REFACTORED. DUPLICATED CODE
	 */
	public List<Field> generateGroovyFields(GameObject object) {
		List<Field> objectFields = new ArrayList<>();
		for(Behavior curr : object.getBehaviors()) {
			for(Class<?> clazz : factory.getBehaviorFields().keySet()) {
				if(clazz.toString().contains(curr.getName())) {
					objectFields.addAll(factory.getBehaviorFields().get(clazz));
				}
			}
		}
//		return steralizeList(objectFields);
		return objectFields;
	}
	
	public List<Method> generateGroovyMethods(GameObject object) {
		List<Method> objectMethods = new ArrayList<>();
		for(Behavior curr : object.getBehaviors()) {
			for(Class<?> clazz : factory.getBehaviorMethods().keySet()) {
				if(clazz.toString().contains(curr.getName())) {
					objectMethods.addAll(factory.getBehaviorMethods().get(clazz));
				}
			}
		}
		return objectMethods;
	}
	
	private List<String> steralizeList(List<Object> toSteralize) {
		List<String> toReturn = new ArrayList<>();
		for(Object curr : toSteralize) {
			String[] st = curr.toString().split(" ");
			toReturn.add(st[st.length-1]);
		}
		return toReturn;
	}
}
