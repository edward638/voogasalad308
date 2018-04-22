package authoring.display.eventspopup;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import authoring.AuthBehavior;
import authoring.Game;
import authoring.GameObject;
import authoring.groovy.GroovyCommandFactory;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MFWindow extends VBox {

	private EventsPopUpController epuc;
	private Game game;
	private GameObject go;
	private AuthBehavior currentBehavior;
	private GroovyCommandFactory gcf;
	
	private static final String NONVALID = "No Behavior or Event selected";
	
	public MFWindow(EventsPopUpController myEPUC, Game myGame, GameObject myGo) {
		game = myGame;
		go = myGo;
		epuc = myEPUC;
		currentBehavior = null;
		gcf = new GroovyCommandFactory();
		assignCurrentBehavior();
		createVBox();
	}
	
	public void createVBox() {
		this.getChildren().clear();
		this.setPadding(new Insets(10));
	    this.setSpacing(8);
	    this.setPrefWidth(200);
	    Text title = new Text("Methods and Fields");
	    this.getChildren().add(title);
	    createLists();
	}
	
	private void createLists() {
		if (currentBehavior == null || !epuc.validEvent()) {
			Text nonvalid = new Text(NONVALID);
			this.getChildren().add(nonvalid);
			return;
		}
		addMethods();
		addFields();
	}
	
	private void addMethods() {
		List<Method> methods = gcf.getMethods(currentBehavior);
		if (methods == null) {
			Text noMethods = new Text("No available methods for this behavior");
			this.getChildren().add(noMethods);
			return;
		}
		ListView<Method> methodsList = new ListView<>();
		methodsList.getItems().addAll(methods);
		methodsList.setOnMouseClicked(e -> methodsClicked(methodsList.getSelectionModel().getSelectedItem()));
		
	}
	private void methodsClicked(Method m) {
		// This is something you can implement on epuc, since you can set text to the text plane in the groovy window
	}
	
	private void addFields() {
		List<Field> fields = gcf.getFields(currentBehavior);
		if (fields == null) {
			Text noFields = new Text("No available fields for this behavior");
			this.getChildren().add(noFields);
			return;
		}
		ListView<Field> fieldsList = new ListView<>();
		fieldsList.getItems().addAll(fields);
		fieldsList.setOnMouseClicked(e -> fieldsClicked(fieldsList.getSelectionModel().getSelectedItem()));
	}
	
	private void fieldsClicked(Field f) {
		// This is something you can implement on epuc, since you can set text to the text plane in the groovy window
	}
	
	private void assignCurrentBehavior() {
		currentBehavior = epuc.getCurrBehavior();
	}
}
