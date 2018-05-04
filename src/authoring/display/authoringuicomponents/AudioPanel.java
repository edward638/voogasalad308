package authoring.display.authoringuicomponents;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import authoring.AudioObservable;
import authoring.display.controllers.AudioController;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class AudioPanel extends AuthoringUIComponent implements Observer {

	private static final int SPACING = 30;
	private HBox hBox;
	private Button chooseAudioButton;
	private Label currentAudio;
	private AudioObservable audioObservable;
	private AudioController audioController;
	
	public AudioPanel(AudioController audioController) {
		// TODO Auto-generated constructor stub
		super();
		this.audioController = audioController;
	}
	
	@Override
	protected void generateComponent() {
		// TODO Auto-generated method stub
		BorderPane borderPane = getBorderPane();
		initializeFXComponents();
		mapFXActions();
		borderPane.setCenter(hBox);
	}

	@Override
	protected void initializeFXComponents() {
		// TODO Auto-generated method stub
		hBox = new HBox(SPACING);
		chooseAudioButton = new Button(ResourceBundleManager.getAuthoring("ChooseAudio"));
		currentAudio = new Label();
		hBox.getChildren().addAll(chooseAudioButton, currentAudio);
	}

	@Override
	protected void mapFXActions() {
		// TODO Auto-generated method stub
		chooseAudioButton.setOnAction(e->{
			chooseAudioFile();
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		audioObservable = (AudioObservable) o;
		currentAudio.setText("Current Audio: " + audioObservable.getAudioName());
	}

	private void chooseAudioFile() {
		String fileName=null;
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().addAll(new ExtensionFilter( "MP3 Files", "*.mp3")); 
		File audioFile = chooser.showOpenDialog(new Stage());
		audioController.setAudio(audioFile);
		System.out.println(audioFile.toURI().toString());
	}

}
