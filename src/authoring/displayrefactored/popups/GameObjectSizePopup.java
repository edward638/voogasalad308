package authoring.displayrefactored.popups;

import authoring.GameObject;
import authoring.displayrefactored.GameObjectImageView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameObjectSizePopup extends PopupRefactored {
	
	private Button save;
	private TextField tf;
	private GameObjectImageView goiv;
	private GameObject go;
	
	private static final int popupSizeX = 300;
	private static final int popupSizeY = 200;
	private static final String SAVE = "Save";
	private static final String promptText = "Enter the desired scale size (a double, 1.0 is 100%)";

	public GameObjectSizePopup(GameObjectImageView ingoiv, GameObject ingo) {
		super();
		goiv = ingoiv;
		go = ingo;
		
		generatePopup();
		mapButtons();
		open(popupSizeX, popupSizeY);
	}

	@Override
	protected void generatePopup() {
		VBox box = new VBox();
		box.getChildren().add(new Text(promptText));
		
		tf = new TextField();
		tf.setMinWidth(popupSizeX);
		box.getChildren().add(tf);
		
		save = new Button(SAVE);
		box.getChildren().add(save);
		
		super.getPane().setCenter(box);
	}

	@Override
	protected void mapButtons() {
		save.setOnAction(e -> {
			double scale = Double.parseDouble(tf.getText());
			
			double oldWidth = goiv.getMyImage().getBoundsInLocal().getWidth();
			double oldHeight = goiv.getMyImage().getBoundsInLocal().getHeight();
			System.out.println("Old width and height: " + oldWidth + " " + oldHeight);
			double newWidth = scale * oldWidth;
			double newHeight = scale * oldHeight;
			
			System.out.println("New width and height: " + newWidth + " " + newHeight);

			
			goiv.getMyImage().setFitWidth(newWidth);
			goiv.getMyImage().setFitHeight(newHeight);
			
			go.getMandatoryBehavior().getProperty("displayWidth").setValue(newWidth);
			go.getMandatoryBehavior().getProperty("displayHeight").setValue(newWidth);
			System.out.println(go.getMandatoryBehavior().getProperty("displayWidth").getValue());
			System.out.println(go.getMandatoryBehavior().getProperty("displayHeight").getValue());
			super.close();
		});
	}

}
