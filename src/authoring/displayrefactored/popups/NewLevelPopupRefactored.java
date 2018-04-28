package authoring.displayrefactored.popups;

import authoring.GameScene;
import authoring.displayrefactored.controllers.Controller;
import authoring.displayrefactored.controllers.LevelPanelController;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * 
 * @author Edward Zhuang
 *
 */
public class NewLevelPopupRefactored extends PopupRefactored{

	private static final int xSize = 300;
	private static final int ySize = 400;
	private Button saveButton;
	private TextField levelText;
	private TextField indexText;
	private LevelPanelController controller;
	
	public NewLevelPopupRefactored(LevelPanelController controller) {
		// TODO Auto-generated constructor stub
		super();
		this.controller = controller;
		open(xSize, ySize);
		generatePopup();
		mapButtons();
	}
	
	@Override
	protected void generatePopup() {
		// TODO Auto-generated method stub
		VBox root = new VBox();

		HBox nameLevel = new HBox();
		levelText = new TextField();
		nameLevel.getChildren().addAll(new Label("Level name: "), levelText);

		HBox levelIndex = new HBox();
		indexText = new TextField();
		levelIndex.getChildren().addAll(new Label("Level number: "), indexText);

		saveButton = new Button(ResourceBundleManager.getAuthoring("Save"));
		root.getChildren().addAll(nameLevel, levelIndex, saveButton);
		
		BorderPane borderPane = getPane();
		borderPane.setCenter(root);
		
	}

	@Override
	protected void mapButtons() {
		// TODO Auto-generated method stub
		saveButton.setOnAction(event -> {
			if(!levelText.getText().isEmpty() && !indexText.getText().isEmpty()) {
				try {
					String levelName = levelText.getText();
					Integer levelIndex = Integer.parseInt(indexText.getText());
					controller.addLevel(levelName, levelIndex);
					close();
					//after slider is implemented, only catch general exception
				} catch(NumberFormatException e) {
					System.out.println("Invalid index input numFormat");
					new Error("Invalid index input"); //this isn't being displayed yet
				}catch(IndexOutOfBoundsException e) {
					System.out.println("Invalid index input OOB");
					//this isn't being displayed yet 
					new Error("Invalid index input"); //eventually to fix this you can get the size of the array and make a slider so they can choose where to put it
				}	
			} else {
				close();
			}
		});
		
	}
	
}
