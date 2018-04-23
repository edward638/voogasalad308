package data.propertiesFiles;

import java.util.ResourceBundle;

/**
 * Class responsible for accessing items from properties files
 * @author Edward Zhuang
 *
 */
public final class ResourceBundleManager {
	 
	private static ResourceBundle resources;
	private static final String DEFAULT_RESOURCE_PATH = "data/propertiesFiles/";
	private static final String PATHS = "Paths";
	private static final String UI_POSITIONS = "UIPositions";
	private static final String AUTHORING = "Authoring";
	
	/**
	 * retrieves string from Paths.properties
	 * @param s string key
	 * @return resource bundle property value
	 */
	public static String getPath(String s) {
		resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + PATHS);
		return resources.getString(s);
	}
	
	public static int getPosition(String s) {
		resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + UI_POSITIONS);
		return Integer.parseInt(resources.getString(s));
	}
	
	public static String getAuthoring(String s) {
		resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + AUTHORING);
		return resources.getString(s);
	}
	
}
