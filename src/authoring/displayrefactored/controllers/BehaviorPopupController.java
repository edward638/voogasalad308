package authoring.displayrefactored.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import authoring.AuthBehavior;
import authoring.BehaviorFactory;
import authoring.GameObject;
import authoring.displayrefactored.popups.behaviorspopup.BehaviorPanel;
import authoring.displayrefactored.popups.behaviorspopup.PropertyPanel;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class BehaviorPopupController extends PopupController {
	private final String PACKAGE_NAME = "engine.behaviors";
	private final String SUPERCLASS_NAME = "engine.behaviors.Behavior";

	private List<GameObject> myGameObjects;
	private BehaviorPanel myBehaviorPanel;
	private PropertyPanel myPropertyPanel;

	public BehaviorPopupController(List<GameObject> gameObjects) {
		myGameObjects = gameObjects;
		getAllBehaviors();
		initializeScreenComponents();
	}

	@Override
	protected void initializeScreenComponents() {
		myBehaviorPanel = new BehaviorPanel(this, myGameObjects, getAllBehaviors());
		myPropertyPanel = new PropertyPanel(this, myGameObjects);
	}

	@Override
	protected void setUpConnections() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void addToGUI(Pane pane) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void refreshView() {
		myBehaviorPanel.refresh();
		myPropertyPanel.refresh();
	}
	
	public List<Node> getPanels() {
		List<Node> panels = new ArrayList<>();
		panels.add(myBehaviorPanel.asNode());
		panels.add(myPropertyPanel.asNode());
		return panels;
	}
	
	private Set<AuthBehavior> getAllBehaviors() {
		BehaviorFactory behaviorFactory = new BehaviorFactory();
		//got the below from StackOverflow
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());

		Reflections reflections = new Reflections(new ConfigurationBuilder()
		    .setScanners(new SubTypesScanner(false), new ResourcesScanner())
		    .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
		    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(PACKAGE_NAME))));
		
		Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
		
		Set<AuthBehavior> allBehaviors = new HashSet<>();
		for(Class<?> c : classes) {
			String fullName = c.getCanonicalName();
			if(!fullName.equals(SUPERCLASS_NAME)) {
				allBehaviors.add(behaviorFactory.makeBehavior(fullName));
			}
		}
		return allBehaviors;
	}
	
	public AuthBehavior getCurrBehavior() {
		return myBehaviorPanel.getCurrBehavior();
	}
}
