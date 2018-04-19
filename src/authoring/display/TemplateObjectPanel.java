package authoring.display;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import authoring.Game;
import authoring.GameObject;
import data.GameObjectManager;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/*
 * @author Edward Zhuang
 */
public class TemplateObjectPanel extends AuthoringUIComponent {

	ListView<String> listView;
	GameObjectManager gameObjectManager;
	ObjectInformationDisplay informationDisplay;
	TreeMap<String, GameObject> map;
	BorderPane pane;
	
	public TemplateObjectPanel(ResourceBundle resources, Game game, Node root) {
		super(resources, game, root);
		listView = new ListView<String>();
		gameObjectManager = new GameObjectManager();
		pane = new BorderPane();
		pane.setTop(listView);
		informationDisplay = new ObjectInformationDisplay(resources, game, root);
		pane.setCenter(informationDisplay.asPane());
		update();
	}
	
	
	/**
	 * When new object is added, this will provide an updated list
	 */
	public void update() {
		listView.getItems().removeAll();
		map = (TreeMap<String, GameObject>) gameObjectManager.getSavedGameObjects();
		SortedSet<String> keys = new TreeSet<>(map.keySet());
		for (String key: keys) {
			listView.getItems().add(key);
		}
		listView.setOnMouseClicked(e -> informationDisplay.update(map.get(listView.getSelectionModel().getSelectedItem())));
	}
	
	public void addCustomObject(GameObject gameObject, String name) throws IOException {
		gameObjectManager.saveCustomGameObject(gameObject, name);
		update();
	}
	
	public Node asPane() {
		return pane;
	}
	
	
}
