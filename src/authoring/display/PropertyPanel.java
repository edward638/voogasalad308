package authoring.display;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import authoring.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * @author Maddie Wilkinson
 *
 */
public class PropertyPanel extends MainWindowComponent {

	private ScrollPane myScrollPane;
	private static final String IMAGES_FILEPATH = "./";

	//in AuthoringDisplay, don't forget to remove and re-add this to the root every time it changes
	public PropertyPanel(ResourceBundle resources, Game game, Node root) {
		super(resources, game, root);
		myScrollPane = new ScrollPane();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose Object Image");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

		VBox vBox = new VBox();
		vBox.getChildren().add(makeButton("ChooseImageButton", event -> 
		{
			try {
				File image = fileChooser.showOpenDialog(new Stage());
				Path storedImages = Paths.get(IMAGES_FILEPATH, image.getName());
				Files.copy(image.toPath(), storedImages);
				//put image.getName into a property of the GameObject
			} catch (Exception e) {
				//do nothing
				//this just means the user didn't choose an image
				//which is a perfectly fine thing for them to do
			}
		}));

		myScrollPane.setContent(vBox);
		myScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	}

	public ScrollPane asScrollPane() {
		return myScrollPane;
	}

	@Override
	protected Node asNode() {
		return myScrollPane;
	}


}
