package authoring.display;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.dropbox.core.DbxException;

import authoring.Game;
import authoring.display.controllers.AuthoringEnvironment;
import authoring.display.popups.NewGamePopup;
import data.GameLoader;
import data.GameSaver;
import data.propertiesFiles.ResourceBundleManager;
import display.AnimatedButton;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import voogadropbox.VoogaDropbox;

public class AuthoringDisplay implements LoadAuthoringInterface {
	
	private static final int GUI_LAYOUT_X = 20;
	private static final int GUI_LAYOUT_Y = 40;
	private static final String SAVE_GAME = "Save Game";
	private static final String LOAD_GAME = "Load Game";
	private static final String NEW_GAME = "New Game";
	private static final String NAME = "Authoring Environment";
	private static final String baseLocation = "./data/gamedata/games/";
	
	private static final int WIDTH = 1500;
	private static final int HEIGHT = 1000;
	private Pane root;
	private HBox buttonBox;
	private Button newGameButton;
	private Button loadGameButton;
	private Button saveGameButton;
	private Button saveOnlineButton;
	private ComboBox<String> gameNames;
	private Game currentGame;
	private AuthoringEnvironment authoringEnvironmentRefactored;
	
	public AuthoringDisplay(Stage stage) {
		initialize(stage);
		initializeButtonBox();
	}

	private void initialize(Stage stage) {
		root = new Pane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
//		scene.getStylesheets().add(this.getClass().getResource("teststyle.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle(NAME);
		stage.show();
	}
	
	private void initializeButtonBox() {
		buttonBox = new HBox();
		buttonBox.setSpacing(30);
		gameNames = new ComboBox<>();
		AnimatedButton newGame = new AnimatedButton(ResourceBundleManager.getButton("NewGame"), NEW_GAME);
		AnimatedButton loadGame = new AnimatedButton(ResourceBundleManager.getButton("SaveGame"), LOAD_GAME);
		AnimatedButton saveGame = new AnimatedButton(ResourceBundleManager.getButton("LoadGame"), SAVE_GAME);
		saveOnlineButton = new Button(ResourceBundleManager.getAuthoring("SaveOnline"));
		
		newGameButton = newGame.getButton();
		loadGameButton = loadGame.getButton();
		saveGameButton = saveGame.getButton();
		buttonBox.getChildren().addAll(newGame.getHBox(),saveGame.getHBox(),loadGame.getHBox(),gameNames,saveOnlineButton);
		setButtonActions();
		initializeComboBoxes();
		root.getChildren().add(buttonBox);
	}

	private void initializeComboBoxes() {
		gameNames.getItems().clear();
		gameNames.setPromptText(ResourceBundleManager.getAuthoring("RecentGames"));
		File directory = new File(baseLocation);
		File[] directoryListing = directory.listFiles();
	        
	        if (directoryListing != null){
	            for (File f : directoryListing){
	                String path = f.getName();
	                gameNames.getItems().add(path);
	            }
	        }
		
	}
	
	private void setButtonActions() {
		newGameButton.setOnAction(e -> {
			NewGamePopup popup = new NewGamePopup(this);
		});
		saveGameButton.setOnAction(e -> {
			GameSaver saver = new GameSaver(currentGame.getName());
			try {
				System.out.println(currentGame.getScenes());
				saver.gameAuthorToXML(currentGame.getScenes(), true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			initializeComboBoxes();
		});
		saveOnlineButton.setOnAction(e->{
			GameSaver saver = new GameSaver(currentGame.getName());
			try {
				System.out.println(currentGame.getScenes());
				saver.gameAuthorToXML(currentGame.getScenes(), true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			initializeComboBoxes();
			VoogaDropbox voogaDropbox = new VoogaDropbox(ResourceBundleManager.getPath("BASELOCATION"));
			try {
				voogaDropbox.uploadGame(currentGame.getName());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		loadGameButton.setOnAction(e -> {
			String gameName = gameNames.getSelectionModel().getSelectedItem();
			GameLoader gameLoader = new GameLoader(gameNames.getSelectionModel().getSelectedItem());
			currentGame = new Game(gameName);
			
			root.getChildren().clear();
			root.getChildren().add(buttonBox);
			currentGame.restoreGame(gameLoader.getGameScenes(true));
			authoringEnvironmentRefactored = new AuthoringEnvironment(currentGame);
			Pane GUIPane = authoringEnvironmentRefactored.getGUI();
			GUIPane.setLayoutX(GUI_LAYOUT_X);
			GUIPane.setLayoutY(GUI_LAYOUT_Y);
			root.getChildren().add(GUIPane);
			
		});
		
	}
	
	public void loadAuthoringEnvironment(Game game) {
		root.getChildren().clear();
		root.getChildren().add(buttonBox);
		currentGame = game;
		authoringEnvironmentRefactored = new AuthoringEnvironment(game);
		Pane GUIPane = authoringEnvironmentRefactored.getGUI();
		GUIPane.setLayoutX(GUI_LAYOUT_X);
		GUIPane.setLayoutY(GUI_LAYOUT_Y);
		root.getChildren().add(GUIPane);
	}

}
