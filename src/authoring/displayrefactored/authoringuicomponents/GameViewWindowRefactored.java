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


/**
 * 
 * @author Edward Zhuang
 * GameViewWindow which provides main visual representation for created game objects and scene background.
 */
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
		this.controller = controller;
		objectImageViews = new ArrayList<>();
		updatePaneSize(DEFAULTSIZEX, DEFAULTSIZEY);
	}
	
	@Override
	protected void GenerateComponent() {
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
		myLevelSizeButton.setOnAction(e-> {
			LevelSizePopupRefactored popup = new LevelSizePopupRefactored(this);
		});
	}

	private void initializeComboBoxes() {
		myPanelSelector.setPromptText(ResourceBundleManager.getAuthoring("ChoosePanel"));
		myPanelSelector.getItems().add("Background");
		myPanelSelector.getItems().add("Foreground");
		myPanelSelector.valueProperty().addListener((o, old, key) -> {
			switchPanes(key);
		});
	}

	/**
	 * Called by Game when model is updated, updates the game window view
	 */
	@Override
	public void update(Observable o, Object arg) {
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
		sceneBackground.clearPane();
		for (SceneBackgroundImage s: list) {
			sceneBackground.addImage(s);
		}
	}
	
	/**
	 * Switches between foreground and background editing, based on which option is selected in Combobox
	 * @param key
	 */
	public void switchPanes(String key) {
		if (key.equals("Background")) {
			for (ImageView imageView: objectImageViews) {
				imageView.setOpacity(0.25);
				imageView.setMouseTransparent(true);
			}
			for (SceneBackgroundImage image: list) {
				image.setOpacity(1.0);
			}
			foregroundPane.setMouseTransparent(true);
		}
		
		if (key.equals("Foreground")) {
				for (ImageView imageView: objectImageViews) {
					imageView.setOpacity(1);
					imageView.setMouseTransparent(false);
				}
				for (SceneBackgroundImage image: list) {
					image.setOpacity(0.25);
				}
				foregroundPane.setMouseTransparent(false);
			}
		}
	
	/**
	 * Resizes the panes of the gameviewwindow, called by level size popup
	 * @param x_size new window size x
	 * @param y_size new window size y
	 */
	public void updatePaneSize(int x_size, int y_size) {
		sizeX = x_size;
		sizeY = y_size;
		backgroundPane.setMinSize(sizeX, sizeY);
		foregroundPane.setMinSize(sizeX, sizeY);
		sceneBackground.setRectangle(sizeX, sizeY);
	}
}
