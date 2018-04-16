package engine.groovy;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class GroovyExecutor {

	private ScriptEngine engine;
	
	public GroovyExecutor() {
		engine = new ScriptEngineManager().getEngineByName("groovy");
	}
	
	public Object execute(String script) {
		try {
			return engine.eval(script);
		} catch (ScriptException e) {
			throw new GroovyException("cannot evaluate groovy expression");
		}
	}
	
	public void addToMap(String name, Object toAdd) {
		engine.put(name, toAdd);
	}
	
}
