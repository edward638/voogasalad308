package display.buttonevents;

import authoring.displayrefactored.popups.eventspopup.TWKPopup;
import authoring.displayrefactored.popups.eventspopup.TriggerWindow;

public class TriggerKeyboardPress implements ButtonEvent {
	
	private TriggerWindow tw;

	public TriggerKeyboardPress(TriggerWindow intw) {
		tw = intw;
	}

	@Override
	public void pressed() {
		new TWKPopup(tw);
	}

}
