package engine.groovy;

import java.util.ArrayList;
import java.util.List;

import engine.GameElement;
import engine.behaviors.Gravity;

public class TestGroovyExecutor {

    public static void main(String[] args){
    		GameElement ge = new GameElement();
//    		List<Double> doubles= new ArrayList<>();
//    		doubles.add(4.1);
//    		doubles.add(2.5);
//    		Gravity g = new Gravity(ge, 3.2, doubles);
    		GroovyExecutor executor = new GroovyExecutor();
    		executor.addToMap("myObject", ge);
    		executor.execute("import engine.GameElement");
    		executor.execute("println myObject");
    		System.out.println(executor.execute("myObject.behaviors.size()"));
//    		executor.execute()
    		
//    		ee.getResponses().stream().forEach(response -> executor.execute(response));
    }
	
}
