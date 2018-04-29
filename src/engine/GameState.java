package engine;

import java.util.ArrayList;
import java.util.List;

import authoring.GameScene;
import data.GameLoader;
import engine.audio.AudioManager;
import engine.authouringconversion.Converter2;
import engine.behaviors.ExitPortal;

public class GameState {
	private List<GameLevel> gameLevels;
	private GameLevel currentGameLevel;
	private final double gameSpeed = 2;
	private String gameName;
	
	private List<GameElement> addToDisplay;
	private List<GameElement> removeFromDisplay;
	
	private AudioManager audioManager;
	private String musicPath = "data/music/WiiShopChannelMusic.mp3";

	public GameState(String gameName) {
		this.gameName = gameName;
		gameLevels = new ArrayList<>();
		addToDisplay = new ArrayList<>();
		removeFromDisplay = new ArrayList<>();
		audioManager = new AudioManager(1);
		audioManager.newAudioPlayer(musicPath);
		
		constructGameState(loadGame(this.gameName));
	}
	
	private List<GamePart> loadGame(String gameName) {
		GameLoader gameLoader = new GameLoader(gameName);
		Converter2 converter = new Converter2();
		List<GamePart> gameDataParts = new ArrayList<>();
		
		for (GameScene scene : gameLoader.getGameScenes()) {
			gameDataParts.add(converter.gameScene2GamePart(scene));
		}
		return gameDataParts;
	}
	
	private void constructGameState(List<GamePart> gameDataParts) {
		for (GamePart gp : gameDataParts) {
			if (!this.containsLevel(gp.getMyLevelID())) {
				this.addLevel(gp.getMyLevelID());
			}
			this.getLevel(gp.getMyLevelID()).addGamePart(gp);
			
			if (gp.hasMainCharacter()) {
				this.setCurrentGameLevel(this.getLevel(gp.getMyLevelID()));
				this.getCurrentGameLevel().setCurrentGamePart(gp);
			}
		}
	}
	
	public AudioManager getAudioManager() {
		return audioManager;
	}
	
	public void addToDisplay (GameElement ge) {
		addToDisplay.add(ge);
	}
	
	protected List<GameElement> getAddToDisplay() {
		return addToDisplay;
	}
	
	protected void clearAddToDisplay() {
		addToDisplay.clear();
	}
	
	public void removeFromDisplay (GameElement ge) {
		removeFromDisplay.add(ge);
	}
	
	protected List<GameElement> getRemoveFromDisplay() {
		return removeFromDisplay;
	}
	
	protected void clearRemoveFromDisplay() {
		removeFromDisplay.clear();
	}
	
	protected double getGameSpeed() {
		return gameSpeed;
	}
	
	public GamePart getCurrentGamePart() {
		return currentGameLevel.getCurrentGamePart();
	}
	
	public void changeCurrentGamePart(String newPartID, Integer portalID) {
		for (GameLevel gl : gameLevels) {
			for (GamePart newGamePart : gl.getGameParts()) {
				if(newGamePart.getGamePartID().equals(newPartID)) {
					GameElement mainCharacter = this.getCurrentGamePart().getMainCharacter();
					this.getCurrentGamePart().removeGameElement(mainCharacter);
					
					for (GameElement element : this.getCurrentGamePart().getElements()) {
						removeFromDisplay(element);
					}
					
					setCurrentGameLevel(gl);
					gl.setCurrentGamePart(newGamePart);
					for (GameElement element : this.getCurrentGamePart().getElements()) {
						if (element.hasBehavior(ExitPortal.class)) {
							ExitPortal exitP = (ExitPortal) element.getBehavior(ExitPortal.class);
							if (exitP.getPortalID().equals(portalID)) {
								mainCharacter.setPosition(element.getPosition());
								this.getCurrentGamePart().addGameElement(mainCharacter);
								break;	
							}
						}
						
					}
					
					for (GameElement element : this.getCurrentGamePart().getElements()) {
						addToDisplay(element);
					}
					
				}
			}
		}
		
	}
	
	public void resetLevel(String levelID) {
		if(this.getCurrentGameLevel().getGameLevelID().equals(levelID)) {
			for (GameElement element : this.getCurrentGamePart().getElements()) {
				removeFromDisplay(element);
			}
		}
		
		GameLevel toReset = this.getLevel(levelID);
		List<GamePart> gameDataParts = loadGame(this.gameName);
		for(GamePart gamePart : toReset.getGameParts()) {
			for (GamePart initialGamePart : gameDataParts) {
				if (initialGamePart.getGamePartID().equals(gamePart.getGamePartID())) {
					gamePart = initialGamePart;
				}				
				if (gamePart.hasMainCharacter()) {
					toReset.setCurrentGamePart(gamePart);
				}
			}
		}
		
		if(this.getCurrentGameLevel().getGameLevelID().equals(levelID)) {
			for (GameElement element : this.getCurrentGamePart().getElements()) {
				addToDisplay(element);
			}
		}
	}
	
	protected void setCurrentGameLevel(GameLevel gl) {
		currentGameLevel = gl;
	}
	
	protected GameLevel getCurrentGameLevel() {
		return currentGameLevel;
	}
	
	protected boolean containsLevel(String levelID) {
		return gameLevels.stream().anyMatch(gl -> gl.getGameLevelID().equals(levelID));
	}
	
	protected GameLevel getLevel(String levelID) {
		for (GameLevel gl : gameLevels) {
			if (gl.getGameLevelID().equals(levelID))
				return gl;
		}
		System.out.println("ERROR: Game Level Not Found");
		return null;
	}
	
	protected void addLevel(String levelID) {
		gameLevels.add(new GameLevel(levelID));
	}
	
	protected List<GamePart> getAllGameParts() {
		List<GamePart> toReturn = new ArrayList<>();
		for (GameLevel gl : this.gameLevels) {
			toReturn.addAll(gl.getGameParts());
		}
		return toReturn;
	}
}
