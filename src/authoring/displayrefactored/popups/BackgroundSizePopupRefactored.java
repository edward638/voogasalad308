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
	private TextField tfx;
	private TextField tfy;
	
	private static final int popupSizeX = 300;
	private static final int popupSizeY = 200;
	private static final String SAVE = "Save";
	private static final String xSizeText = "Enter the desired X size";
	private static final String ySizeText = "Enter the desired Y size";
	
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
			
			sbi.getImageView().setFitHeight(newWidth);
			sbi.getImageView().setFitWidth(newHeight);
			
			sbis.setxSize(sbi.getImageView().getBoundsInLocal().getWidth());
			sbis.setySize(sbi.getImageView().getBoundsInLocal().getHeight());
						
			sbi.updateDimensions();
			super.close();
		});
	}

}
