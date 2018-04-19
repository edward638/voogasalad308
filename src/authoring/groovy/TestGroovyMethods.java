package authoring.groovy;

import authoring.GameObject;

public class TestGroovyMethods {

    public static void main(String[] args){
    		System.out.print("hey");
	    GroovyCommandFactory factory = new GroovyCommandFactory();
	    System.out.println(factory.getBehaviorFields());
	    System.out.println(factory.getBehaviorMethods());
	    GameObject go = new GameObject();
	    go.addBehavior("Gravity");
	    ObjectGroovyGenerator gen = new ObjectGroovyGenerator();
	    System.out.println(gen.generateGroovyFields(go));
	    System.out.println(gen.generateGroovyMethods(go));
    }
    
}
