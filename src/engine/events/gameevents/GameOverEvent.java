package engine.events.gameevents;

import engine.GameElement;
import engine.GameState;

public class GameOverEvent extends GameEvent{
	
	private GameElement toRemove;
	
	public GameOverEvent(GameElement gameElement) {
		toRemove = gameElement;
		//System.out.println("Remove Event created");
	}

//	@Override
//	public void execute(GamePart part) {
//		System.out.println("GAME OVER EVENT");
//		//state.setState(state.getGameMetaData().getLevel(0));
////		if (state.getGameMetaData().getCurrentLevel() == state.getGameMetaData().getLevel(0)) {
////			state.changeState(state.getGameMetaData().getCurrentLevel(), state.getGameMetaData().getLevel(1));
////		} else {
////			state.changeState(state.getGameMetaData().getCurrentLevel(), state.getGameMetaData().getLevel(0));
////		}
//		//state.resetGame(state.getGameMetaData().getLevel(0));
//	}

	@Override
	public void execute(GameState gameState) {
		// TODO Auto-generated method stub
		
	}
}
