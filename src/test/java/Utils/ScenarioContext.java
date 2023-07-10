package Utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
	
	private Map<String, Object> scenarioContext;
	
	public ScenarioContext() {
		scenarioContext = new HashMap<>();
	}

	public Object getContext(String key) {
		return scenarioContext.get(key.toString());
	}

	public void setContext(String key, Object value) {
		scenarioContext.put(key.toString(), value);
	}
	
	public boolean isContains(String Key) {
		return scenarioContext.containsKey(Key.toString());
	}
	
}
