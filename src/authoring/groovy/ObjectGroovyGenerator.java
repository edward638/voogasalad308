package authoring.groovy;

import java.util.ArrayList;
import java.util.List;

import authoring.Behavior;
import authoring.GameObject;

/** 
 * 
 * @author: Summer
 **/
public class ObjectGroovyGenerator {

	private GroovyCommandFactory factory;
	
	public ObjectGroovyGenerator() {
		factory = new GroovyCommandFactory();
	}
	
	/*
	 * THESE METHODS SHOULD BE REFACTORED. DUPLICATED CODE
	 */
	public List<String> generateGroovyFields(GameObject object) {
		List<Object> objectFields = new ArrayList<>();
		for(Behavior curr : object.getBehaviors()) {
			for(Class<?> clazz : factory.getBehaviorFields().keySet()) {
				if(clazz.toString().contains(curr.getName())) {
					objectFields.addAll(factory.getBehaviorFields().get(clazz));
				}
			}
		}
		return cleanList(objectFields);
	}
	
	public List<String> generateGroovyMethods(GameObject object) {
		List<Object> objectMethods = new ArrayList<>();
		for(Behavior curr : object.getBehaviors()) {
			for(Class<?> clazz : factory.getBehaviorMethods().keySet()) {
				if(clazz.toString().contains(curr.getName())) {
					objectMethods.addAll(factory.getBehaviorMethods().get(clazz));
				}
			}
		}
		return cleanList(objectMethods);
	}
	
	private List<String> cleanList(List<Object> toSteralize) {
		List<String> toReturn = new ArrayList<>();
		for(Object curr : toSteralize) {
			String[] st = curr.toString().split(" ");
			toReturn.add(st[st.length-1]);
		}
		return toReturn;
	}
}
