package engine;

import java.util.ArrayList;
import java.util.List;

import engine.behaviors.EntrancePortal;
import engine.behaviors.ExitPortal;
import engine.behaviors.MandatoryBehavior;
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
								System.out.println("Did this work");
								if (element.hasBehavior(EntrancePortal.class)) {
									System.out.println("This is also an entrance portal");
									EntrancePortal entranceP = (EntrancePortal) element.getBehavior(EntrancePortal.class);
									entranceP.disableTemp();
								}
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
