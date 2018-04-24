package authoring.displayrefactored.popups.eventspopup;

import authoring.displayrefactored.popups.PopupRefactored;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TWPopup extends PopupRefactored {
	
	private TriggerWindow tw;
	private TextField tf;
	private String kc;
	private Button save;
	private static final int xSize = 300;
	private static final int ySize = 200;
	private static final String SAVE = "Save";

	public TWPopup(TriggerWindow intw) {
		intw = tw;
		tf = new TextField();
		save = new Button(SAVE);
		generatePopup();
		mapButtons();
		open(xSize, ySize);
	}

	@Override
	protected void generatePopup() {
		VBox box = new VBox();
		box.getChildren().add(new Text("Configure a keybinding"));
		tf.setOnKeyTyped(e -> getKey(e.getCharacter()));
		box.getChildren().add(tf);
		box.getChildren().add(save);
		super.getPane().setCenter(box);
	}
	
	private void getKey(String inkc) {
		kc = inkc;
		tf.clear();
	}
	@Override
	protected void mapButtons() {
		save.setOnAction(e -> {
			tw.setKeyCode(kc);
			close();
		});
	}

}
