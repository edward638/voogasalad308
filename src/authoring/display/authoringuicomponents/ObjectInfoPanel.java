package authoring.display.authoringuicomponents;

import java.util.Observable;
import java.util.Observer;

import authoring.ObjectInfoObservable;
import authoring.display.controllers.ObjectInfoPanelController;
import authoring.display.objectinfoboxes.GameObjectInfoBox;
import authoring.display.objectinfoboxes.LibraryObjectInfoBox;
import data.LibraryObservable;
import data.LibraryObserver;

/**
 * 
 * @author Edward Zhuang
 *
 */
public class ObjectInfoPanel extends AuthoringUIComponent implements Observer, LibraryObserver {

	private ObjectInfoPanelController controller;
	private ObjectInfoObservable observable;
	private LibraryObservable libraryObservable;


	public ObjectInfoPanelRefactored(ObjectInfoPanelController controller) {
		this.controller = controller;
	}

	public void setObservable(ObjectInfoObservable observable) {
		this.observable = observable;
	}
	
	public void setLibraryObservable(LibraryObservable libraryObservable) {
		this.libraryObservable = libraryObservable;
	}

	@Override
	protected void generateComponent() {

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		observable = (ObjectInfoObservable) arg0;
		if (observable.getCurrentGameObject() != null) {
		GameObjectInfoBox gameObjectInfoBox = new GameObjectInfoBox(observable.getCurrentGameObject(),observable.getInstances(), controller);	
		gameObjectInfoBox.initializeVBox();
		gameObjectInfoBox.addToBorderPane(getBorderPane());
		} else {
			getBorderPane().getChildren().clear();
		}
		
	}

	@Override
	public void notifyObserver() {
		LibraryObjectInfoBox libraryObjectInfoBox = libraryObservable.getLibraryObjectInfoBox();
		libraryObjectInfoBox.initializeVBox();
		libraryObjectInfoBox.addToBorderPane(getBorderPane());
	}

	@Override
	protected void initializeFXComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void mapFXActions() {
		// TODO Auto-generated method stub
		
	}

}
