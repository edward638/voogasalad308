package gamePlayer.buttons;

import java.util.ArrayList;
import java.util.List;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.ListFolderErrorException;

import data.propertiesFiles.ResourceBundleManager;
import gamePlayer.GameListViewer;
import javafx.scene.control.Button;
import voogadropbox.VoogaDropbox;

public class LoadOnlineButton extends PlayerButtons {

	private static final String BUTTONTEXT = "Download Game from Online";
	private final static String BASELOCATION = ResourceBundleManager.getPath("BASELOCATION");

	public LoadOnlineButton(double x, double y, double width, double height, ButtonData buttonData) {
		super(x, y, width, height, buttonData, BUTTONTEXT);
	}

	@Override
	protected void setAction() {
		this.setOnAction(event -> {
			List<String> gameList;
			VoogaDropbox voogaDropbox = new VoogaDropbox(BASELOCATION);
			try {
				gameList = voogaDropbox.getOnlineGames();
			} catch (Exception e) {
				gameList = new ArrayList<>();
			}

			GameListViewer listViewer = new GameListViewer(gameList, buttonData, voogaDropbox);
			buttonData.addToRoot(listViewer);

			// create listview that receives a list of strings that represent the name of
			// the games on the dropbox

			// add a "download" button to download the selected choice in the listview

		});

	}

}
