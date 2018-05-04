package data;

import authoring.GameObject;
import authoring.GameScene;
import authoring.GameSceneSerializable;
import data.propertiesFiles.ResourceBundleManager;
import engine.GamePart;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameSaver {

	private Serializer serializer;
	private String baseLocation = ResourceBundleManager.getPath("BASELOCATION");
	private String gameLocation;
	private String gameDescriptionLocation;
	private String gameScenesLocation;
	private String gameImagesLocation;
	private String gameSavesLocation;

	/**
	 * GameSaver constructor
	 * 
	 * @param gameName
	 *            desired name of game root file
	 */
	public GameSaver(String gameName) {
		gameLocation = baseLocation + gameName + "/";
		gameDescriptionLocation = gameLocation + ResourceBundleManager.getPath("DESCRIPTION");
		gameScenesLocation = gameLocation + ResourceBundleManager.getPath("SCENES");
		gameImagesLocation = gameLocation + ResourceBundleManager.getPath("IMAGES");
		gameSavesLocation = gameLocation + ResourceBundleManager.getPath("SAVES");

		serializer = new Serializer();

		if (!new File(gameLocation).exists()) {
			System.out.println("Please initialize game first."); // TODO: throw an error here that says game isn't
																	// initialized!
		}
	}

	/**
	 * provides a description of the game
	 * 
	 * @param name
	 *            of game
	 * @param description
	 *            description of game
	 * @param descriptionImage
	 *            thumbnail image to represent game
	 */
	public void addDescription(String name, String description) throws FileNotFoundException {
		try (PrintWriter out = new PrintWriter(gameDescriptionLocation + ResourceBundleManager.getPath("NAME"))) {
			out.println(name);
		}
		try (PrintWriter out = new PrintWriter(
				gameDescriptionLocation + ResourceBundleManager.getPath("DESCRIPTIONTEXT"))) {
			out.println(description);
		}

		try {
			BufferedImage img = ImageIO.read(new File(ResourceBundleManager.getPath("DEFAULTIMAGE")));
			ImageIO.write(img, "jpg",
					new File(gameDescriptionLocation + ResourceBundleManager.getPath("DESCRIPTIONIMAGE")));
		} catch (IOException e) {
			e.printStackTrace(); // TODO: remove this print stacktrace!
		}

	}

	/**
	 * Saves the game that authoring environment has created. A folder is created
	 * for each level, and in that folder the scene is created along with xml files
	 * for each game object
	 * 
	 * @param objectMap
	 *            map of gamescenes (levels) to game objects in that level
	 */
	public void gameAuthorToXML(List<GameScene> gameSceneList, boolean isAuthoring) throws IOException {
		List<GameSceneSerializable> serializables = new ArrayList<>();
		for (GameScene scene : gameSceneList) {
			serializables.add(new GameSceneSerializable(scene));
		}
		if (isAuthoring) {
			serializer.gameAuthorToXML(gameScenesLocation, serializables);
		} else {
			serializer.gameAuthorToXML(gameSavesLocation, serializables);
		}

	}

	/**
	 * Saves the game when the game is being played in game engine
	 * 
	 * @throws IOException
	 */
	public void saveGamePart(GamePart gamePart) throws IOException {
		serializer.savePartToXML(gameSavesLocation, gamePart);
	}

}
