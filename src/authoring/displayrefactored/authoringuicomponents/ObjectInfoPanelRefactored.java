package authoring.displayrefactored.authoringuicomponents;

import java.util.Observable;
import java.util.Observer;

import authoring.Behavior;
import authoring.GameObject;
import authoring.ObjectInfoObservable;
import authoring.displayrefactored.controllers.ObjectInfoPanelController;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ObjectInfoPanelRefactored extends AuthoringUIComponentRefactored implements Observer {

	private ScrollPane myScrollPane;
	private VBox myVBox;
	private ListView<Behavior> myBehaviors;
	private GameObject gameObject;
	private ObjectInfoPanelController controller;
	private ObjectInfoObservable observable;
	
	public ObjectInfoPanelRefactored(ObjectInfoPanelController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
	}
	
	@Override
	protected void GenerateComponent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
