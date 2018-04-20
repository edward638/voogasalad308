package authoring;

import java.util.Set;

import org.reflections.Reflections;

import engine.events.elementevents.ElementEvent;

public class EngineClassRetriever {
	
	public Set<?> getClasses(Class<?> superClass) {
		Reflections reflections = new Reflections();
		return reflections.getSubTypesOf(superClass);
	}
	
    public static void main(String[] args){
		EngineClassRetriever events = new EngineClassRetriever();
		System.out.println(events.getClasses(ElementEvent.class));
}
	
}
