package authoring;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.reflections.Reflections;

import engine.events.elementevents.ElementEvent;

/** 
 * @author: Summer
 **/
public class EngineClassRetriever {
	
	public EngineClassRetriever() {}
	
	public Set<?> getClasses(Class<?> superClass, String packageName) {
		Reflections reflections = new Reflections(packageName);
		return reflections.getSubTypesOf(superClass);
	}

	public Set<String> cleanClassSet(Set<?> classes) {
		Set<String> classNames = new TreeSet<>();
//		classNames = classes.forEach(item -> System.out.println(item));
		return classNames;
	}
	
    public static void main(String[] args){
		EngineClassRetriever events = new EngineClassRetriever();
		System.out.println(events.getClasses(ElementEvent.class, "engine.events.elementevents"));
    }
	
}
