package authoring.displayrefactored.popups.eventspopup;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import authoring.GameObject;
import authoring.displayrefactored.popups.PopupRefactored;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TWCPopup extends PopupRefactored {
	
	private TriggerWindow tw;
	private List<GameObject> allGos;
	private Set<String> uniqueGoNames;
	private ComboBox<String> otherObjects;
	private String selectedObject;
	
	private Button save;
	private static final int xSize = 300;
	private static final int ySize = 200;
	private static final String SAVE = "Save";
	
	public TWCPopup(TriggerWindow intw, List<GameObject> inGos) {
		tw = intw;
		allGos = inGos;
		selectedObject = "";
		trainUniqueGameObjects();
		generatePopup();
		mapButtons();
		open(xSize, ySize);
	}

	private void trainUniqueGameObjects() {
		uniqueGoNames = new TreeSet<>();
		for (GameObject go : allGos) {
			if (!go.getName().equals(tw.currentObjectName())) {
				uniqueGoNames.add(go.getName());
			}
		}
	}

	@Override
	protected void generatePopup() {
		VBox box = new VBox();
		box.getChildren().add(new Text("Select a GameObject to collide with:"));
		
		otherObjects = new ComboBox<>();
		otherObjects.getItems().addAll(uniqueGoNames);
		otherObjects.setOnAction(e -> comboBoxSelect(otherObjects.getSelectionModel().getSelectedItem()));
		otherObjects.setMinWidth(xSize);
		box.getChildren().add(otherObjects);
		
		save = new Button(SAVE);
		box.getChildren().add(save);
		
		super.getPane().setCenter(box);
	}
	
	private void comboBoxSelect(String goName) {
		selectedObject = goName;
	}

	@Override
	protected void mapButtons() {
		save.setOnAction(e -> {
			tw.setCollideObject(selectedObject);
			tw.updateCollideObject();
			tw.createVBox();
			close();
		});
	}

}
