package engine;

import java.util.ArrayList;
import java.util.List;

import engine.behaviors.ExitPortal;
import engine.behaviors.MandatoryBehavior;
import engine.tests.ModelGamePart1;
import engine.tests.ModelGamePart2;
import engine.audio.AudioManager;

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
	
	/* ***************************To Be Replaced With Load From Game Data*************************** */
	private List<GamePart> loadGame(String gameName) {
		GamePart modelGamePart1 = new ModelGamePart1().getGamePart();
		GamePart modelGamePart2 = new ModelGamePart2().getGamePart();
		List<GamePart> gameDataParts = new ArrayList<>();
		gameDataParts.add(modelGamePart1);
		gameDataParts.add(modelGamePart2);
		return gameDataParts;
	}
	/* ***************************To Be Replaced With Load From Game Data*************************** */
	
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
	
	public void changeCurrentGamePart(String newPartID, int portalID) {
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
							if (exitP.getPortalID() == portalID) {
								mainCharacter.setPosition(element.getPosition());
								MandatoryBehavior mb = (MandatoryBehavior) element.getBehavior(MandatoryBehavior.class);
								mb.setPosition(mb.getX()-30, mb.getY());
								break;
							}
						}
					}
					this.getCurrentGamePart().addGameElement(mainCharacter);
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
}
