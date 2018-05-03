package authoring.displayrefactored.authoringuicomponents;

import java.util.Observable;
import java.util.Observer;

import authoring.AudioObservable;
import authoring.displayrefactored.controllers.AudioController;
import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class AudioPanel extends AuthoringUIComponentRefactored implements Observer {

	private HBox hBox;
	private Button chooseAudioButton;
	private Label currentAudio;
	private AudioObservable audioObservable;
	private AudioController audioController;
	
	public AudioPanel(AudioController audioController) {
		// TODO Auto-generated constructor stub
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
		hBox = new HBox();
		chooseAudioButton = new Button(ResourceBundleManager.getAuthoring("ChooseAudio"));
		currentAudio = new Label();
		hBox.getChildren().addAll(chooseAudioButton, currentAudio);
	}

	@Override
	protected void mapFXActions() {
		// TODO Auto-generated method stub
		chooseAudioButton.setOnAction(e->{
			
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		audioObservable = (AudioObservable) o;
		currentAudio.setText(audioObservable.getAudioName());
	}
	

}
