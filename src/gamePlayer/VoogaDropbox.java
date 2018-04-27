package gamePlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CreateFolderErrorException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.UploadErrorException;

import data.propertiesFiles.ResourceBundleManager;

public class VoogaDropbox {

	private final String ACCESS_TOKEN = "VFY6qzXoWGAAAAAAAAAAG6EdeJfxadAHl-RA-1rgXqgulUGvTxrsCyYULBbtLqW9";
	private DbxClientV2 client;
	private String BASELOCATION;

	public VoogaDropbox(String gameFolderPath) {

		BASELOCATION = gameFolderPath;

		DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
		client = new DbxClientV2(config, ACCESS_TOKEN);

	}

	public void uploadGame(String gameName) throws DbxException, FileNotFoundException, IOException {
		File demo308Folder = new File(BASELOCATION + "/" + gameName);
		createFolder(gameName);
		File[] demo308FileList = demo308Folder.listFiles();
		// write recursive function using loops below. base case is if isFile() is true.
		// harder part will be to get the proper pathname to the file, i think we will
		// need to be adding to some string as we go deeper into the recursive loop. 
		for (File f : demo308FileList) {
			if (f.isFile()) {
				uploadFile(gameName, f);
			} else if (f.isDirectory()) {
				File[] subgameFolder = f.listFiles();
				for (File subFile : subgameFolder) {
					uploadFile(gameName + "/" + f.getName(), subFile);
				}
			}
		}

	}

	private void uploadFile(String filePath, File fileName)
			throws FileNotFoundException, IOException, UploadErrorException, DbxException {
		try (InputStream in = new FileInputStream(BASELOCATION + "/" + filePath + "/" + fileName.getName())) {
			FileMetadata metadata = client.files().uploadBuilder("/games/" + filePath + "/" + fileName.getName())
					.uploadAndFinish(in);
		}

	}

	public void createFolder(String folderName) throws DbxException {
		try {
			FolderMetadata folder = client.files().createFolder(folderName);
		} catch (Exception e) {
			// errorbox that says please rename your game. this error check might be done
			// for some button instead
		}
	}

}
