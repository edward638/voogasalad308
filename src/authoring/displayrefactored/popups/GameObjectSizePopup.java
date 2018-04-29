package authoring.displayrefactored.popups;

import authoring.GameObject;
import authoring.displayrefactored.GameObjectImageView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameObjectSizePopup extends PopupRefactored {
	
	private Button save;
	private TextField tfx;
	private TextField tfy;
	private GameObjectImageView goiv;
	private GameObject go;
	
	private static final int popupSizeX = 300;
	private static final int popupSizeY = 200;
	private static final String SAVE = "Save";
	private static final String xSizeText = "Enter the desired X size";
	private static final String ySizeText = "Enter the desired Y size";


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
		box.getChildren().add(new Text(xSizeText));
		
		tfx = new TextField();
		tfx.setMinWidth(popupSizeX);
		box.getChildren().add(tfx);
		
		box.getChildren().add(new Text(ySizeText));
		
		tfy = new TextField();
		tfy.setMinWidth(popupSizeX);
		box.getChildren().add(tfy);
		
		save = new Button(SAVE);
		box.getChildren().add(save);
		
		super.getPane().setCenter(box);
	}

	@Override
	protected void mapButtons() {
		save.setOnAction(e -> {
			double newWidth = Double.parseDouble(tfx.getText());
			double newHeight = Double.parseDouble(tfy.getText());
			
//			double oldWidth = goiv.getMyImage().getBoundsInLocal().getWidth();
//			double oldHeight = goiv.getMyImage().getBoundsInLocal().getHeight();
//			System.out.println("Old width and height: " + oldWidth + " " + oldHeight);
//			double newWidth = scale * oldWidth;
//			double newHeight = scale * oldHeight;
//			
//			System.out.println("New width and height: " + newWidth + " " + newHeight);

			
			goiv.getMyImage().setFitWidth(newWidth);
			goiv.getMyImage().setFitHeight(newHeight);
			
			go.getMandatoryBehavior().getProperty("displayWidth").setValue(newWidth);
			go.getMandatoryBehavior().getProperty("displayHeight").setValue(newHeight);
//			System.out.println(go.getMandatoryBehavior().getProperty("displayWidth").getValue());
//			System.out.println(go.getMandatoryBehavior().getProperty("displayHeight").getValue());
			super.close();
		});
	}

}
