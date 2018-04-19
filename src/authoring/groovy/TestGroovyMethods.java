package authoring.groovy;

public class TestGroovyMethods {

    public static void main(String[] args){
    		System.out.println("hey");
	    GroovyCommandFactory factory = new GroovyCommandFactory();
	    System.out.println(factory.getBehaviorFields());
	    System.out.println(factory.getBehaviorMethods());
    }
    
}
