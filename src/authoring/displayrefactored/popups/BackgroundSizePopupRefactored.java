package authoring.displayrefactored.popups;

import authoring.SceneBackgroundImage;
import authoring.SceneBackgroundImageSerializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class BackgroundSizePopupRefactored extends PopupRefactored {
	
	SceneBackgroundImage sbi;
	SceneBackgroundImageSerializable sbis;
	
	private Button save;
	private TextField tf;
	
	private static final int popupSizeX = 300;
	private static final int popupSizeY = 200;
	private static final String SAVE = "Save";
	private static final String promptText = "Enter the desired scale size (a double, 1.0 is 100%)";

	public BackgroundSizePopupRefactored(SceneBackgroundImage inSbi, SceneBackgroundImageSerializable inSbis) {
		super();
		
		sbi = inSbi;
		sbis = inSbis;
		
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
			double oldWidth = sbi.getImageView().getBoundsInLocal().getWidth();
			double oldHeight = sbi.getImageView().getBoundsInLocal().getHeight();
			sbi.getImageView().setFitHeight(scale * oldWidth);
			sbi.getImageView().setFitWidth(scale * oldHeight);
			
			sbis.setxSize(sbi.getImageView().getBoundsInLocal().getWidth());
			sbis.setySize(sbi.getImageView().getBoundsInLocal().getHeight());
						
			sbi.updateDimensions();
			super.close();
		});
	}

}
