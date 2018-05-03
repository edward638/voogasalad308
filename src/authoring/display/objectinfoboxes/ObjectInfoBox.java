package authoring.display.objectinfoboxes;

import java.util.concurrent.BlockingDeque;

import authoring.GameObject;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public abstract class ObjectInfoBox {
	
	private VBox vBox;
	
	private static final double PANE_PREF_WIDTH = 250;
	private static final double DEFAULT_SPACING = 5;
	
	
	public ObjectInfoBox() {
		vBox = new VBox(DEFAULT_SPACING);
		vBox.setAlignment(Pos.CENTER);
	}
	
	protected VBox getVBox() {
		return vBox;
	}
	
	public abstract void initializeFXComponents();
	
	public abstract void mapFXActions();
	
	public abstract void initializeVBox();
	
	public void addToBorderPane(BorderPane pane) {
		pane.setCenter(vBox);
	}
	
}
