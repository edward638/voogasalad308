package gamePlayer;

import data.propertiesFiles.ResourceBundleManager;
import javafx.scene.control.Button;
import voogadropbox.VoogaDropbox;

public class UploadGameButton extends Button {

	private String gameName;

	private final static String BASELOCATION = ResourceBundleManager.getPath("BASELOCATION");

	public UploadGameButton (String gameName, /*Whatever other Parameters you need*/) {
		this.gameName = gameName;
		setupButtonProperties(); //fill this in
		setupAction();
		
	}

	private void setupAction() {
		this.setOnAction((event) -> {
			// must first save locally because saving online means going through local games
			// and uploading the one with the correct name
			saveGameLocally();
			saveGameOnline();

		});

	}

	public void saveGameOnline() {
		VoogaDropbox vd = new VoogaDropbox(BASELOCATION);
		vd.downloadGame(gameName);
	}

}
