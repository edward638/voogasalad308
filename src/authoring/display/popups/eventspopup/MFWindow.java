package authoring.display.popups.eventspopup;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import authoring.AuthBehavior;
import authoring.GameObject;
import authoring.display.controllers.EventsPopupController;
import authoring.groovy.GroovyCommandFactory;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MFWindow extends VBox {

	private EventsPopupController epuc;
	private List<GameObject> gos;
	private AuthBehavior currentBehavior;
	private GroovyCommandFactory gcf;
	
	private static final String NONVALID = "No Behavior or Event selected";
	
	public MFWindow(EventsPopupController myEPUC, List<GameObject> myGos) {
		gos = myGos;
		epuc = myEPUC;
		currentBehavior = null;
		gcf = new GroovyCommandFactory();
		createVBox();
	}
	
	public void createVBox() {
		this.getChildren().clear();
		this.setPadding(new Insets(10));
	    this.setSpacing(8);
	    this.setMinWidth(200);
	    Text title = new Text("Methods and Fields");
	    this.getChildren().add(title);
	    createLists();
	}
	
	private void createLists() {
		assignCurrentBehavior();
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
			Text noMethods = new Text("No available methods\nfor this behavior");
			this.getChildren().add(noMethods);
			return;
		}
		this.getChildren().add(new Text("Methods"));
		List<String> methodNames = new ArrayList<>();
		methods.forEach(m -> parseMethod(m, methodNames));
		ListView<String> methodsList = new ListView<>();
		methodsList.setMinHeight(100);
		methodsList.getItems().addAll(methodNames);
		methodsList.setOnMouseClicked(e -> methodsClicked(methodsList.getSelectionModel().getSelectedItem()));
		this.getChildren().add(methodsList);
		
	}
	private void parseMethod(Method m, List<String> list) {
		String[] holder = m.toString().split(" ");
		String methodName = holder[holder.length - 1];
		String[] name = holder[holder.length - 1].split("\\.");
		String prefix = name[0] + "." + name[1] + "." + name[2] + ".";
		methodName = methodName.replace(prefix, "");
		list.add(methodName);
	}
	private void methodsClicked(String method) {
		// This is something you can implement on epuc, since you can set text to the text plane in the groovy window
		epuc.concatenateString(method, "MFWindow");
	}
	
	private void addFields() {
		List<Field> fields = gcf.getFields(currentBehavior);
		if (fields == null) {
			Text noFields = new Text("No available fields\nfor this behavior");
			this.getChildren().add(noFields);
			return;
		}
		this.getChildren().add(new Text("Fields"));
		List<String> fieldNames = new ArrayList<>();
		fields.forEach(f -> parseField(f, fieldNames));
		ListView<String> fieldsList = new ListView<>();
		fieldsList.setMinHeight(100);
		fieldsList.getItems().addAll(fieldNames);
		fieldsList.setOnMouseClicked(e -> fieldsClicked(fieldsList.getSelectionModel().getSelectedItem()));
		this.getChildren().add(fieldsList);
	}
	private void parseField(Field f, List<String> list) {
		String[] holder = f.toString().split(" ");
		String fieldName = holder[holder.length - 1];
		String[] name = holder[holder.length - 1].split("\\.");
		String prefix = name[0] + "." + name[1] + "." + name[2] + ".";
		fieldName = fieldName.replace(prefix, "");
		list.add(fieldName);
	}
	private void fieldsClicked(String field) {
		// This is something you can implement on epuc, since you can set text to the text plane in the groovy window
		epuc.concatenateString(field, "MFWindow");
	}
	
	private void assignCurrentBehavior() {
		currentBehavior = epuc.getCurrBehavior();
	}
}
