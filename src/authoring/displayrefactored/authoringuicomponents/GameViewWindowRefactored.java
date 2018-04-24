package authoring.displayrefactored.authoringuicomponents;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import authoring.GameViewObservable;
import authoring.SceneBackground;
import authoring.SceneBackgroundImage;
import authoring.displayrefactored.controllers.GameViewWindowController;
import authoring.displayrefactored.popups.LevelSizePopupRefactored;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameViewWindowRefactored extends AuthoringUIComponentRefactored implements Observer {
	
	
	private int sizeX;
	private int sizeY;
	private List<ImageView> objectImageViews;
	private ScrollPane scrollPane;
	private StackPane stackPane;
	private Pane backgroundPane;
	private SceneBackground sceneBackground;
	private List<SceneBackgroundImage> list;
	private ComboBox<String> myPanelSelector; 
	private Pane foregroundPane;
	private GameViewWindowController controller;
	private Button myLevelSizeButton;
	private GameViewObservable gameViewObservable = null;
	
	private static final int DEFAULTSIZEX = 1000;
	private static final int DEFAULTSIZEY = 1000;
	
	
	public GameViewWindowRefactored(GameViewWindowController controller) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		objectImageViews = new ArrayList<>();
		updatePaneSize(DEFAULTSIZEX, DEFAULTSIZEY);
	}
	
	@Override
	protected void GenerateComponent() {
		// TODO Auto-generated method stub
		BorderPane borderPane = getBorderPane();
		stackPane = new StackPane();
		list = new ArrayList<>();
		HBox hBox = new HBox();
		myPanelSelector = new ComboBox<>();
		myLevelSizeButton = new Button(ResourceBundleManager.getAuthoring("EditLevelSize"));
		initializeComboBoxes();
		initializeButtons();
		hBox.getChildren().addAll(myPanelSelector, myLevelSizeButton);
		sceneBackground = new SceneBackground(ResourceBundleManager.getPosition("GAMEVIEWSIZE_X"), ResourceBundleManager.getPosition("GAMEVIEWSIZE_Y"));
		backgroundPane = sceneBackground.getPane();
		foregroundPane = new Pane();
		stackPane.setStyle("-fx-border-color: black");
		stackPane.setPrefSize(ResourceBundleManager.getPosition("GAMEVIEWSIZE_X"), ResourceBundleManager.getPosition("GAMEVIEWSIZE_Y"));
		scrollPane = new ScrollPane(stackPane);
		stackPane.getChildren().add(backgroundPane);
		stackPane.getChildren().add(foregroundPane);

		borderPane.setCenter(scrollPane);
		borderPane.setTop(hBox);
	}

	private void initializeButtons() {
		// TODO Auto-generated method stub
		myLevelSizeButton.setOnAction(e-> {
			LevelSizePopupRefactored popup = new LevelSizePopupRefactored(this);
		});
	}

	private void initializeComboBoxes() {
		// TODO Auto-generated method stub
		myPanelSelector.setPromptText(ResourceBundleManager.getAuthoring("ChoosePanel"));
		myPanelSelector.getItems().add("Background");
		myPanelSelector.getItems().add("Foreground");
		myPanelSelector.valueProperty().addListener((o, old, key) -> {
			switchPanes(key);
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		gameViewObservable = (GameViewObservable) o;
		updateForeground(gameViewObservable.getImageViews());
		updateBackground(gameViewObservable.getBackgroundImages());
	}
	
	private void updateForeground(List<ImageView> list) {
		foregroundPane.getChildren().clear();
		foregroundPane.getChildren().addAll(list);
		objectImageViews = list;
	}
	
	private void updateBackground(List<SceneBackgroundImage> list) {
		this.list = list;
		System.out.println(list);
		System.out.println(list.size());
		sceneBackground.clearPane();
		for (SceneBackgroundImage s: list) {
			sceneBackground.addImage(s);
		}

		
	}
	
	public void switchPanes(String key) {
		if (key.equals("Background")) {
			for (ImageView imageView: objectImageViews) {
				
				imageView.setOpacity(0.25);
		
				imageView.setMouseTransparent(true);
			}
			foregroundPane.setMouseTransparent(true);
			
//			stackPane.getChildren().clear();
//			stackPane.getChildren().add(backgroundPane);
		}
		
		if (key.equals("Foreground")) {
//			stackPane.getChildren().clear();
//			stackPane.getChildren().add(backgroundPane);
//			stackPane.getChildren().add(foregroundPane);
				for (ImageView imageView: objectImageViews) {
			
					imageView.setOpacity(1);

					imageView.setMouseTransparent(false);
				}
				foregroundPane.setMouseTransparent(false);
			}
		}
	
	
	public void updatePaneSize(int x_size, int y_size) {
		sizeX = x_size;
		sizeY = y_size;
		
		backgroundPane.setMinSize(sizeX, sizeY);
		foregroundPane.setMinSize(sizeX, sizeY);
		sceneBackground.setRectangle(sizeX, sizeY);
		
		System.out.println("From gameviewwindow refactored: the size should have changed");
	}
	
	
}
