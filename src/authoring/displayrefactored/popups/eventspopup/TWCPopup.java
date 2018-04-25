package authoring.displayrefactored.popups.eventspopup;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import authoring.GameObject;
import authoring.displayrefactored.popups.PopupRefactored;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TWCPopup extends PopupRefactored {
	
	private TriggerWindow tw;
	private List<GameObject> allGos;
	private Set<String> uniqueGoNames;
	
	private Button save;
	private static final int xSize = 300;
	private static final int ySize = 200;
	private static final String SAVE = "Save";
	
	public TWCPopup(TriggerWindow intw, List<GameObject> inGos) {
		tw = intw;
		allGos = inGos;
		trainUniqueGameObjects();
		generatePopup();
		mapButtons();
		open(xSize, ySize);
	}

	private void trainUniqueGameObjects() {
		uniqueGoNames = new HashSet<>();
		for (GameObject go : allGos) {
			if (!go.getName().equals(tw.currentObjectName())) {
				uniqueGoNames.add(go.getName());
			}
		}
	}

	@Override
	protected void generatePopup() {
		VBox box = new VBox();
		box.getChildren().add(new Text("Select a GameObject to collide with"));
		
	}

	@Override
	protected void mapButtons() {
		// TODO Auto-generated method stub
		
	}

}
